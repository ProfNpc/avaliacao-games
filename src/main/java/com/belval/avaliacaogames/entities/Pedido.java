package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codPedido;
	private Instant dataPedido;
	private String statusPedido;

	@ManyToOne
	@JoinColumn(name = "codUsuario")
	private Usuario usuario;

	public Pedido() {
		super();
	}

	public Pedido(Long codPedido, Instant dataPedido, String statusPedido, Usuario usuario) {
		super();
		this.codPedido = codPedido;
		this.dataPedido = dataPedido;
		this.statusPedido = statusPedido;
		this.usuario = usuario;
	}

	// Getters and Setters Usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getters and Setters
	public Long getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}

	public Instant getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Instant dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(codPedido, other.codPedido);
	}
}
