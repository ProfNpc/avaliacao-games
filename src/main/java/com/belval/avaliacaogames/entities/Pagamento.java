package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codPag;
	private Instant momPag;

	@OneToOne
	@MapsId
	private Pedido pedido;

	public Pagamento() {
		super();
	}

	public Pagamento(Long codPag, Instant momPag, Pedido pedido) {
		super();
		this.codPag = codPag;
		this.momPag = momPag;
		this.pedido = pedido;
	}

	// Getters and Setters pedido
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	// Getters and Setters 
	public Long getCodPag() {
		return codPag;
	}

	public void setCodPag(Long codPag) {
		this.codPag = codPag;
	}

	public Instant getMomPag() {
		return momPag;
	}

	public void setMomPag(Instant momPag) {
		this.momPag = momPag;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codPag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(codPag, other.codPag);
	}
}
