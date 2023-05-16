package com.belval.avaliacaogames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface TrocaRepository extends JpaRepository<Troca, Long> {

	List<Troca> findByUsuario(Usuario usuario);

	Optional<Troca> findByCadProduto(CadProduto cad_produto);

	List<Troca> findAllByUsuarioCpfNotOrderByNomeTrocaDesc(Long cpf);
	
	List<Troca> findAllByUsuarioCpfNotAndStatusTrocaTrue(Long cpf);
	
	List<Troca> findAllByStatusTrocaTrue();
}