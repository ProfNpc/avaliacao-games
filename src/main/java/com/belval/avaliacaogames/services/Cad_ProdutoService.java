package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.Cad_ProdutoRepository;

@Service
public class Cad_ProdutoService {

	@Autowired
	private Cad_ProdutoRepository repository;
	
	public List<Cad_Produto> findAll(){
		return repository.findAll();
	}
	
	public Cad_Produto findById(Long id) {
		Optional<Cad_Produto> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Cad_Produto> findByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}
	
	public Cad_Produto findByProduto(Produto produto) {
		Optional<Cad_Produto> obj = repository.findByProduto(produto);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return new Cad_Produto();
		}
	}
	
	public Cad_Produto findByUsuarioAndProduto(Usuario usuario, Produto produto) {
		return repository.findByUsuarioAndProduto(usuario, produto);
	}
}
