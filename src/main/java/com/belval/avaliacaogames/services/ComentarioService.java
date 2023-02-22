package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.Comentario;
import com.belval.avaliacaogames.repositories.ComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;

	public List<Comentario> findAll() {
		return repository.findAll();
	}

	public Comentario findById(Long id) {
		Optional<Comentario> obj = repository.findById(id);
		return obj.get();
	}
	/*
	 * public Comentario findByNomeProd(String nomeProd) { Optional<Comentario> obj
	 * = repository.findByNomeProd(nomeProd); if (obj.isPresent()) { return
	 * obj.get(); } else { return new Comentario(); } }
	 */
}
