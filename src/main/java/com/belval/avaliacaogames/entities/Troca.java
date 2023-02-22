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
public class Troca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codTroca;
	private String nomeTroca;
	private String descTroca;
	private Double valorTroca;
	private Boolean statusTroca;
	private String nomeImagem;

	// Ligação com tabela Cad_Produto
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "cod_cad_produto")
	private Cad_Produto cad_produto;

	// Ligação com tabela usuario
	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	// Constructors
	public Troca() {
		super();
	}

	public Troca(Long codTroca, String nomeTroca, String descTroca, Double valorTroca, Boolean statusTroca,
			String nomeImagem) {
		super();
		this.codTroca = codTroca;
		this.nomeTroca = nomeTroca;
		this.descTroca = descTroca;
		this.valorTroca = valorTroca;
		this.statusTroca = statusTroca;
		this.nomeImagem = nomeImagem;
	}

	// Getters and Setters cad_produto
	public Cad_Produto getCad_produto() {
		return cad_produto;
	}

	public void setCad_produto(Cad_Produto cad_produto) {
		this.cad_produto = cad_produto;
	}

	// Getters and Setters
	public Long getCodTroca() {
		return codTroca;
	}

	public void setCodTroca(Long codTroca) {
		this.codTroca = codTroca;
	}

	public String getNomeTroca() {
		return nomeTroca;
	}

	public void setNomeTroca(String nomeTroca) {
		this.nomeTroca = nomeTroca;
	}

	public String getDescTroca() {
		return descTroca;
	}

	public void setDescTroca(String descTroca) {
		this.descTroca = descTroca;
	}

	public Double getValorTroca() {
		return valorTroca;
	}

	public void setValorTroca(Double valorTroca) {
		this.valorTroca = valorTroca;
	}

	public Boolean getStatusTroca() {
		return statusTroca;
	}

	public void setStatusTroca(Boolean statusTroca) {
		this.statusTroca = statusTroca;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	// HashCod
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codTroca == null) ? 0 : codTroca.hashCode());
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
		Troca other = (Troca) obj;
		if (codTroca == null) {
			if (other.codTroca != null)
				return false;
		} else if (!codTroca.equals(other.codTroca))
			return false;
		return true;
	}

}
