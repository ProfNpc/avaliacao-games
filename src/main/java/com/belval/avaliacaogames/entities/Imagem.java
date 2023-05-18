package com.belval.avaliacaogames.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Imagem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codImagem;
	private String name;
	private String type;
	private Long size;
	private String location;
	private String url;

	@OneToOne(mappedBy = "imagem", cascade = CascadeType.ALL)
	private Anuncio anuncio;

	@OneToOne(mappedBy = "imagem", cascade = CascadeType.ALL)
	private Produto produto;

	@OneToOne(mappedBy = "imagem", cascade = CascadeType.ALL)
	private Troca troca;

	@OneToOne(mappedBy = "imagem", cascade = CascadeType.ALL)
	private PedidoTroca pedidoTroca;

	public Imagem() {
		super();
	}

	public Imagem(Long codImagem, String name, String type, Long size, String location, String url) {
		super();
		this.codImagem = codImagem;
		this.name = name;
		this.type = type;
		this.size = size;
		this.location = location;
		this.url = url;
	}

	public Long getCodImagem() {
		return codImagem;
	}

	public void setCodImagem(Long codImagem) {
		this.codImagem = codImagem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
