package com.belval.avaliacaogames.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belval.avaliacaogames.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
	boolean existsByEmail(String email);
	
}
