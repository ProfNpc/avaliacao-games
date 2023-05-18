package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CadProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codCadProd;
	private Long quantidade;

	// Ligação com a tabela produto
	@ManyToOne
	@JoinColumn(name = "cod_produto")
	private Produto produto;

	// Ligação com a tabela usuario
	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	@OneToOne(mappedBy = "cadProduto", cascade = CascadeType.ALL)
	private Troca troca;

	// Constructor
	public CadProduto() {
	}

	public CadProduto(Long codCadProd, Produto produto, Long quantidade, Usuario usuario) {
		super();
		this.codCadProd = codCadProd;
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
	}

	// Produto
	public Long getProdutoID() {
		return produto.getCodProd();
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
	public Long getCodCadProd() {
		return codCadProd;
	}

	public void setCodCadProd(Long codCadProd) {
		this.codCadProd = codCadProd;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codCadProd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadProduto other = (CadProduto) obj;
		return Objects.equals(codCadProd, other.codCadProd);
	}
}
