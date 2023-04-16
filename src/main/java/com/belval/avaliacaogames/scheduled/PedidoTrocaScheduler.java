package com.belval.avaliacaogames.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.belval.avaliacaogames.entities.PedidoTroca;
import com.belval.avaliacaogames.repositories.PedidoTrocaRepository;

@Component
@EnableScheduling
public class PedidoTrocaScheduler {

	@Autowired
	private PedidoTrocaRepository repository;

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
