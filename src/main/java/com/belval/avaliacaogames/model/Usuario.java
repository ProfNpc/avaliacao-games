package com.belval.avaliacaogames.model;

import javax.persistence.Entity;

public class Usuario {
	private String primeiroNome;
	private String sobrenome;
	private String email;
	private String celular;
	private String senha;
	private String confirmarSenha;
	private String genero;
	
	
	
	public String getPrimeiroNome() {
		return primeiroNome;
	}



	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
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



	public Usuario(String primeiroNome, String sobrenome, String email, String celular, String senha, String genero) {
		this.primeiroNome = primeiroNome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.celular = celular;
		this.senha = senha;
		this.genero = genero;
	}
	
	
}
