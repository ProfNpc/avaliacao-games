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
	private String comentario;
	private Double avaliacao;

	// Ligação com tabela usuario
	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "anuncio")
	private Anuncio anuncio;

	public Comentario() {
		super();
	}

	public Comentario(Long codComentario, String comentario, Double avaliacao, Usuario usuario) {
		super();
		this.codComentario = codComentario;
		this.comentario = comentario;
		this.avaliacao = avaliacao;
		this.usuario = usuario;
	}

	// Getters and Setters usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
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
