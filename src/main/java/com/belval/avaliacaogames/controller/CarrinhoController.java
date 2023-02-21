package com.belval.avaliacaogames.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.services.AnuncioService;

@Controller
public class CarrinhoController {

	@Autowired
	public AnuncioService anuncioService;

	public static List<Anuncio> cartAnuncio = new ArrayList<>();

	@GetMapping("/usuario/{cpf}/carrinho")
	public ModelAndView carrinho(@PathVariable("cpf") Long cpf, Model model) {

		model.addAttribute(cartAnuncio);

		ModelAndView mv = new ModelAndView("carrinho/carrinho");

		mv.addObject("cartAnuncio", cartAnuncio);

		return mv;
	}

	@PostMapping("/usuario/{cpf}/adicionar/produto/{codAnuncio}/carrinho")
	public ModelAndView adicionarCarrinho(@PathVariable("cpf") Long cpf, @PathVariable("codAnuncio") Long codAnuncio) {

		Anuncio anuncio = anuncioService.findById(codAnuncio);

		cartAnuncio.add(anuncio);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/carrinho");
		return mv;
	}
}
