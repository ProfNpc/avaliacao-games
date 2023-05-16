package com.belval.avaliacaogames.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.belval.avaliacaogames.entities.ItemPedidoTroca;
import com.belval.avaliacaogames.entities.PedidoTroca;
import com.belval.avaliacaogames.repositories.ItemPedidoTrocaRepository;
import com.belval.avaliacaogames.repositories.PedidoTrocaRepository;
import com.belval.avaliacaogames.services.CadProdutoService;

@Component
@EnableScheduling
public class PedidoTrocaScheduler {

	@Autowired
	private PedidoTrocaRepository repository;
	
	@Autowired
	private ItemPedidoTrocaRepository itemPedidoTrocaRepository;
	
	@Autowired
	private CadProdutoService cadProdutoService;

	@Scheduled(fixedRate = 60000) // Executa a cada minuto = 1000, hora = 3600000
	public void atualizarStatusPedidosTrocas() {
		List<PedidoTroca> pedidosTrocas = repository.findAll();

		for (PedidoTroca pedidoTroca : pedidosTrocas) {
			switch (pedidoTroca.getStatusRemetente()) {
			case "PREPARANDO":
				pedidoTroca.setStatusRemetente("NA_TRANSPORTADORA");
				pedidoTroca.setStatusDestinatario("NA_TRANSPORTADORA");
				break;
			case "EM_PREPARO":
				pedidoTroca.setStatusRemetente("A_CAMINHO");
				pedidoTroca.setStatusDestinatario("A_CAMINHO");
				break;
			case "NA_TRANSPORTADORA":
				// Adiciona os ItensPedidoTroca aos respectivos usuarios
				for (ItemPedidoTroca item : itemPedidoTrocaRepository.findByIdPedidoTroca(pedidoTroca)) {
					cadProdutoService.addCadProdutoToUsuario(item.getProduto(), item.getUsuario());
				}
				
				pedidoTroca.setStatusRemetente("ENTREGUE");
				pedidoTroca.setStatusDestinatario("ENTREGUE");
				break;
			default:
				// Não faz nada se o pedido troca já foi entregue ou cancelado
				break;
			}

			repository.save(pedidoTroca);
		}
	}

}
