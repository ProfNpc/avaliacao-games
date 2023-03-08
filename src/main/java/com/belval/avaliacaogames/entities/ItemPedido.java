package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.belval.avaliacaogames.entities.pk.ItemPedidoPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Integer quantidade;
	private Double preco;

	public ItemPedido() {
		super();
	}

	public ItemPedido(Pedido pedido, Anuncio anuncio, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setAnuncio(anuncio);
		this.quantidade = quantidade;
		this.preco = preco;
	}

	// Getters and Setters pedido
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	// Getters and Setters anuncio
	public Anuncio getAnuncio() {
		return id.getAnuncio();
	}

	public void setAnuncio(Anuncio anuncio) {
		id.setAnuncio(anuncio);
	}

	// Getters and Setters
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
}
