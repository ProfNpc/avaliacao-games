package com.belval.avaliacaogames.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Troca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codTroca;
	private String nomeTroca;
	private String descTroca;
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

	// Ligação com tabela item_troca
	@JsonIgnore
	@OneToMany(mappedBy = "troca")
	private List<Item_Troca> itens_troca = new ArrayList<>();

	// Constructors
	public Troca() {
		super();
	}

	public Troca(Long codTroca, String nomeTroca, String descTroca, Boolean statusTroca, Usuario usuario,
			Cad_Produto cad_produto, String nomeImagem) {
		super();
		this.codTroca = codTroca;
		this.nomeTroca = nomeTroca;
		this.descTroca = descTroca;
		this.statusTroca = statusTroca;
		this.usuario = usuario;
		this.cad_produto = cad_produto;
		this.nomeImagem = nomeImagem;
	}

	// Getters and Setters cad_produto
	public Cad_Produto getCad_produto() {
		return cad_produto;
	}

	public void setCad_produto(Cad_Produto cad_produto) {
		this.cad_produto = cad_produto;
	}

	// Getters and Setters usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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