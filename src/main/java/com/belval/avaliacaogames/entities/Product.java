package com.belval.avaliacaogames.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	// Attribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome_prod;
	private Integer valor_prod;

	// Constructors
	public Product() {

	}

	public Product(Long id, String nome_prod, Integer valor_prod) {
		this.id = id;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// HashCode
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
