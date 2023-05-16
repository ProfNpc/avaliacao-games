package com.belval.avaliacaogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.ItemPedidoTroca;
import com.belval.avaliacaogames.entities.PedidoTroca;

@Repository
public interface ItemPedidoTrocaRepository extends JpaRepository<ItemPedidoTroca, Long> {
	List<ItemPedidoTroca> findByIdPedidoTroca(PedidoTroca pedidoTroca);
}
