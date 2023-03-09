package com.belval.avaliacaogames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.CarrinhoRepository;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.ItemCarrinhoRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.EnderecoService;
import com.belval.avaliacaogames.services.ItemCarrinhoService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class PedidoController {

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ItemCarrinhoService itemCarrinhoService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;

	@GetMapping("/usuario/{cpf}/compra/finalizar")
	public ModelAndView comprar(@PathVariable("cpf") Long cpf) {
		Usuario usuario = usuarioService.findById(cpf);

		Endereco endereco = enderecoService.findByUsuario(usuario);
		
		if (endereco.getCodEnd() == null) {
			ModelAndView mve = new ModelAndView("redirect:/usuario/{cpf}/cadastrar/endereco");
			return mve;
		}

		ModelAndView mv = new ModelAndView("compra/finalizar-compra");
		
		mv.addObject("endereco", endereco);
		
		return mv;
	}
}
