package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<CadProduto> cad_produtos = new HashSet<>();

	// Ligação com a tabela Item_Troca
	@JsonIgnore
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<ItemTroca> itens_troca = new ArrayList<>();

	// ligação com tabela imagem
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "codImagem")
	private Imagem imagem;

	// Constructors
	public Produto() {

	}

	public Produto(Long codProd, String nomeProd, Integer valorProd, String descProd, Imagem imagem) {
		this.codProd = codProd;
		this.nomeProd = nomeProd;
		this.valorProd = valorProd;
		this.descProd = descProd;
		this.imagem = imagem;
	}

	// Getters and Setters itens_troca
	public List<ItemTroca> getItens_troca() {
		return itens_troca;
	}

	public void setItens_troca(List<ItemTroca> itens_troca) {
		this.itens_troca = itens_troca;
	}

	// Getters and Setters imagem
	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	// Cad_Produto
	public Set<CadProduto> getCad_produtos() {
		return cad_produtos;
	}

	public void setCad_produtos(Set<CadProduto> cad_produtos) {
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
