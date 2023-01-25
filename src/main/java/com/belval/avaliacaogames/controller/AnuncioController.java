package com.belval.avaliacaogames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AnuncioController {

	@GetMapping("usuario/{cpf}/anunciar")
	public String anunciar(@PathVariable("cpf") Long cpf, Model model) {
		model.addAttribute(cpf);
		
		return "anuncio/anuncio-venda";
	}
}
