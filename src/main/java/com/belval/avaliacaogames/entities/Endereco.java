package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codEnd;
	private String cepEnd;
	private Integer numEnd;
	private String ruaEnd;
	private String bairroEnd;
	private String cidadeEnd;
	private String estadoEnd;
	private String paisEnd;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	// @ManyToMany(mappedBy = "enderecos")
	// private Set<Usuario> usuarios = new HashSet<>();

	// Constructors
	public Endereco() {

	}

	public Endereco(Long codEnd, String cepEnd, Integer numEnd, String ruaEnd, String bairroEnd, String cidadeEnd,
			String estadoEnd, String paisEnd, Usuario usuario) {
		super();
		this.codEnd = codEnd;
		this.cepEnd = cepEnd;
		this.numEnd = numEnd;
		this.ruaEnd = ruaEnd;
		this.bairroEnd = bairroEnd;
		this.cidadeEnd = cidadeEnd;
		this.estadoEnd = estadoEnd;
		this.paisEnd = paisEnd;
		this.usuario = usuario;
	}

	// Getters and Setters

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getCodEnd() {
		return codEnd;
	}

	public void setCodEnd(Long codEnd) {
		this.codEnd = codEnd;
	}

	public String getCepEnd() {
		return cepEnd;
	}

	public void setCepEnd(String cepEnd) {
		this.cepEnd = cepEnd;
	}

	public Integer getNumEnd() {
		return numEnd;
	}

	public void setNumEnd(Integer numEnd) {
		this.numEnd = numEnd;
	}

	public String getRuaEnd() {
		return ruaEnd;
	}

	public void setRuaEnd(String ruaEnd) {
		this.ruaEnd = ruaEnd;
	}

	public String getBairroEnd() {
		return bairroEnd;
	}

	public void setBairroEnd(String bairroEnd) {
		this.bairroEnd = bairroEnd;
	}

	public String getCidadeEnd() {
		return cidadeEnd;
	}

	public void setCidadeEnd(String cidadeEnd) {
		this.cidadeEnd = cidadeEnd;
	}

	public String getEstadoEnd() {
		return estadoEnd;
	}

	public void setEstadoEnd(String estadoEnd) {
		this.estadoEnd = estadoEnd;
	}

	public String getPaisEnd() {
		return paisEnd;
	}

	public void setPaisEnd(String paisEnd) {
		this.paisEnd = paisEnd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEnd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(codEnd, other.codEnd);
	}

	// HashCode

}
