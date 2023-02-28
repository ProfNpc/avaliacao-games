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

	// Tela com todos os produtos no carrinho
	@GetMapping("/usuario/{cpf}/carrinho")
	public ModelAndView carrinho(@PathVariable("cpf") Long cpf, Model model, Integer quantCarrinho) {
		Usuario usuario = usuarioService.findById(cpf);

		List<Carrinho> carrinhos = carrinhoRepository.findByUsuario(usuario);

		List<Anuncio> anuncios = new ArrayList<>();

		for (Carrinho car : carrinhos) {
			Anuncio anuncio = anuncioService.findById(car.getCodAnuncio());

			anuncios.add(anuncio);
		}

		System.out.println(quantCarrinho);

		// model.addAttribute(cartAnuncio);

		ModelAndView mv = new ModelAndView("carrinho/carrinho");

		mv.addObject("anuncios", anuncios);
		mv.addObject("carrinhos", carrinhos);

		return mv;
	}

	// Adiciona o produto ao carrinho
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

	// Deleta o produto do carrinho
	@PostMapping("/usuario/{cpf}/deletar/produto/{codCarrinho}/carrinho")
	public ModelAndView deletarCarrinho(@PathVariable("cpf") Long cpf, @PathVariable("codCarrinho") Long codCarrinho) {

		Carrinho carrinho = carrinhoService.findById(codCarrinho);

		carrinhoRepository.delete(carrinho);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/carrinho");
		return mv;
	}

	// Altera a quantidade do produto pelo carrinho
	@PostMapping("/usuario/{cpf}/carrinho/{codCarrinho}/quantidade")
	public ModelAndView alteraQuantidade(@PathVariable("cpf") Long cpf, @PathVariable("codCarrinho") Long codCarrinho,
			Integer quantCarrinho) {

		Carrinho carrinho = carrinhoService.findById(codCarrinho);

		carrinho.setQuantCarrinho(quantCarrinho);
		carrinhoRepository.save(carrinho);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/carrinho");
		return mv;
	}

}
