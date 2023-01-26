package com.belval.avaliacaogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{

	List<Anuncio> findByUsuario(Usuario usuario);
	
	List<Anuncio> findByNomeAnuncioContainingIgnoreCase(String nomeAnuncio);
}
