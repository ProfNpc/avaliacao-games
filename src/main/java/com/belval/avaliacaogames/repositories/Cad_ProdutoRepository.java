package com.belval.avaliacaogames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface Cad_ProdutoRepository extends JpaRepository<Cad_Produto, Long> {

	List<Cad_Produto> findByUsuario(Usuario usuario);
	
	Optional<Cad_Produto> findByProduto(Produto produto);
}
