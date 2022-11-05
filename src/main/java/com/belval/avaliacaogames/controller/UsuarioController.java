package com.belval.avaliacaogames.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.belval.avaliacaogames.model.Usuario;

@Controller
public class UsuarioController {
	
	private static List<Usuario> listaUsuarios = new ArrayList<>();

	@GetMapping("/usuario")
	public String novo() {
		return "usuario/form";
	}
	
	@PostMapping("/usuario/novo")
	public String criado(Usuario usuario, Model model) {
		listaUsuarios.add(usuario);
		
		System.out.println(usuario.getPrimeiroNome());
		System.out.println(usuario.getSobrenome());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getCelular());
		System.out.println(usuario.getSenha());
		System.out.println(usuario.getGenero());
		
		return "home/home";
	}
}
