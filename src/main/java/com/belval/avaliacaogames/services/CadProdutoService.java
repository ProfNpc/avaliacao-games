package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.CadProdutoRepository;

@Service
public class CadProdutoService {

	@Autowired
	private CadProdutoRepository repository;
	
	public List<CadProduto> findAll(){
		return repository.findAll();
	}
	
	public CadProduto findById(Long id) {
		Optional<CadProduto> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<CadProduto> findByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}
	
	public CadProduto findByProduto(Produto produto) {
		Optional<CadProduto> obj = repository.findByProduto(produto);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return new CadProduto();
		}
	}
	
	public CadProduto findByUsuarioAndProduto(Usuario usuario, Produto produto) {
		return repository.findByUsuarioAndProduto(usuario, produto);
	}
	
	public void addCadProdutoToUsuario(Produto produto, Usuario usuario) {
		CadProduto cp = repository.findByUsuarioAndProduto(usuario, produto);
		if (cp == null) {
			cp = new CadProduto();
			cp.setUsuario(usuario);
			cp.setProduto(produto);
			cp.setQuantidade(1L);
		} else {
			cp.setQuantidade(cp.getQuantidade() + 1);
		}
		
		repository.save(cp);
	}
	
	public void removeCadProdutoFromUsuario(Produto produto, Usuario usuario) {
		CadProduto cp = repository.findByUsuarioAndProduto(usuario, produto);
		if (cp == null) return;
		
		cp.setQuantidade(cp.getQuantidade() - 1);
		if (cp.getQuantidade() == 0) {
			repository.delete(cp);
		} else {
			repository.save(cp);
		}
	}
}
