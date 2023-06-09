package com.belval.avaliacaogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	List<Anuncio> findByUsuario(Usuario usuario);

	List<Anuncio> findByNomeAnuncioContainingIgnoreCase(String nomeAnuncio);

	List<Anuncio> findByNomeAnuncioContainingIgnoreCaseAndUsuarioCpfNot(String nomeAnuncio, Long cpf);

	List<Anuncio> findByUsuarioAndNomeAnuncioContainingIgnoreCase(Usuario usuario, String nomeAnuncio);

	List<Anuncio> findAllByUsuarioCpfNotOrderByNomeAnuncioDesc(Long cpf);
	
	@Query("SELECT a FROM Anuncio a WHERE a.usuario.cpf <> ?1 and a.quantAnuncio > 0 ORDER BY a.nomeAnuncio DESC")
	List<Anuncio> findAllValidAnunciosExcetoUsuario(Long cpf);
	
	List<Anuncio> findAllByQuantAnuncioGreaterThan(Integer quantAnuncio);
	
	//List<Anuncio> findAllByQuantVendidaGreaterThanAndUsuarioCpfNotOrderByNomeAnuncioDesc(Integer quantVendida, Long cpf);
}
