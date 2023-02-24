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

import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Item_Troca;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.Cad_ProdutoRepository;
import com.belval.avaliacaogames.repositories.Item_TrocaRepository;
import com.belval.avaliacaogames.repositories.ProdutoRepository;
import com.belval.avaliacaogames.repositories.TrocaRepository;
import com.belval.avaliacaogames.services.Cad_ProdutoService;
import com.belval.avaliacaogames.services.ProdutoService;
import com.belval.avaliacaogames.services.TrocaService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class TrocaController {

	@Autowired
	private TrocaService trocaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private Cad_ProdutoService cad_ProdutoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private Cad_ProdutoRepository cad_ProdutoRepository;

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private Item_TrocaRepository item_TrocaRepository;

	// public static Troca troca;
	public List<Item_Troca> itens_troca = new ArrayList<>();

	public Troca trocas;

	@GetMapping("/usuario/{cpf}/trocas")
	public ModelAndView minhasTrocas(@PathVariable("cpf") Long cpf, Model model) {

		Usuario usuario = usuarioService.findById(cpf);

		List<Troca> trocas = trocaRepository.findByUsuario(usuario);

		ModelAndView mv = new ModelAndView("troca/minhas-trocas");

		mv.addObject("trocas", trocas);

		return mv;
	}

	// Abre a tela de pesquisar produto para troca
	@GetMapping("/usuario/{cpf}/trocar/{codCadProd}/pesquisar")
	public String pesquisarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			Troca troca) {

		Usuario usuario = usuarioService.findById(cpf);
		Cad_Produto cad_produto = cad_ProdutoService.findById(codCadProd);

		troca.setUsuario(usuario);
		troca.setCad_produto(cad_produto);
		trocaRepository.save(troca);
		
		trocas = troca;

		return "troca/troca-pesquisado";
	}

	// Pesquisar produtos
	@PostMapping("/usuario/{cpf}/trocar/{codCadProd}/pesquisar")
	public ModelAndView pesquisarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			String nomeProd) {

		ModelAndView mv = new ModelAndView("troca/troca-pesquisado");

		List<Produto> produtos = produtoRepository.findByNomeProdContainingIgnoreCase(nomeProd);

		mv.addObject("produtos", produtos);

		return mv;
	}

	// Adicionar produto
	@PostMapping("/usuario/{cpf}/trocar/{codCadProd}/adicionar/{codProd}")
	public ModelAndView adicionarItem_Troca(@PathVariable("cpf") Long cpf, @PathVariable("codProd") Long codProd,
			@PathVariable("codCadProd") Long codCadProd, Item_Troca item_troca) {

		Produto produto = produtoService.findById(codProd);

		item_troca.setProduto(produto);
		item_troca.setTroca(trocas);

		itens_troca.add(item_troca);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/trocar/{codCadProd}/pesquisar");

		return mv;
	}

	// Abre a tela para cadastrar um anuncio
	@GetMapping("/usuario/{cpf}/trocar/{codCadProd}/cadastrar")
	public String cadastrarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			Model model) {
		model.addAttribute(cpf);

		return "troca/anuncio-troca";
	}
}