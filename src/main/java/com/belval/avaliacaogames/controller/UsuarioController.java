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
		
		ModelAndView model = new ModelAndView("redirect:/usuario/perfil");
		
		listaUsuario.add(usuario);
		
		System.out.println(usuario.getSobrenome());
		
		model.addObject("usuario", usuario);
		
		return model;
	}
	
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
