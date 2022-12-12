package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cad_Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_cad_prod;
	private Long quantidade;
	private Boolean status;
	
	// Constructor
	public Cad_Produto() {
	}

	public Cad_Produto(Long cod_cad_prod, Long quantidade, Boolean status) {
		this.cod_cad_prod = cod_cad_prod;
		this.quantidade = quantidade;
		this.status = status;
	}

	// Getters and Setters
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
