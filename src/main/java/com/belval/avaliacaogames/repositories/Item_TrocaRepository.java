package com.belval.avaliacaogames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Item_Troca;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Troca;

@Repository
public interface Item_TrocaRepository extends JpaRepository<Item_Troca, Long> {
	List<Item_Troca> findByTroca(Troca troca);
	Optional<Item_Troca> findByProdutoAndTroca(Produto produto, Troca troca);
}
