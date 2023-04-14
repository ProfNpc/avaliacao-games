package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.belval.avaliacaogames.entities.pk.ItemPedidoTrocaPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedidoTroca implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPedidoTrocaPK id = new ItemPedidoTrocaPK();

	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	public ItemPedidoTroca() {
		super();
	}

	public ItemPedidoTroca(PedidoTroca pedidoTroca, Cad_Produto cad_produto, Usuario usuario) {
		super();
		id.setPedidoTroca(pedidoTroca);
		id.setCad_Produto(cad_produto);
		this.usuario = usuario;
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
	public Cad_Produto getCad_Produto() {
		return id.getCad_Produto();
	}

	public void setCad_Produto(Cad_Produto cad_produto) {
		id.setCad_Produto(cad_produto);
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
