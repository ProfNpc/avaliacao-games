package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codProd;
	private String nomeProd;
	private Integer valorProd;
	private String descProd;

	// Ligação com a tabela cad_produto
	@JsonIgnore
	@OneToMany(mappedBy = "produto")
	private Set<Cad_Produto> cad_produtos = new HashSet<>();

	// Constructors
	public Produto() {

	}

	public Produto(Long codProd, String nomeProd, Integer valorProd, String descProd) {
		this.codProd = codProd;
		this.nomeProd = nomeProd;
		this.valorProd = valorProd;
		this.descProd = descProd;
	}

	// Cad_Produto
	public Set<Cad_Produto> getCad_produtos() {
		return cad_produtos;
	}

	public void setCad_produtos(Set<Cad_Produto> cad_produtos) {
		this.cad_produtos = cad_produtos;
	}

	// Getters and Setters
	public String getNomeProd() {
		return nomeProd;
	}

	public Long getCodProd() {
		return codProd;
	}

	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}

	public Integer getValorProd() {
		return valorProd;
	}

	public void setValorProd(Integer valorProd) {
		this.valorProd = valorProd;
	}

	public String getDescProd() {
		return descProd;
	}

	public void setDescProd(String descProd) {
		this.descProd = descProd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codProd == null) ? 0 : codProd.hashCode());
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
		if (codProd == null) {
			if (other.codProd != null)
				return false;
		} else if (!codProd.equals(other.codProd))
			return false;
		return true;
	}

}
