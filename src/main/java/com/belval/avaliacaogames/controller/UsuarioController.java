package com.belval.avaliacaogames.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.model.Usuario;

@Controller
public class UsuarioController {
	
	// Armazena os dados
	private static List<Usuario> listaUsuario = new ArrayList<>();

	// Home
	@GetMapping("")
	public String indice() {
		return "home/home";
	}
	
	// 
	@GetMapping("/usuario/cadastrar")
	public String form() {
		return "usuario/cadastro";
	}
	
	@PostMapping("/usuario/cadastrar")
	public ModelAndView criado(Usuario usuario) {
		
		if (
				usuario.getPrimeiroNome().length() < 5
			||	usuario.getSobrenome().length() < 5
			|| 	(usuario.getEmail().length() < 5 || usuario.getEmail().indexOf('@') == -1)
			||	usuario.getCelular().length() < 5
			|| 	usuario.getSenha().length() < 5
			|| 	usuario.getGenero().length() < 2
		) {
			// Dados enviados com erros
			
		}
		
		ModelAndView model = new ModelAndView("redirect:/");
		
		listaUsuario.add(usuario);
		
		System.out.println(usuario.getSobrenome());
		
		model.addObject("usuario", usuario);
		
		return model;
	}
	
	/*
	@GetMapping("/usuario/perfil")
	public String verPerfil() {
		return "usuario/perfil-geral";
	}
	*/
	
	@GetMapping("/usuario/{nome}/perfil")
	public String perfil(
			@PathParam("nome") String nome, Model model, Usuario usuario) {
		
		
		if (usuario == null) {
			return "produto-nao-encontrado";
		}
		
		model.addAttribute("usuario", usuario);
		return "usuario/perfil-geral";
	}
	
}
