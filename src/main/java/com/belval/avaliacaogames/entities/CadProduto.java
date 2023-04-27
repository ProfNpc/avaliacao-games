package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
public class CadProduto implements Serializable {
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

	@OneToOne(mappedBy = "cadProduto", cascade = CascadeType.ALL)
	private Troca troca;

	// Ligação com a tabela ItemPedidoTroca
	@OneToMany(mappedBy = "id.cad_produto")
	private Set<ItemPedidoTroca> itens = new HashSet<>();

	// Constructor
	public CadProduto() {
	}

	public CadProduto(Long codCadProd, Produto produto, Long quantidade, Usuario usuario, Boolean status) {
		super();
		this.codCadProd = codCadProd;
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.status = status;
	}

	// Getters and Setters itemPedidoTroca
	@JsonIgnore
	private Set<PedidoTroca> getPedidos() {
		Set<PedidoTroca> set = new HashSet<>();
		for (ItemPedidoTroca x : itens) {
			set.add(x.getPedidoTroca());
		}
		return set;
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
		CadProduto other = (CadProduto) obj;
		if (codCadProd == null) {
			if (other.codCadProd != null)
				return false;
		} else if (!codCadProd.equals(other.codCadProd))
			return false;
		return true;
	}

}