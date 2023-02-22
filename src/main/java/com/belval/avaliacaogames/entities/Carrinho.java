package com.belval.avaliacaogames.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long codCarrinho;
	public Integer quantCarrinho;

	// Ligação com tabela anuncio
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "codAnuncio")
	public Anuncio anuncio;

	// Ligação com tabela usuario
	@ManyToOne
	@JoinColumn(name = "cpfUsuario")
	public Usuario usuario;

	// Constructors
	public Carrinho() {
		super();
	}

	public Carrinho(Long codCarrinho, Integer quantCarrinho, Anuncio anuncio, Usuario usuario) {
		super();
		this.codCarrinho = codCarrinho;
		this.quantCarrinho = quantCarrinho;
		this.anuncio = anuncio;
		this.usuario = usuario;
	}

	// Getters and Setters anuncios
	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	
	public Long getCodAnuncio() {
		return anuncio.getCodAnuncio();
	}

	// Getters and Setters usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getters and Setters
	public Long getCodCarrinho() {
		return codCarrinho;
	}

	public void setCodCarrinho(Long codCarrinho) {
		this.codCarrinho = codCarrinho;
	}

	public Integer getQuantCarrinho() {
		return quantCarrinho;
	}

	public void setQuantCarrinho(Integer quantCarrinho) {
		this.quantCarrinho = quantCarrinho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCarrinho == null) ? 0 : codCarrinho.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrinho other = (Carrinho) obj;
		if (codCarrinho == null) {
			if (other.codCarrinho != null)
				return false;
		} else if (!codCarrinho.equals(other.codCarrinho))
			return false;
		return true;
	}

}
