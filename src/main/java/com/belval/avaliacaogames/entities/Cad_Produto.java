package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Cad_Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_cad_prod;
	private Long quantidade;
	private Boolean status;
	
	// Ligação com a tabela produto
	@ManyToOne
	@JoinColumn(name = "cod_produto")
	private Produto produto;
	
	// Ligação com a tabela usuario
	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;
	
	// Ligação com a tabela Anucio
	@OneToOne(mappedBy = "cad_produto")
	private Anuncio anuncio;
	
	
	// Constructor
	public Cad_Produto() {
	}
	
	
	public Cad_Produto(Long cod_cad_prod, Produto produto, Long quantidade, Usuario usuario, Boolean status) {
		super();
		this.cod_cad_prod = cod_cad_prod;
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.status = status;
	}

	// Produto
	public Long getProdutoID() {
		return produto.getCod_prod();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	// Usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	// Anucio
	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}


	// Getters and Setters
	public Long getCod_cad_prod() {
		return cod_cad_prod;
	}


	public void setCod_cad_prod(Long cod_cad_prod) {
		this.cod_cad_prod = cod_cad_prod;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
