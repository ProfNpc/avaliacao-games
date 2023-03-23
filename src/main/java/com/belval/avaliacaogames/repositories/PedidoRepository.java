package com.belval.avaliacaogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Pedido;
import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByUsuario(Usuario usuario);
	

}
