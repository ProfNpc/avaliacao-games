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
import com.belval.avaliacaogames.repositories.Item_TrocaRepository;
import com.belval.avaliacaogames.repositories.ProdutoRepository;
import com.belval.avaliacaogames.repositories.TrocaRepository;
import com.belval.avaliacaogames.services.Cad_ProdutoService;
import com.belval.avaliacaogames.services.Item_TrocaService;
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
	private Item_TrocaService item_TrocaService;

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private Item_TrocaRepository item_TrocaRepository;

	// public static Troca troca;
	public List<Item_Troca> itens_troca = new ArrayList<>();

	public Troca trocas;

	// Tela de minhas trocas
	@GetMapping("/usuario/{cpf}/trocas")
	public ModelAndView minhasTrocas(@PathVariable("cpf") Long cpf, Model model) {

		Usuario usuario = usuarioService.findById(cpf);

		List<Troca> trocas = trocaRepository.findByUsuario(usuario);

		ModelAndView mv = new ModelAndView("troca/minhas-trocas");

		mv.addObject("trocas", trocas);

		return mv;
	}

	@GetMapping("/usuario/{cpf}/troca/{codTroca}")
	public String printTroca(@PathVariable("cpf") Long cpf, @PathVariable("codTroca") Long codTroca, Model model) {

		List<Troca> trocas = trocaService.findAll();
		Troca troca = trocaService.findById(codTroca);

		Usuario usuario = usuarioService.findById(troca.getCodUsuario());

		model.addAttribute("usuario", usuario);
		model.addAttribute("troca", troca);
		model.addAttribute("trocas", trocas);

		return "troca/troca";
	}

	// Abre a tela de pesquisar produto para troca
	@GetMapping("/usuario/{cpf}/trocar/{codCadProd}/pesquisar")
	public String pesquisarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			Troca troca) {

		Usuario usuario = usuarioService.findById(cpf);
		Cad_Produto cad_produto = cad_ProdutoService.findById(codCadProd);

		if (trocaService.findByCadProduto(cad_produto) == null) {
			troca.setUsuario(usuario);
			troca.setCad_produto(cad_produto);
			trocaRepository.save(troca);

			trocas = troca;
		}

		return "troca/troca-pesquisado";
	}

	// Pesquisar produtos de troca
	@PostMapping("/usuario/{cpf}/trocar/{codCadProd}/pesquisar")
	public ModelAndView pesquisarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			String nomeProd) {

		ModelAndView mv = new ModelAndView("troca/troca-pesquisado");

		List<Produto> produtos = produtoRepository.findByNomeProdContainingIgnoreCase(nomeProd);

		mv.addObject("produtos", produtos);

		return mv;
	}

	// Adicionar produto para troca
	@PostMapping("/usuario/{cpf}/trocar/{codCadProd}/adicionar/{codProd}")
	public ModelAndView adicionarItem_Troca(@PathVariable("cpf") Long cpf, @PathVariable("codProd") Long codProd,
			@PathVariable("codCadProd") Long codCadProd, Item_Troca item_troca) {

		Produto produto = produtoService.findById(codProd);
		Cad_Produto cadProduto = cad_ProdutoService.findById(codCadProd);
		Troca troca = trocaService.findByCadProduto(cadProduto);

		if (item_TrocaService.findByProdutoAndTroca(produto, troca) == null) {
			item_troca.setTroca(troca);
			item_troca.setProduto(produto);

			item_TrocaRepository.save(item_troca);
		}

		List<Item_Troca> itens = item_TrocaService.findByTroca(troca);
		ModelAndView mv = new ModelAndView("troca/troca-pesquisado");
		mv.addObject("itens", itens);

		return mv;
	}

	// Abre a tela para cadastrar uma troca
	@GetMapping("/usuario/{cpf}/trocar/{codCadProd}/cadastrar")
	public String cadastrarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			Model model) {

		Cad_Produto cad_produto = cad_ProdutoService.findById(codCadProd);
		Troca troca = trocaService.findByCadProduto(cad_produto);
		List<Item_Troca> itens = item_TrocaService.findByTroca(troca);

		model.addAttribute("cad_produto", cad_produto);
		model.addAttribute("itens", itens);

		return "troca/anuncio-troca";
	}

	@PostMapping("/usuario/{cpf}/trocar/{codCadProd}/cadastrar")
	public ModelAndView cadastroTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			Troca troca, Model model) {

		System.out.println("Cadastro");

		Cad_Produto cad_produto = cad_ProdutoService.findById(codCadProd);

		Troca novaTroca = trocaService.findByCadProduto(cad_produto);
		novaTroca.setNomeTroca(troca.getNomeTroca());
		novaTroca.setDescTroca(troca.getDescTroca());
		// novaTroca.setNomeImagem(troca.getNomeImagem());

		trocaRepository.save(novaTroca);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/trocas");
		return mv;
	}
}
