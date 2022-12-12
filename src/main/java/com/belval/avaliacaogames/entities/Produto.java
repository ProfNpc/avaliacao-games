package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_prod;
	private String nome_prod;
	private Integer valor_prod;

	// Constructors
	public Produto() {

	}

	public Produto(Long cod_prod, String nome_prod, Integer valor_prod) {
		this.cod_prod = cod_prod;
		this.nome_prod = nome_prod;
		this.valor_prod = valor_prod;
	}

	// Getters and Setters
	public String getNome_prod() {
		return nome_prod;
	}

	public void setNome_prod(String nome_prod) {
		this.nome_prod = nome_prod;
	}

	public Integer getValor_prod() {
		return valor_prod;
	}

	public void setValor_prod(Integer valor_prod) {
		this.valor_prod = valor_prod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_prod == null) ? 0 : cod_prod.hashCode());
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
		Produto other = (Produto) obj;
		if (cod_prod == null) {
			if (other.cod_prod != null)
				return false;
		} else if (!cod_prod.equals(other.cod_prod))
			return false;
		return true;
	}

	
}
