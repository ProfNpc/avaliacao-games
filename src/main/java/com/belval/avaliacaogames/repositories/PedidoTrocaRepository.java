package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.PedidoTroca;

@Repository
public interface PedidoTrocaRepository extends JpaRepository<PedidoTroca, Long> {

}
