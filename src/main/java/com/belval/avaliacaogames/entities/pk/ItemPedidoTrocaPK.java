package com.belval.avaliacaogames.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.PedidoTroca;

@Embeddable
public class ItemPedidoTrocaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "codPedidoTroca")
	private PedidoTroca pedidoTroca;

	@ManyToOne
	@JoinColumn(name = "codCadProduto")
	private Cad_Produto cad_produto;

	public PedidoTroca getPedidoTroca() {
		return pedidoTroca;
	}

	public void setPedidoTroca(PedidoTroca pedidoTroca) {
		this.pedidoTroca = pedidoTroca;
	}

	public Cad_Produto getCad_Produto() {
		return cad_produto;
	}

	public void setCad_Produto(Cad_Produto cad_produto) {
		this.cad_produto = cad_produto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoTroca, cad_produto);
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
		return Objects.equals(pedidoTroca, other.pedidoTroca) && Objects.equals(cad_produto, other.cad_produto);
	}
}
