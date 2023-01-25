package com.belval.avaliacaogames.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class AnuncioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AnuncioService anuncioService;

	// Tela com anuncios do usuario
	@GetMapping("/usuario/{cpf}/anuncios")
	public String meusAnuncios(@PathVariable("cpf") Long cpf, Model model) {
		model.addAttribute(cpf);
		
		Usuario usuario = usuarioService.findById(cpf);
		
		List<Anuncio> anuncios = anuncioService.findByUsuario(usuario);
		List<Anuncio> anuncio = new ArrayList<Anuncio>();
		
		
		for(Anuncio an : anuncios) {
			Long id = an.getCodAnuncio();
			Anuncio anun = anuncioService.findById(id);
			anuncio.add(anun);
		}
		
		model.addAttribute("anuncio", anuncio);
		
		return "anuncio/meus-anuncios";
	}
	
	@GetMapping("/usuario/{cpf}/anunciar")
	public String anunciar(@PathVariable("cpf") Long cpf, Model model) {
		model.addAttribute(cpf);
		
		return "anuncio/anuncio-venda";
	}
	

	
}
