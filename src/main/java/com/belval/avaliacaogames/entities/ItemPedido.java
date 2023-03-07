package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.belval.avaliacaogames.entities.pk.ItemPedidoPK;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPedidoPK codItemPedido;

	private Integer quantidade;
	private Double preco;

	public ItemPedido() {
		super();
	}

	public ItemPedido(Pedido pedido, Anuncio anuncio, Integer quantidade, Double preco) {
		super();
		codItemPedido.setPedido(pedido);
		codItemPedido.setAnuncio(anuncio);
		this.quantidade = quantidade;
		this.preco = preco;
	}

	// Getters and Setters pedido
	public Pedido getPedido() {
		return codItemPedido.getPedido();
	}

	public void setPedido(Pedido pedido) {
		codItemPedido.setPedido(pedido);
	}

	// Getters and Setters anuncio
	public Anuncio getAnuncio() {
		return codItemPedido.getAnuncio();
	}

	public void setAnuncio(Anuncio anuncio) {
		codItemPedido.setAnuncio(anuncio);
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
		return Objects.hash(codItemPedido);
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
		return Objects.equals(codItemPedido, other.codItemPedido);
	}
}
