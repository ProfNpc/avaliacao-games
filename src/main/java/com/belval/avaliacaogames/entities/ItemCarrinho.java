package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemCarrinho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codItemCar;

	private Integer quantItemCar;

	@ManyToOne
	@JoinColumn(name = "codAnuncio")
	private Anuncio anuncio;

	@ManyToOne
	@JoinColumn(name = "codCarrinho")
	private Carrinho carrinho;

	public ItemCarrinho() {
		super();
	}

	public ItemCarrinho(Long codItemCar, Integer quantItemCar, Anuncio anuncio, Carrinho carrinho) {
		super();
		this.codItemCar = codItemCar;
		this.quantItemCar = quantItemCar;
		this.anuncio = anuncio;
		this.carrinho = carrinho;
	}

	// Getters and Setters carrinho
	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	// Getters and Setters anuncio

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	// Getters and Setters
	public Long getCodItemCar() {
		return codItemCar;
	}

	public void setCodItemCar(Long codItemCar) {
		this.codItemCar = codItemCar;
	}

	public Integer getQuantItemCar() {
		return quantItemCar;
	}

	public void setQuantItemCar(Integer quantItemCar) {
		this.quantItemCar = quantItemCar;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codItemCar);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarrinho other = (ItemCarrinho) obj;
		return Objects.equals(codItemCar, other.codItemCar);
	}

}
