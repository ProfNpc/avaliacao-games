package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Troca;

@Repository
public interface TrocaRepository extends JpaRepository<Troca, Long> {

}
