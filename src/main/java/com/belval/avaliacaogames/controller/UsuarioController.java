package com.belval.avaliacaogames.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.model.Usuario;

@Controller
public class UsuarioController {
	
	//Bla bla bla
	private static List<Usuario> listaUsuarios = new ArrayList<>();

	// Índice
	@GetMapping("")
	public String indice() {
		return "home/home";
	}
	
	@GetMapping("/usuario")
	public String form() {
		return "usuario/cadastro";
	}
	
	@PostMapping("/usuario/cadastrar")
	public ModelAndView criado(Usuario usuario) {
		
		ModelAndView model = new ModelAndView("redirect:");
		
		listaUsuarios.add(usuario);
		
		model.addObject("usuario", usuario);
		
		System.out.println(usuario.getPrimeiroNome());
		System.out.println(usuario.getSobrenome());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getCelular());
		System.out.println(usuario.getSenha());
		System.out.println(usuario.getGenero());
		
		return model;
	}
	
	
}
