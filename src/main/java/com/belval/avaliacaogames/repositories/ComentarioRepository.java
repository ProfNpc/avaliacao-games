package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
