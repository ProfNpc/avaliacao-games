package com.belval.avaliacaogames.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item_Troca {

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
	public Item_Troca() {
		super();
	}

	public Item_Troca(Long codItemTroca, Troca troca, Produto produto) {
		super();
		this.codItemTroca = codItemTroca;
		this.troca = troca;
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
		Item_Troca other = (Item_Troca) obj;
		if (codItemTroca == null) {
			if (other.codItemTroca != null)
				return false;
		} else if (!codItemTroca.equals(other.codItemTroca))
			return false;
		return true;
	}

}
