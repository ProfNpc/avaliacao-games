package com.belval.avaliacaogames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.ItemTroca;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Troca;

@Repository
public interface ItemTrocaRepository extends JpaRepository<ItemTroca, Long> {
	List<ItemTroca> findByTroca(Troca troca);

	Optional<ItemTroca> findByProdutoAndTroca(Produto produto, Troca troca);
}
