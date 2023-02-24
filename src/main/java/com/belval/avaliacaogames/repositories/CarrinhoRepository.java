package com.belval.avaliacaogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Carrinho;
import com.belval.avaliacaogames.entities.Usuario;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

	List<Carrinho> findByUsuario(Usuario usuario);

	List<Carrinho> findByAnuncio(Anuncio anuncio);
}
