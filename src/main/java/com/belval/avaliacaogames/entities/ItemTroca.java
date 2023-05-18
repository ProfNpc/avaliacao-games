package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemTroca implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codItemTroca;

	// Ligação com a tabela Troca
	@ManyToOne
	@JoinColumn(name = "codTroca")
	private Troca troca;

	// Ligação com a tabela Produto
	@ManyToOne
	@JoinColumn(name = "codProduto")
	private Produto produto;

	// Constructors
	public ItemTroca() {
		super();
	}

	public ItemTroca(Long codItemTroca, Troca troca, Produto produto) {
		super();
		this.codItemTroca = codItemTroca;
		this.troca = troca;
		this.produto = produto;

	}

	// Getters and Setters troca
	public Troca getTroca() {
		return troca;
	}

	public void setTroca(Troca troca) {
		this.troca = troca;
	}

	// Getters and Setters produto
	public Long getCodProduto() {
		return produto.getCodProd();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	// Getters and Setters
	public Long getCodItemTroca() {
		return codItemTroca;
	}

	public void setCodItemTroca(Long codItemTroca) {
		this.codItemTroca = codItemTroca;
	}

	// HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codItemTroca == null) ? 0 : codItemTroca.hashCode());
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
		ItemTroca other = (ItemTroca) obj;
		if (codItemTroca == null) {
			if (other.codItemTroca != null)
				return false;
		} else if (!codItemTroca.equals(other.codItemTroca))
			return false;
		return true;
	}

}
