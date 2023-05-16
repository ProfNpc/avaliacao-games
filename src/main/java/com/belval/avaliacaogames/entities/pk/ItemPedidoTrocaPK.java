package com.belval.avaliacaogames.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.belval.avaliacaogames.entities.PedidoTroca;
import com.belval.avaliacaogames.entities.Produto;

@Embeddable
public class ItemPedidoTrocaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "codPedidoTroca")
	private PedidoTroca pedidoTroca;

	@ManyToOne
	@JoinColumn(name = "codProduto")
	private Produto produto;

	public PedidoTroca getPedidoTroca() {
		return pedidoTroca;
	}

	public void setPedidoTroca(PedidoTroca pedidoTroca) {
		this.pedidoTroca = pedidoTroca;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoTroca, produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoTrocaPK other = (ItemPedidoTrocaPK) obj;
		return Objects.equals(pedidoTroca, other.pedidoTroca) && Objects.equals(produto, other.produto);
	}
}
