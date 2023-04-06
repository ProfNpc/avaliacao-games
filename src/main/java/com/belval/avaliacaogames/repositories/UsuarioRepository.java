package com.belval.avaliacaogames.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsById(Long cpf);
}
