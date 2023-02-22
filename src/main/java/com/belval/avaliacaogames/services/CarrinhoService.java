package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.Carrinho;
import com.belval.avaliacaogames.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repository;

	public List<Carrinho> findAll() {
		return repository.findAll();
	}

	public Carrinho findById(Long id) {
		Optional<Carrinho> obj = repository.findById(id);
		return obj.get();
	}
	/*
	 * public List<Carrinho> findByUsuario(Usuario usuario) { return
	 * repository.findByUsuario(usuario); }
	 */
	/*
	 * public Carrinho findByProduto(Produto produto) { Optional<Carrinho> obj =
	 * repository.findByProduto(produto); if (obj.isPresent()) { return obj.get(); }
	 * else { return new Carrinho(); } }
	 */
}
