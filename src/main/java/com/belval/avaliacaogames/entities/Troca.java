package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Troca implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codTroca;
	private String nomeTroca;
	private String descTroca;
	private Boolean statusTroca;

	// Ligação com tabela Cad_Produto
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "cod_cad_produto")
	private CadProduto cadProduto;

	// Ligação com tabela usuario
	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	// Ligação com tabela item_troca
	@JsonIgnore
	@OneToMany(mappedBy = "troca", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemTroca> itens_troca = new ArrayList<>();

	// ligação com tabela imagem
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "codImagem")
	private Imagem imagem;

	// Constructors
	public Troca() {
		super();
	}

	public Troca(Long codTroca, String nomeTroca, String descTroca, Boolean statusTroca, Usuario usuario,
			CadProduto cad_produto, Imagem imagem) {
		super();
		this.codTroca = codTroca;
		this.nomeTroca = nomeTroca;
		this.descTroca = descTroca;
		this.statusTroca = statusTroca;
		this.usuario = usuario;
		this.cadProduto = cad_produto;
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

	// Getters and Setters cad_produto
	public CadProduto getCad_produto() {
		return cadProduto;
	}

	public void setCad_produto(CadProduto cad_produto) {
		this.cadProduto = cad_produto;
	}

	// Getters and Setters usuario

	public Long getCodUsuario() {
		return usuario.getCpf();
	}

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
