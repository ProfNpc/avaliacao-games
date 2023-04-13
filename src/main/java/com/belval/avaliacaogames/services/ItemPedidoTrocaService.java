package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.ItemPedidoTroca;
import com.belval.avaliacaogames.repositories.ItemPedidoTrocaRepository;

@Service
public class ItemPedidoTrocaService {

	@Autowired
	private ItemPedidoTrocaRepository repository;

	public List<ItemPedidoTroca> findAll() {
		return repository.findAll();
	}

	public ItemPedidoTroca findById(Long id) {
		Optional<ItemPedidoTroca> obj = repository.findById(id);
		return obj.get();
	}
}
