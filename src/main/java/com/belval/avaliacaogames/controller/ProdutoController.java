package com.belval.avaliacaogames.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Comentario;
import com.belval.avaliacaogames.entities.Imagem;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.CadProdutoRepository;
import com.belval.avaliacaogames.repositories.ComentarioRepository;
import com.belval.avaliacaogames.repositories.ProdutoRepository;
import com.belval.avaliacaogames.repositories.TrocaRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.CadProdutoService;
import com.belval.avaliacaogames.services.ImagemService;
import com.belval.avaliacaogames.services.ProdutoService;
import com.belval.avaliacaogames.services.TrocaService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class ProdutoController {

	@Autowired
	private CadProdutoService cadProdutoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private ImagemService imagemService;

	@Autowired
	private TrocaService trocaService;

	@Autowired
	private CadProdutoRepository cadProdutoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private TrocaRepository trocaRepository;

	// Pesquisar produto na tela inicial
	@PostMapping("/usuario/{cpf}/pesquisar")
	public ModelAndView pesquisar(@PathVariable("cpf") Long cpf, String nomeAnuncio) {

		ModelAndView mv = new ModelAndView("produto/produto-pesquisado");

		List<Anuncio> anuncios = anuncioRepository.findByNomeAnuncioContainingIgnoreCaseAndUsuarioCpfNot(nomeAnuncio,
				cpf);

		mv.addObject("anuncios", anuncios);
		mv.addObject("nomeAnuncio", nomeAnuncio);

		return mv;
	}

	// Tela do produto
	@GetMapping("/usuario/{cpf}/produto/{codAnuncio}")
	public String printProduto(@PathVariable("codAnuncio") Long codAnuncio, @PathVariable("cpf") Long cpf,
			Model model) {

		List<Anuncio> anuncios = anuncioService.findAll();
		Anuncio anuncio = anuncioService.findById(codAnuncio);

		// Long newCpf = anuncio.getCpfUsuario();
		Usuario usuario = usuarioService.findById(anuncio.getCpfUsuario());

		List<Comentario> comentarios = comentarioRepository.findByAnuncio(anuncio);
		List<String> nomeComentarios = new ArrayList<>();

		for (Comentario cm : comentarios) {
			nomeComentarios.add(cm.getNomeUsuario());
		}
		
		boolean travarBotao = (anuncio.getQuantAnuncio() <= 0);

		model.addAttribute("nomeComentarios", nomeComentarios);
		model.addAttribute("comentarios", comentarios);
		model.addAttribute("usuario", usuario);
		model.addAttribute("anuncio", anuncio);
		model.addAttribute("anuncios", anuncios);
		model.addAttribute("travarBotao", travarBotao);
		return "produto/produto";
	}

	// Biblioteca
	@GetMapping("/usuario/{cpf}/biblioteca")
	public String biblioteca(@PathVariable("cpf") Long cpf, Model model, CadProduto cad_prod, Usuario usu) {

		Usuario usuario = usuarioService.findById(cpf);

		List<CadProduto> cad_produtos = cadProdutoService.findByUsuario(usuario);
		List<Long> id_prods = new ArrayList<Long>();
		List<Produto> produtos = new ArrayList<Produto>();

		for (CadProduto cp : cad_produtos) {
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

	// Adicionar produto na biblioteca
	@PostMapping("/usuario/{cpf}/biblioteca/{codProd}/adicionar")
	public ModelAndView adicionarProduto(@PathVariable("cpf") Long cpf, @PathVariable("codProd") Long codProd,
			CadProduto cad_produto) {

		Usuario usuario = usuarioService.findById(cpf);
		Produto produto = produtoService.findById(codProd);

		CadProduto cadProdutoNoBd = cadProdutoService.findByUsuarioAndProduto(usuario, produto);
		if (cadProdutoNoBd == null) {
			cad_produto.setUsuario(usuario);
			cad_produto.setProduto(produto);

			cadProdutoRepository.save(cad_produto);
		} else {
			cadProdutoNoBd.setQuantidade(cadProdutoNoBd.getQuantidade() + cad_produto.getQuantidade());
			cadProdutoRepository.save(cadProdutoNoBd);
		}

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/biblioteca");
		return mv;
	}

	// Cadastar produto na biblioteca
	@GetMapping("/usuario/{cpf}/biblioteca/cadastrar")
	public String cadastrarProduto(@PathVariable("cpf") Long cpf, Model model) {
		model.addAttribute(cpf);

		return "produto/cadastro-produto";
	}

	// Cadastar produto na biblioteca
	@PostMapping("/usuario/{cpf}/biblioteca/cadastrar")
	public ModelAndView cadastrarProduto(CadProduto cad_produto, Produto produto, @PathVariable("cpf") Long cpf,
			@RequestParam("file") MultipartFile file) {

		Imagem imagem = null;

		try {
			imagem = imagemService.upload(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		produto.setImagem(imagem);

		produtoRepository.save(produto);

		cad_produto.setUsuario(usuarioService.findById(cpf));
		cad_produto.setProduto(produto);
		cadProdutoRepository.save(cad_produto);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/biblioteca");
		return mv;
	}

	// Deletar produto na biblioteca
	@PostMapping("/usuario/{cpf}/biblioteca/{codCadProd}/deletar")
	public ModelAndView deletarProduto(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd) {

		CadProduto cadProduto = cadProdutoService.findById(codCadProd);

		Troca troca = trocaService.findByCadProduto(cadProduto);

		if (troca != null) {
			troca.setCad_produto(null);
			troca.setStatusTroca(false);
			trocaRepository.save(troca);
		}
		cadProdutoRepository.deleteById(codCadProd);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/biblioteca");

		return mv;
	}

}
