package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_end;
	private String cep_end;
	private Integer num_end;
	private String rua_end;
	private String bairro_end;
	private String cidade_end;
	private String estado_end;
	private String pais_end;

	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	// @ManyToMany(mappedBy = "enderecos")
	// private Set<Usuario> usuarios = new HashSet<>();

	// Constructors
	public Endereco() {

	}

	public Endereco(Long id_end, String cep_end, Integer num_end, String rua_end, String bairro_end, String cidade_end,
			String estado_end, String pais_end, Usuario usuario) {
		super();
		this.cep_end = cep_end;
		this.num_end = num_end;
		this.rua_end = rua_end;
		this.bairro_end = bairro_end;
		this.cidade_end = cidade_end;
		this.estado_end = estado_end;
		this.pais_end = pais_end;
		this.usuario = usuario;
	}

	// Getters and Setters

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId_end() {
		return id_end;
	}

	public void setId_end(Long id_end) {
		this.id_end = id_end;
	}

	public String getCep_end() {
		return cep_end;
	}

	public void setCep_end(String cep_end) {
		this.cep_end = cep_end;
	}

	public Integer getNum_end() {
		return num_end;
	}

	public void setNum_end(Integer num_end) {
		this.num_end = num_end;
	}

	public String getRua_end() {
		return rua_end;
	}

	public void setRua_end(String rua_end) {
		this.rua_end = rua_end;
	}

	public String getBairro_end() {
		return bairro_end;
	}

	public void setBairro_end(String bairro_end) {
		this.bairro_end = bairro_end;
	}

	public String getCidade_end() {
		return cidade_end;
	}

	public void setCidade_end(String cidade_end) {
		this.cidade_end = cidade_end;
	}

	public String getEstado_end() {
		return estado_end;
	}

	public void setEstado_end(String estado_end) {
		this.estado_end = estado_end;
	}

	public String getPais_end() {
		return pais_end;
	}

	public void setPais_end(String pais_end) {
		this.pais_end = pais_end;
	}

	// HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep_end == null) ? 0 : cep_end.hashCode());
		return result;
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
		if (cep_end == null) {
			if (other.cep_end != null)
				return false;
		} else if (!cep_end.equals(other.cep_end))
			return false;
		return true;
	}

}
