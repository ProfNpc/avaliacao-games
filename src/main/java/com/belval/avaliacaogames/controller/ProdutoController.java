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
import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.Cad_ProdutoRepository;
import com.belval.avaliacaogames.repositories.ProdutoRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.Cad_ProdutoService;
import com.belval.avaliacaogames.services.ProdutoService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class ProdutoController {

	@Autowired
	private Cad_ProdutoService cad_produtoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private Cad_ProdutoRepository cad_produtoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private AnuncioRepository anuncioRepository;

	// Pesquisar produto na tela inicial
	@PostMapping("/usuario/{cpf}/pesquisar")
	public ModelAndView pesquisar(@PathVariable("cpf") Long cpf, String nomeAnuncio) {

		ModelAndView mv = new ModelAndView("produto/produto-pesquisado");

		System.out.println(nomeAnuncio);
		List<Anuncio> anuncios = anuncioRepository.findByNomeAnuncioContainingIgnoreCase(nomeAnuncio);

		mv.addObject("anuncios", anuncios);

		return mv;
	}

	/*
	 * Pesquisar produto na tela inicial
	 * 
	 * @PostMapping("/pesquisar") public ModelAndView pesquisar(String nomeAnuncio)
	 * {
	 * 
	 * ModelAndView mv = new ModelAndView("produto/produto-pesquisado");
	 * 
	 * System.out.println(nomeAnuncio); List<Anuncio> anuncios =
	 * anuncioRepository.findByNomeAnuncioContainingIgnoreCase(nomeAnuncio);
	 * 
	 * mv.addObject("anuncios", anuncios);
	 * 
	 * return mv; }
	 */

	// Tela do produto
	@GetMapping("/usuario/{cpf}/produto/{codAnuncio}")
	public String printProduto(@PathVariable("codAnuncio") Long codAnuncio, @PathVariable("cpf") Long cpf,
			Model model) {

		List<Anuncio> anuncios = anuncioService.findAll();
		Anuncio anuncio = anuncioService.findById(codAnuncio);
		
		Long newCpf = anuncio.getCpfUsuario();
		Usuario usuario = usuarioService.findById(newCpf);

		model.addAttribute("usuario", usuario);
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("anuncios", anuncios);
		return "produto/produto";
	}

	// Biblioteca
	@GetMapping("/usuario/{cpf}/biblioteca")
	public String biblioteca(@PathVariable("cpf") Long cpf, Model model, Cad_Produto cad_prod, Usuario usu) {

		Usuario usuario = usuarioService.findById(cpf);

		List<Cad_Produto> cad_produtos = cad_produtoService.findByUsuario(usuario);
		List<Long> id_prods = new ArrayList<Long>();
		List<Produto> produtos = new ArrayList<Produto>();

		for (Cad_Produto cp : cad_produtos) {
			Long id = cp.getProdutoID();
			Produto prod = produtoService.findById(id);
			id_prods.add(id);
			produtos.add(prod);
		}

		model.addAttribute("cad_produtos", cad_produtos);
		model.addAttribute("produtos", produtos);

		return "produto/biblioteca-produto";
	}

	// Adicionar produto a biblioteca
	@GetMapping("/usuario/{cpf}/biblioteca/adicionar")
	public String adicionarProduto(@PathVariable("cpf") Long cpf, Model model) {
		// model.addAttribute(cpf);
		// model.addAttribute("produtos", new ArrayList<Produto>());

		return "produto/adicionar-produto";
	}

	// Pesquisar - Adicionar a biblioteca
	@PostMapping("/usuario/{cpf}/biblioteca/adicionar")
	public ModelAndView adicionarProduto(@PathVariable("cpf") Long cpf, String nomeProd, Model model) {

		ModelAndView mv = new ModelAndView("produto/adicionar-produto");

		List<Produto> produtos = produtoRepository.findByNomeProdContainingIgnoreCase(nomeProd);

		if (produtos.isEmpty()) {
			String nulo = "null";
			model.addAttribute("nulo", nulo);
		}

		mv.addObject("produtos", produtos);

		return mv;
	}

	// Cadastar produto na biblioteca
	@GetMapping("/usuario/{cpf}/biblioteca/cadastrar")
	public String cadastrarProduto(@PathVariable("cpf") Long cpf, Model model) {
		model.addAttribute(cpf);

		return "produto/cadastro-produto";
	}

	@PostMapping("/usuario/{cpf}/biblioteca/cadastrar")
	public ModelAndView cadastrarProduto(Cad_Produto cad_produto, Produto produto, @PathVariable("cpf") Long cpf) {

		produtoRepository.save(produto);

		cad_produto.setUsuario(usuarioService.findById(cpf));
		cad_produto.setProduto(produto);
		cad_produtoRepository.save(cad_produto);

		ModelAndView model = new ModelAndView("produto/biblioteca-produto");
		return model;
	}

	// Deletar produto na biblioteca
	@PostMapping("/usuario/{cpf}/biblioteca/deletar")
	public ModelAndView deletarProduto(@PathVariable("cpf") Long cpf, String nomeProd) {

		Usuario usuario = usuarioService.findById(cpf);

		List<Cad_Produto> cad_produtos = cad_produtoService.findByUsuario(usuario);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/biblioteca");
		return mv;
	}

}
