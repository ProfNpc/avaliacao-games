package com.belval.avaliacaogames.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.belval.avaliacaogames.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	List<Usuario> findBySobrenome(String sobrenome);
	Usuario findByNome(String nome);
}
