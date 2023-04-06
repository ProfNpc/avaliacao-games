
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
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return null;
		}
	}
	
	public Usuario findByEmail(String email) {
		Optional<Usuario> obj = repository.findByEmail(email);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			return null;
		}
	}

	public List<Usuario> findAll(String email) {
		return repository.findAll();
	}
	
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}
	
	public boolean existsById(Long cpf) {
		return repository.existsById(cpf);
	}

}
