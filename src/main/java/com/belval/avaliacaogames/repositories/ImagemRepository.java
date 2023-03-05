package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

}
