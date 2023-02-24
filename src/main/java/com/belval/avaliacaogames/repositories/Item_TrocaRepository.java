package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Item_Troca;

@Repository
public interface Item_TrocaRepository extends JpaRepository<Item_Troca, Long> {

}
