package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.PedidoTroca;
import com.belval.avaliacaogames.repositories.PedidoTrocaRepository;

@Service
public class PedidoTrocaService {

	@Autowired
	private PedidoTrocaRepository repository;

	public List<PedidoTroca> findAll() {
		return repository.findAll();
	}

	public PedidoTroca findById(Long id) {
		Optional<PedidoTroca> obj = repository.findById(id);
		return obj.get();
	}
}
