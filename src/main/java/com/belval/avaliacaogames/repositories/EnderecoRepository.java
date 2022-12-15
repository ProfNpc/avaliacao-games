package com.belval.avaliacaogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	//Optional<Endereco> findByCpf_End(Usuario usuario);
	List<Endereco> findByUsuario(Usuario usuario);
}
