package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
public class PedidoTroca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codPedidoTroca;

	private String dataPedidoTroca;
	private String statusRemetente;
	private String statusDestinatario;
	private String nomeTroca;

	@ManyToOne
	@JoinColumn(name = "cpf_usuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "id.pedidoTroca")
	private Set<ItemPedidoTroca> itens = new HashSet<>();

	// ligação com tabela imagem
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "codImagem")
	private Imagem imagem;

	public PedidoTroca() {
		super();
	}

	public PedidoTroca(Long codPedidoTroca, String dataPedidoTroca, String statusRemetente, String statusDestinatario,
			Usuario usuario) {
		super();
		this.codPedidoTroca = codPedidoTroca;
		this.dataPedidoTroca = dataPedidoTroca;
		this.statusRemetente = statusRemetente;
		this.statusDestinatario = statusDestinatario;
		this.usuario = usuario;
	}

	// Getters and Setters Imagem
	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	// Getters and Setters Troca
	public String getNomeTroca() {
		return nomeTroca;
	}

	public void setNomeTroca(String nomeTroca) {
		this.nomeTroca = nomeTroca;
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

	public String getStatusRemetente() {
		return statusRemetente;
	}

	public void setStatusRemetente(String statusRemetente) {
		this.statusRemetente = statusRemetente;
	}

	public String getStatusDestinatario() {
		return statusDestinatario;
	}

	public void setStatusDestinatario(String statusDestinatario) {
		this.statusDestinatario = statusDestinatario;
	}

	public void setItens(Set<ItemPedidoTroca> itens) {
		this.itens = itens;
	}

	// HashCode
	@Override
	public int hashCode() {
		return Objects.hash(codPedidoTroca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoTroca other = (PedidoTroca) obj;
		return Objects.equals(codPedidoTroca, other.codPedidoTroca);
	}
}
