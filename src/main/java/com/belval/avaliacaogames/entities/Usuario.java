package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long cpf;
	private String nome;
	private String sobrenome;
	private String email;
	private String celular;
	private String senha;

	// Ligação com tabela cad_produto
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<CadProduto> cad_produto = new ArrayList<>();

	// Ligação com tabela endereco
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Endereco endereco;

	// Ligação com tabela anuncio
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Anuncio> anuncios = new ArrayList<>();

	// Ligação com tabela comentario
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<>();

	// Ligação com tabela Carrinho
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Carrinho carrinho;

	// Ligação com tabela Troca
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Troca> trocas = new ArrayList<>();

	// Ligação com tabela pedido
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Pedido> pedido = new ArrayList<>();

	// Ligação com tabela pedido troca
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<PedidoTroca> pedidoTroca = new ArrayList<>();

	// Ligação com tabela ItemPedidoTroca
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<ItemPedidoTroca> itemPedidoTroca = new ArrayList<>();

	// @ManyToMany
	// @JoinTable(name = "usuario_endereco", joinColumns = @JoinColumn(name =
	// "usuario_id"), inverseJoinColumns = @JoinColumn(name = "id_end"))
	// private Set<Endereco> enderecos = new HashSet<>();

	// Constructors
	public Usuario() {

	}

	public Usuario(Long cpf, String nome, String sobrenome, String email, String celular, String senha) {

		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.celular = celular;
		this.senha = senha;
	}

	// Getters and Setters pedido
	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	// Getter and Setters

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String primeiroNome) {
		this.nome = primeiroNome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Metodos
	@Override
	public String toString() {
		return "Nome: " + nome + " " + sobrenome + ", email: " + email + ", celular: " + celular;
	}
}
