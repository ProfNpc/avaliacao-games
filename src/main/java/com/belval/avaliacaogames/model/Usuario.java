package com.belval.avaliacaogames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	// Atributos de usuario
	private String nome;
	private String sobrenome;
	private String email;
	private String celular;
	private String senha;
	private String confirmarSenha;
	private String genero;
	
	
	// Constructors
	public Usuario(String primeiroNome, String sobrenome, String email, String celular, String senha, String genero) {
		this.nome = primeiroNome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.celular = celular;
		this.senha = senha;
		this.genero = genero;
	}
	
	public Usuario() {
		
	}


	// Getter and Setters
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
