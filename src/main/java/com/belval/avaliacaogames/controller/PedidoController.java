package com.belval.avaliacaogames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Carrinho;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.ItemCarrinho;
import com.belval.avaliacaogames.entities.ItemPedido;
import com.belval.avaliacaogames.entities.Pedido;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.CarrinhoRepository;
import com.belval.avaliacaogames.repositories.ItemCarrinhoRepository;
import com.belval.avaliacaogames.repositories.ItemPedidoRepository;
import com.belval.avaliacaogames.repositories.PedidoRepository;
import com.belval.avaliacaogames.services.EnderecoService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class PedidoController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	// Tela de finalizar compra
	@GetMapping("/usuario/{cpf}/compra/finalizar")
	public ModelAndView comprar(@PathVariable("cpf") Long cpf) {
		Usuario usuario = usuarioService.findById(cpf);

		Endereco endereco = enderecoService.findByUsuario(usuario);

		if (endereco.getCodEnd() == null) {
			ModelAndView mve = new ModelAndView("redirect:/usuario/{cpf}/cadastrar/endereco");
			return mve;
		}

		Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);
		List<ItemCarrinho> itensCarrinho = itemCarrinhoRepository.findByCarrinho(carrinho);

		int quant = itensCarrinho.size();

		double total = 0;
		for (ItemCarrinho item : itensCarrinho) {
			total += item.getAnuncio().getValorAnuncio() * item.getQuantItemCar();
		}

		ModelAndView mv = new ModelAndView("compra/finalizar-compra");

		mv.addObject("endereco", endereco);
		mv.addObject("quant", quant);
		mv.addObject("total", total);

		return mv;
	}

	// Realiza a compra
	@PostMapping("/usuario/{cpf}/compra/finalizar")
	public ModelAndView terminarCompra(@PathVariable("cpf") Long cpf, Pedido pedido) {
		Usuario usuario = usuarioService.findById(cpf);

		Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);
		List<ItemCarrinho> itensCarrinho = itemCarrinhoRepository.findByCarrinho(carrinho);

		pedido.setUsuario(usuario);

		pedidoRepository.save(pedido);

		for (ItemCarrinho ic : itensCarrinho) {
			ItemPedido itemPedido = new ItemPedido();

			itemPedido.setAnuncio(ic.getAnuncio());
			itemPedido.setQuantidade(ic.getQuantItemCar());
			itemPedido.setPreco(ic.getAnuncio().getValorAnuncio());
			itemPedido.setPedido(pedido);

			itemPedidoRepository.save(itemPedido);

		}

		itemCarrinhoRepository.deleteAll(itensCarrinho);
		carrinhoRepository.delete(carrinho);

		ModelAndView mv = new ModelAndView("redirect:/home/{cpf}");

		return mv;
	}
}
