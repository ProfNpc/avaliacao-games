package com.belval.avaliacaogames.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;
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
}
