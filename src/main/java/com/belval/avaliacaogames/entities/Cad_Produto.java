package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cad_Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codCadProd;
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

	/*
	 * Ligação com a tabela Anucio
	 * 
	 * @OneToOne(mappedBy = "cad_produto") private Anuncio anuncio;
	 */

	// Constructor
	public Cad_Produto() {
	}

	public Cad_Produto(Long codCadProd, Produto produto, Long quantidade, Usuario usuario, Boolean status) {
		super();
		this.codCadProd = codCadProd;
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.status = status;
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

	/*
	 * Anucio public Anuncio getAnuncio() { return anuncio; }
	 * 
	 * public void setAnuncio(Anuncio anuncio) { this.anuncio = anuncio; }
	 */

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCadProd == null) ? 0 : codCadProd.hashCode());
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
		Cad_Produto other = (Cad_Produto) obj;
		if (codCadProd == null) {
			if (other.codCadProd != null)
				return false;
		} else if (!codCadProd.equals(other.codCadProd))
			return false;
		return true;
	}

}
