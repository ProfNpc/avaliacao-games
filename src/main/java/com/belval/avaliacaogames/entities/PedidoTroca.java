package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PedidoTroca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codPedidoTroca;

	private String dataPedidoTroca;
	private Boolean statusPedidoTroca;

	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "id.pedidoTroca")
	private Set<ItemPedidoTroca> itens = new HashSet<>();

	public PedidoTroca() {
		super();
	}

	public PedidoTroca(Long codPedidoTroca, String dataPedidoTroca, Boolean statusPedidoTroca, Usuario usuario) {
		super();
		this.codPedidoTroca = codPedidoTroca;
		this.dataPedidoTroca = dataPedidoTroca;
		this.statusPedidoTroca = statusPedidoTroca;
		this.usuario = usuario;
	}

	// Getters and Setters Itens
	@SuppressWarnings("unused") // Caso der erro tirar
	private Set<ItemPedidoTroca> getItens() {
		return itens;
	}

	// Getters and Setters Usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getters and Setters
	public Long getCodPedidoTroca() {
		return codPedidoTroca;
	}

	public void setCodPedidoTroca(Long codPedidoTroca) {
		this.codPedidoTroca = codPedidoTroca;
	}

	public String getDataPedidoTroca() {
		return dataPedidoTroca;
	}

	public void setDataPedidoTroca(String dataPedidoTroca) {
		this.dataPedidoTroca = dataPedidoTroca;
	}

	public Boolean getStatusPedidoTroca() {
		return statusPedidoTroca;
	}

	public void setStatusPedidoTroca(Boolean statusPedidoTroca) {
		this.statusPedidoTroca = statusPedidoTroca;
	}
}
