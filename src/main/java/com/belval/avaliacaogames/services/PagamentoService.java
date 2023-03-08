package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.Pagamento;
import com.belval.avaliacaogames.repositories.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;

	public List<Pagamento> findAll() {
		return repository.findAll();
	}

	public Pagamento findById(Long id) {
		Optional<Pagamento> obj = repository.findById(id);
		return obj.get();
	}
}
