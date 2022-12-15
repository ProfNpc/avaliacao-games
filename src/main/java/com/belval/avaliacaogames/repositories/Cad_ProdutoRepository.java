package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Cad_Produto;

@Repository
public interface Cad_ProdutoRepository extends JpaRepository<Cad_Produto, Long> {

}
