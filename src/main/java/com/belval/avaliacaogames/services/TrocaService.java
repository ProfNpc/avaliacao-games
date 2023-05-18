package com.belval.avaliacaogames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.TrocaRepository;

@Service
public class TrocaService {

	@Autowired
	private TrocaRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	public List<Troca> findAll() {
		return repository.findAll();
	}

	public Troca findById(Long id) {
		Optional<Troca> obj = repository.findById(id);
		return obj.get();
	}

	public Troca findByCadProduto(CadProduto cadProduto) {
		Troca obj = repository.findByCadProduto(cadProduto);
		if (obj != null) {
			return obj;
		} else {
			return null;
		}
	}

	public List<Troca> findAllAnunciosExcetoUsuario(Long cpf) {
		Usuario usuario = usuarioService.findById(cpf);
		return repository.findAllByUsuarioCpfNotOrderByNomeTrocaDesc(usuario.getCpf());
	}

	public List<Troca> findAllValidAnunciosExcetoUsuario(Long cpf) {
		return repository.findAllByUsuarioCpfNotAndStatusTrocaTrue(cpf);
	}

	public List<Troca> findAllValidAnuncios() {
		return repository.findAllByStatusTrocaTrue();
	}
}
