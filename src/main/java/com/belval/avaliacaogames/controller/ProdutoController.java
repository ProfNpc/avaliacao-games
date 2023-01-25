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
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.Cad_ProdutoRepository;
import com.belval.avaliacaogames.repositories.ProdutoRepository;
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
	private Cad_ProdutoRepository cad_produtoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
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
		model.addAttribute(cpf);
		
		return "produto/adicionar-produto";
	}
	
	// Pesquisar
	@PostMapping("/usuario/{cpf}/biblioteca/adicionar")
	public ModelAndView adicionarProduto(@PathVariable("cpf") Long cpf, Model model, Produto produto) {
		model.addAttribute(cpf);
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		for (Produto pd : produtos) {
			String nomeProd = pd.getNomeProd();
			produto.equals(produtoService.findByNomeProd(nomeProd));

			produtos.add(produto);
		}
		
		model.addAttribute("produtos", produtos);
		
		ModelAndView mv = new ModelAndView("redirect:usuario/{cpf}/biblioteca/adicionar");
		//return "produto/adicionar-produto";
		return mv;
	}
	
	// Cadastar produto
	@GetMapping("/usuario/{cpf}/produto/cadastrar")
	public String cadastrarProduto(@PathVariable("cpf") Long cpf, Model model) {
		model.addAttribute(cpf);
		
		return "produto/cadastro-produto";
	}
	
	@PostMapping("/usuario/{cpf}/produto/cadastrar")
	public ModelAndView cadastrarProduto(Cad_Produto cad_produto, Produto produto,
										@PathVariable("cpf") Long cpf) {
		
		produtoRepository.save(produto);
		
		cad_produto.setUsuario(usuarioService.findById(cpf));
		cad_produto.setProduto(produto);
		cad_produtoRepository.save(cad_produto);
		
		ModelAndView model = new ModelAndView("redirect:/usuario/{cpf}/biblioteca");
		return model;
	}
	
	
	// Produto
	@GetMapping("/usuario/produto/{cod_prod}")
	public String printProduto(@PathVariable("cod_prod") Long cod_prod, Model model) {
		Produto produto = produtoService.findById(cod_prod);
		
		model.addAttribute("produto", produto);
		return "produto/produto";
	}
	
}
