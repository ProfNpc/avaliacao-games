package com.belval.avaliacaogames.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.model.Usuario;
import com.belval.avaliacaogames.repositories.UsuarioRepository;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;
	
	// Armazena os dados
	private static List<Usuario> listaUsuario = new ArrayList<>();

	private static int next = 1;
	
	
	// Home
	@GetMapping("/")
	public String indice() {
		return "home/home";
	}
	
	// Mostra formulario
	@GetMapping("/usuario/cadastrar")
	public String form(Model model) {
		
		// testar
		model.addAttribute("usuario", new Usuario());
		
		return "usuario/cadastro";
	}
	
	@PostMapping("/usuario/cadastrar")
	public ModelAndView form(Usuario usuario) {
		
		ModelAndView model = new ModelAndView("redirect:/");
		
		repository.save(usuario);
		
		return model;
	}
	
	// TESTAR
	private void updateUsuario(Usuario usuario) {
		ListIterator<Usuario> i = listaUsuario.listIterator();
		while(i.hasNext()) {
			Usuario atual = i.next(); 
			if (atual.getNome() == usuario.getNome()) {
				i.set(usuario);
			}
		}
	}
	
	// Perfil do usuario
	@GetMapping("/usuario/{id}")
	public String perfil(
			@PathVariable("id") Long id, Model model) {
		
		Usuario usuario = service.findById(id);
		
		if (usuario == null) {
			return "usuario/usuario-nao-existe";
		}
		
		model.addAttribute("usuario", usuario);
		return "usuario/perfil-geral";
	}
	
	// Editar dados do usuario
	@GetMapping("/usuario/{id}/edit")
	public String edit(
			@PathVariable("id") Long id, Model model) {
		
		Usuario usuario = service.findById(id);
		
		if (usuario == null) {
			return "usuario/usuario-nao-existe";
		}
		
		model.addAttribute("usuario", usuario);
		return "usuario/perfil-geral-edit";
	}
	
	// Deletar conta
	@GetMapping("/usuario/{id}/deletar")
	public String delete(
			@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("id", id);
		return "usuario/deletar-conta";
	}
	
	// Confirmar deletar conta
	@PostMapping("/usuario/{id}/deletar")
	public String deleteConfirm(
			@PathVariable("id") Long id, Model model) {
		
		repository.deleteById(id);
		
		// Talvez essa não seja a maneira certa, aí tem que ver com o professor
		return indice();
	}
	
}
