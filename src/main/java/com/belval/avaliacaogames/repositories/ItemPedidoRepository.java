package com.belval.avaliacaogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.ItemPedido;
import com.belval.avaliacaogames.entities.Pedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	List<ItemPedido> findByIdPedido(Pedido pedido);
}
