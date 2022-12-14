
package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario findById(Long cpf) {
		Optional<Usuario> obj = repository.findById(cpf);
		return obj.get();
	}
	
	public Usuario findByEmail(String email) {
		Optional<Usuario> obj = repository.findByEmail(email);
		return obj.get();
	}

	public List<Usuario> findAll(String email) {
		return repository.findAll();
	}
	
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}
}
