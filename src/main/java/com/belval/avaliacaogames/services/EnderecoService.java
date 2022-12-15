package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public List<Endereco> findAll() {
		return repository.findAll();
	}

	public Endereco findById(Long id) {
		Optional<Endereco> obj = repository.findById(id);
		return obj.get();
	}
	
	public Endereco findByUsuario(Usuario usuario) {
		Optional<Endereco> obj = repository.findByUsuario(usuario);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return new Endereco();
		}
	}
}
