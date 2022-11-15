package com.belval.avaliacaogames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.avaliacaogames.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/*List<Usuario> findBySobrenome(String sobrenome);
	Usuario findByNome(String nome);*/
}
