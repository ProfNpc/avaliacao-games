package com.belval.avaliacaogames.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private String genero;

	// Ligação com tabela cad_produto
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Cad_Produto> cad_produto = new ArrayList<>();
	
	// Ligação com tabela endereco
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Endereco> enderecos = new ArrayList<>();
	
	//@ManyToMany
	//@JoinTable(name = "usuario_endereco", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "id_end"))
	//private Set<Endereco> enderecos = new HashSet<>();

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

	/*public Set<Endereco> getEnderecos() {
		return enderecos;
	}*/

	/*public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}*/

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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	// Metodos
	@Override
	public String toString() {
		return "Nome: " + nome + " " + sobrenome + ", email: " + email + ", celular: " + celular;
	}
}
