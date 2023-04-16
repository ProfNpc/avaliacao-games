package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.repositories.TrocaRepository;

@Service
public class TrocaService {

	@Autowired
	private TrocaRepository repository;

	public List<Troca> findAll() {
		return repository.findAll();
	}

	public Troca findById(Long id) {
		Optional<Troca> obj = repository.findById(id);
		return obj.get();
	}
	
	public Troca findByCadProduto(CadProduto cadProduto) {
		Optional<Troca> obj = repository.findByCadProduto(cadProduto);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return null;
		}
	}
	
	/*
	 * public List<Troca> findByUsuario(Usuario usuario) { return
	 * repository.findByUsuario(usuario); }
	 */
	/*
	 * public Troca findByProduto(Produto produto) { Optional<Troca> obj =
	 * repository.findByProduto(produto); if (obj.isPresent()) { return obj.get(); }
	 * else { return new Troca(); } }
	 */
}
