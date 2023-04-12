package com.belval.avaliacaogames.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.belval.avaliacaogames.entities.PedidoTroca;
import com.belval.avaliacaogames.entities.Troca;

@Embeddable
public class ItemPedidoTrocaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "codPedidoTroca")
	private PedidoTroca pedidoTroca;

	@ManyToOne
	@JoinColumn(name = "codTroca")
	private Troca troca;

	public PedidoTroca getPedidoTroca() {
		return pedidoTroca;
	}

	public void setPedidoTroca(PedidoTroca pedidoTroca) {
		this.pedidoTroca = pedidoTroca;
	}

	public Troca getTroca() {
		return troca;
	}

	public void setTroca(Troca troca) {
		this.troca = troca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoTroca, troca);
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
		return Objects.equals(pedidoTroca, other.pedidoTroca) && Objects.equals(troca, other.troca);
	}
}
