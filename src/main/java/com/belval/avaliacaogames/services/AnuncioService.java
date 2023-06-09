package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	public List<Anuncio> findAll() {
		return repository.findAll();
	}

	public Anuncio findById(Long id) {
		Optional<Anuncio> obj = repository.findById(id);
		return obj.get();
	}

	public List<Anuncio> findByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}

	public List<Anuncio> findAllAnunciosExcetoUsuario(Long cpf) {
		Usuario usuario = usuarioService.findById(cpf);
		return repository.findAllByUsuarioCpfNotOrderByNomeAnuncioDesc(usuario.getCpf());
	}
	
	public List<Anuncio> findAllValidAnunciosExcetoUsuario(Long cpf) {
		Usuario usuario = usuarioService.findById(cpf);
		return repository.findAllValidAnunciosExcetoUsuario(usuario.getCpf());
	}
	
	public List<Anuncio> findAllValidAnuncios() {
		return repository.findAllByQuantAnuncioGreaterThan(0);
	}

}
