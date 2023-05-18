package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Carrinho implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long codCarrinho;

	// Ligação com tabela usuario
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "cpfUsuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
	private Set<ItemCarrinho> itens = new HashSet<>();

	// Constructors
	public Carrinho() {
		super();
	}

	public Carrinho(Long codCarrinho, Usuario usuario) {
		super();
		this.codCarrinho = codCarrinho;
		this.usuario = usuario;
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
