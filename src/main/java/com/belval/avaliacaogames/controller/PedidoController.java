package com.belval.avaliacaogames.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Carrinho;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.ItemCarrinho;
import com.belval.avaliacaogames.entities.ItemPedido;
import com.belval.avaliacaogames.entities.Pedido;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.entities.pk.ItemPedidoPK;
import com.belval.avaliacaogames.repositories.CarrinhoRepository;
import com.belval.avaliacaogames.repositories.ItemCarrinhoRepository;
import com.belval.avaliacaogames.repositories.ItemPedidoRepository;
import com.belval.avaliacaogames.repositories.PedidoRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.EnderecoService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class PedidoController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	// Mostra todas as compras do usuario
	@GetMapping("/usuario/{cpf}/compras")
	public ModelAndView minhasCompras(@PathVariable("cpf") Long cpf) {
		Usuario usuario = usuarioService.findById(cpf);

		List<Pedido> pedidos = pedidoRepository.findByUsuario(usuario);
		List<ItemPedido> itensPedido = new ArrayList<>();

		for (Pedido p : pedidos) {
			List<ItemPedido> ip = itemPedidoRepository.findByIdPedido(p);
			itensPedido.addAll(ip);
		}

		ModelAndView mv = new ModelAndView("compra/minhas-compras");
		mv.addObject("itensPedido", itensPedido);

		return mv;
	}

	@PostMapping("/usuario/{cpf}/compra/{codAnuncio}/adicionar")
	public ModelAndView adicionarCompra(@PathVariable("cpf") Long cpf, @PathVariable("codAnuncio") Long codAnuncio,
			Carrinho carrinho, ItemCarrinho itemCarrinho, Integer quantidade) {

		Usuario usuario = usuarioService.findById(cpf);

		Anuncio anuncio = anuncioService.findById(codAnuncio);

		if (carrinhoRepository.findByUsuario(usuario) == null) {
			carrinho.setUsuario(usuario);
			carrinhoRepository.save(carrinho);
		} else {
			carrinho = carrinhoRepository.findByUsuario(usuario);
		}

		itemCarrinho.setAnuncio(anuncio);
		itemCarrinho.setCarrinho(carrinho);
		itemCarrinho.setQuantidade(quantidade);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/compra/finalizar");
		return mv;
	}

	// Tela para finalizar compra
	@GetMapping("/usuario/{cpf}/compra/finalizar")
	public ModelAndView comprar(@PathVariable("cpf") Long cpf, Integer quantidade) {
		Usuario usuario = usuarioService.findById(cpf);

		System.out.println(quantidade);

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
			total += item.getAnuncio().getValorAnuncio() * item.getQuantidade();
		}

		ModelAndView mv = new ModelAndView("compra/finalizar-compra");

		mv.addObject("endereco", endereco);
		mv.addObject("quant", quant);
		mv.addObject("total", total);

		return mv;
	}

	// Realizar a compra
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
			itemPedido.setQuantidade(ic.getQuantidade());
			itemPedido.setPreco(ic.getAnuncio().getValorAnuncio());
			itemPedido.setPedido(pedido);

			itemPedidoRepository.save(itemPedido);

		}

		itemCarrinhoRepository.deleteAll(itensCarrinho);
		carrinhoRepository.delete(carrinho);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/compras");

		return mv;
	}

	// Cancelar a compra do usuario
	@PostMapping("/usuario/{cpf}/compra/{id}/cancelar")
	public ModelAndView cancelarCompra(@PathVariable("cpf") Long cpf, @PathVariable("id") ItemPedidoPK id) {

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/compras");

		return mv;
	}
}
