package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.ItemTroca;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.repositories.ItemTrocaRepository;

@Service
public class ItemTrocaService {

	@Autowired
	private ItemTrocaRepository repository;

	public List<ItemTroca> findAll() {
		return repository.findAll();
	}

	public ItemTroca findById(Long id) {
		Optional<ItemTroca> obj = repository.findById(id);
		return obj.get();
	}
	
	public ItemTroca findByProdutoAndTroca(Produto produto, Troca troca) {
		Optional<ItemTroca> obj = repository.findByProdutoAndTroca(produto, troca);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return null;
		}
	}
	
	public List<ItemTroca> findByTroca(Troca troca) {
		return repository.findByTroca(troca);
	}
	/*
	 * public List<Item_Troca> findByUsuario(Usuario usuario) { return
	 * repository.findByUsuario(usuario); }
	 * 
	 * public Item_Troca findByProduto(Produto produto) { Optional<Item_Troca> obj =
	 * repository.findByProduto(produto); if (obj.isPresent()) { return obj.get(); }
	 * else { return new Item_Troca(); } }
	 */
}
