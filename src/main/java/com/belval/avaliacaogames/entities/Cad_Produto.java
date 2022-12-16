package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cad_Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_cad_prod;
	
	// Ligação com a tabela produto
	@ManyToOne
	@JoinColumn(name = "cod_produto")
	private Produto produto;
	
	private Long quantidade;
	
	// Ligação com a tabela usuario
	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;
	
	private Boolean status;
	
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


	// Getters and Setters
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
