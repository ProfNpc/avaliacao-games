package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codComentario;
	private String comenComentario;
	private Double avalComentario;

	// Ligação com tabela usuario
	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "codAnuncio")
	private Anuncio anuncio;

	public Comentario() {
		super();
	}

	public Comentario(Long codComentario, String comenComentario, Double avalComentario, Usuario usuario,
			Anuncio anuncio) {
		super();
		this.codComentario = codComentario;
		this.comenComentario = comenComentario;
		this.avalComentario = avalComentario;
		this.usuario = usuario;
		this.anuncio = anuncio;
	}

	// Getters and Setters usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNomeUsuario() {
		return usuario.getNome();
	}

	// Getters and Setters anuncio
	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	// Getters and Setters
	public Long getCodComentario() {
		return codComentario;
	}

	public void setCodComentario(Long codComentario) {
		this.codComentario = codComentario;
	}

	public String getComenComentario() {
		return comenComentario;
	}

	public void setComenComentario(String comenComentario) {
		this.comenComentario = comenComentario;
	}

	public Double getAvalComentario() {
		return avalComentario;
	}

	public void setAvalComentario(Double avalComentario) {
		this.avalComentario = avalComentario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codComentario == null) ? 0 : codComentario.hashCode());
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
		Comentario other = (Comentario) obj;
		if (codComentario == null) {
			if (other.codComentario != null)
				return false;
		} else if (!codComentario.equals(other.codComentario))
			return false;
		return true;
	}

}
