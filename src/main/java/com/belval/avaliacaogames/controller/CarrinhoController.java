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
import com.belval.avaliacaogames.entities.Carrinho;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.CarrinhoRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.CarrinhoService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class CarrinhoController {

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CarrinhoService carrinhoService;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	// public static List<Anuncio> cartAnuncio = new ArrayList<>();

	@GetMapping("/usuario/{cpf}/carrinho")
	public ModelAndView carrinho(@PathVariable("cpf") Long cpf, Model model) {
		Usuario usuario = usuarioService.findById(cpf);

		List<Carrinho> carrinhos = carrinhoRepository.findByUsuario(usuario);
		// System.out.println(codAnuncio);
		List<Anuncio> anuncios = new ArrayList<>();

		for (Carrinho car : carrinhos) {
			Anuncio anuncio = anuncioService.findById(car.getCodAnuncio());
			anuncios.add(anuncio);
		}
		// Anuncio anuncios = anuncioService.findById(codAnuncio);

		// model.addAttribute(cartAnuncio);

		ModelAndView mv = new ModelAndView("carrinho/carrinho");

		mv.addObject("anuncios", anuncios);
		mv.addObject("carrinhos", carrinhos);

		return mv;
	}

	@PostMapping("/usuario/{cpf}/adicionar/produto/{codAnuncio}/carrinho")
	public ModelAndView adicionarCarrinho(@PathVariable("cpf") Long cpf, @PathVariable("codAnuncio") Long codAnuncio,
			Carrinho carrinho, Integer quantCarrinho) {

		Anuncio anuncio = anuncioService.findById(codAnuncio);
		Usuario usuario = usuarioService.findById(cpf);

		carrinho.setAnuncio(anuncio);
		carrinho.setUsuario(usuario);
		carrinho.setQuantCarrinho(quantCarrinho);

		carrinhoRepository.save(carrinho);
		// cartAnuncio.add(anuncio);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/carrinho");
		return mv;
	}
}
