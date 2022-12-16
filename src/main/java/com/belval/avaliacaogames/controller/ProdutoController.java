package com.belval.avaliacaogames.controller;

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
		Cad_Produto cad_produto = cad_produtoService.findByUsuario(usuario);
		
		Long id_prod = cad_produto.getProdutoID();
		
		Produto produto = produtoService.findById(id_prod);
		
		if(cad_produto.getUsuario().equals(usuario)) {
			
			//if()
			model.addAttribute("cad_produto", cad_produto);
			model.addAttribute("produto", produto);
			return "produto/biblioteca-produto";
			
		}
		return "produto/biblioteca-produto2-sem-cadastro";
		
	}
}
