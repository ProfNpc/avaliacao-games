package com.belval.avaliacaogames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface CadProdutoRepository extends JpaRepository<CadProduto, Long> {

	List<CadProduto> findByUsuario(Usuario usuario);

	Optional<CadProduto> findByProduto(Produto produto);

	CadProduto findByUsuarioAndProduto(Usuario usuario, Produto produto);
}
