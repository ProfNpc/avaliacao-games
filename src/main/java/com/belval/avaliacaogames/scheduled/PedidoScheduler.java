package com.belval.avaliacaogames.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.belval.avaliacaogames.entities.Pedido;
import com.belval.avaliacaogames.repositories.PedidoRepository;

@Component
@EnableScheduling
public class PedidoScheduler {

	@Autowired
	private PedidoRepository repository;

	@Scheduled(fixedRate = 60000) // Executa a cada 1 minuto
	public void atualizarStatusPedidos() {
		List<Pedido> pedidos = repository.findAll();

		for (Pedido pedido : pedidos) {
			switch (pedido.getStatusPedido()) {
			case "PAGAMENTO_EFETUADO":
				pedido.setStatusPedido("EM_PREPARO");
				break;
			case "EM_PREPARO":
				pedido.setStatusPedido("NA_TRANSPORTADORA");
				break;
			case "NA_TRANSPORTADORA":
				pedido.setStatusPedido("A_CAMINHO");
				break;
			case "A_CAMINHO":
				pedido.setStatusPedido("ENTREGUE");
				break;
			default:
				// Não faz nada se o pedido já foi entregue ou cancelado
				break;
			}

			repository.save(pedido);
		}
	}

}
