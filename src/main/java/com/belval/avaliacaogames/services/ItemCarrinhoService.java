package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.ItemCarrinho;
import com.belval.avaliacaogames.repositories.ItemCarrinhoRepository;

@Service
public class ItemCarrinhoService {

	@Autowired
	private ItemCarrinhoRepository repository;

	public List<ItemCarrinho> findAll() {
		return repository.findAll();
	}

	public ItemCarrinho findById(Long id) {
		Optional<ItemCarrinho> obj = repository.findById(id);
		return obj.get();
	}
}
