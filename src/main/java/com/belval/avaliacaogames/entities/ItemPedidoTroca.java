package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.belval.avaliacaogames.entities.pk.ItemPedidoTrocaPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedidoTroca implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPedidoTrocaPK id = new ItemPedidoTrocaPK();

	public ItemPedidoTroca() {
		super();
	}

	public ItemPedidoTroca(PedidoTroca pedidoTroca, Troca troca) {
		super();
		id.setPedidoTroca(pedidoTroca);
		id.setTroca(troca);
	}

	// Getters and Setters pedidoTroca
	@JsonIgnore
	public PedidoTroca getPedidoTroca() {
		return id.getPedidoTroca();
	}

	public void setPedido(PedidoTroca pedidoTroca) {
		id.setPedidoTroca(pedidoTroca);
	}

	// Getters and Setters troca
	public Troca getTroca() {
		return id.getTroca();
	}

	public void setTroca(Troca troca) {
		id.setTroca(troca);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoTroca other = (ItemPedidoTroca) obj;
		return Objects.equals(id, other.id);
	}
}
