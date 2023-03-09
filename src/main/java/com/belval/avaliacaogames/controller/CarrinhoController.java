package com.belval.avaliacaogames.controller;

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
import com.belval.avaliacaogames.entities.ItemCarrinho;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.CarrinhoRepository;
import com.belval.avaliacaogames.repositories.ItemCarrinhoRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.ItemCarrinhoService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class CarrinhoController {

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ItemCarrinhoService itemCarrinhoService;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;

	// public static List<Anuncio> cartAnuncio = new ArrayList<>();

	// Tela com todos os produtos no carrinho
	@GetMapping("/usuario/{cpf}/carrinho")
	public ModelAndView carrinho(@PathVariable("cpf") Long cpf, Model model, Integer quantCarrinho) {
		Usuario usuario = usuarioService.findById(cpf);

		Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);

		List<ItemCarrinho> itens = itemCarrinhoRepository.findByCarrinho(carrinho);

		ModelAndView mv = new ModelAndView("carrinho/carrinho");

		mv.addObject("itens", itens);

		return mv;
	}

	// Adiciona o produto ao carrinho
	@PostMapping("/usuario/{cpf}/adicionar/produto/{codAnuncio}/carrinho")
	public ModelAndView adicionarCarrinho(@PathVariable("cpf") Long cpf, @PathVariable("codAnuncio") Long codAnuncio,
			Carrinho carrinho, ItemCarrinho itemCarrinho, Integer quantItemCar) {

		Usuario usuario = usuarioService.findById(cpf);
		Anuncio anuncio = anuncioService.findById(codAnuncio);
		
		
		if (carrinhoRepository.findByUsuario(usuario) == null) {
			carrinho.setUsuario(usuario);
			carrinhoRepository.save(carrinho);
		} else {
			carrinho = carrinhoRepository.findByUsuario(usuario);
		}

		itemCarrinho.setAnuncio(anuncio);
		itemCarrinho.setQuantItemCar(quantItemCar);
		itemCarrinho.setCarrinho(carrinho);
		itemCarrinhoRepository.save(itemCarrinho);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/carrinho");
		return mv;
	}

	// Deleta o produto do carrinho
	@PostMapping("/usuario/{cpf}/deletar/produto/{codItemCar}/carrinho")
	public ModelAndView deletarCarrinho(@PathVariable("cpf") Long cpf, @PathVariable("codItemCar") Long codItemCar) {

		ItemCarrinho itemCarrinho = itemCarrinhoService.findById(codItemCar);

		itemCarrinhoRepository.delete(itemCarrinho);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/carrinho");
		return mv;
	}

	// Altera a quantidade do produto pelo carrinho
	@PostMapping("/usuario/{cpf}/carrinho/{codItemCar}/quantidade")
	public ModelAndView alteraQuantidade(@PathVariable("cpf") Long cpf, @PathVariable("codItemCar") Long codItemCar,
			Integer quantItemCar) {

		ItemCarrinho itemCarrinho = itemCarrinhoService.findById(codItemCar);
		itemCarrinho.setQuantItemCar(quantItemCar);
		itemCarrinhoRepository.save(itemCarrinho);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/carrinho");
		return mv;
	}

}
