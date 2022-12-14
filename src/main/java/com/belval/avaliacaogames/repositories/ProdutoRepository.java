package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
