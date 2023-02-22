package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.get();
	}

	public Produto findByNomeProd(String nomeProd) {
		Optional<Produto> obj = repository.findByNomeProd(nomeProd);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return new Produto();
		}
	}
}
