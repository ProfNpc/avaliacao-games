package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Anuncio implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codAnuncio;
	private String nomeAnuncio;
	private String descAnuncio;
	private Double valorAnuncio;
	private Integer quantAnuncio;
	private Boolean statusAnuncio;
	private String generoAnuncio;
	/*
	 * // Atributos de ligação com Cad_Produto
	 * 
	 * @JsonIgnore
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "cod_cad_produto") private Cad_Produto cad_produto;
	 */
	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	// Ligação com tabela comentario
	@JsonIgnore
	@OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<>();

	// Ligação com tabela carrinho
	@OneToOne(mappedBy = "anuncio", cascade = CascadeType.ALL)
	private Carrinho carrinho;

	// ligação com tabela imagem
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codImagem")
	private Imagem imagem;

	// Constructors
	public Anuncio() {
	}

	public Anuncio(Long codAnuncio, String nomeAnuncio, String descAnuncio, Double valorAnuncio, Integer quantAnuncio,
			Boolean statusAnuncio, Usuario usuario, Imagem imagem, String generoAnuncio) {
		this.codAnuncio = codAnuncio;
		this.nomeAnuncio = nomeAnuncio;
		this.descAnuncio = descAnuncio;
		this.valorAnuncio = valorAnuncio;
		this.quantAnuncio = quantAnuncio;
		this.statusAnuncio = statusAnuncio;
		this.usuario = usuario;
		this.imagem = imagem;
		this.generoAnuncio = generoAnuncio;
	}

	/*
	 * // Getters and Setters Cad_Produto public Cad_Produto getCad_produto() {
	 * return cad_produto; }
	 * 
	 * public void setCad_produto(Cad_Produto cad_produto) { this.cad_produto =
	 * cad_produto; }
	 */

	// Getters and Setters Imagem
	public Long getCodImagem() {
		return imagem.getCodImagem();
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	// Getters and Setters Usuario
	public Usuario getUsuario() {
		return usuario;
	}

	public Long getCpfUsuario() {
		return usuario.getCpf();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// Getters and Setters comentario
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	// Getters and Setters
	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	// Getters and Setters
	public Long getCodAnuncio() {
		return codAnuncio;
	}

	public void setCodAnuncio(Long codAnuncio) {
		this.codAnuncio = codAnuncio;
	}

	public String getNomeAnuncio() {
		return nomeAnuncio;
	}

	public void setNomeAnuncio(String nomeAnuncio) {
		this.nomeAnuncio = nomeAnuncio;
	}

	public String getDescAnuncio() {
		return descAnuncio;
	}

	public void setDescAnuncio(String descAnuncio) {
		this.descAnuncio = descAnuncio;
	}

	public Double getValorAnuncio() {
		return valorAnuncio;
	}

	public void setValorAnuncio(Double valorAnuncio) {
		this.valorAnuncio = valorAnuncio;
	}

	public Integer getQuantAnuncio() {
		return quantAnuncio;
	}

	public void setQuantAnuncio(Integer quantAnuncio) {
		this.quantAnuncio = quantAnuncio;
	}

	public Boolean getStatusAnuncio() {
		return statusAnuncio;
	}

	public void setStatusAnuncio(Boolean statusAnuncio) {
		this.statusAnuncio = statusAnuncio;
	}

	public String getGeneroAnuncio() {
		return generoAnuncio;
	}

	public void setGeneroAnuncio(String generoAnuncio) {
		this.generoAnuncio = generoAnuncio;
	}

	// Hash Code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAnuncio == null) ? 0 : codAnuncio.hashCode());
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
		Anuncio other = (Anuncio) obj;
		if (codAnuncio == null) {
			if (other.codAnuncio != null)
				return false;
		} else if (!codAnuncio.equals(other.codAnuncio))
			return false;
		return true;
	}
}
