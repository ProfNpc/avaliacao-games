package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.avaliacaogames.entities.Produto;

public interface ProductRepository extends JpaRepository<Produto, Long> {

}
