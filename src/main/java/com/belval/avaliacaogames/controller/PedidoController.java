package com.belval.avaliacaogames.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Carrinho;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.ItemCarrinho;
import com.belval.avaliacaogames.entities.ItemPedido;
import com.belval.avaliacaogames.entities.Pagamento;
import com.belval.avaliacaogames.entities.Pedido;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.entities.pk.ItemPedidoPK;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.CarrinhoRepository;
import com.belval.avaliacaogames.repositories.ItemCarrinhoRepository;
import com.belval.avaliacaogames.repositories.ItemPedidoRepository;
import com.belval.avaliacaogames.repositories.PagamentoRepository;
import com.belval.avaliacaogames.repositories.PedidoRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.EnderecoService;
import com.belval.avaliacaogames.services.PedidoService;
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
	private PedidoService pedidoService;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ItemCarrinhoRepository itemCarrinhoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private AnuncioRepository anuncioRepository;

	private Pedido ped;
	private ItemPedido itemPed;

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
			Carrinho carrinho, ItemCarrinho itemCarrinho, Pedido pedido, ItemPedido itemPedido, Integer quantidade) {

		Usuario usuario = usuarioService.findById(cpf);

		Anuncio anuncio = anuncioService.findById(codAnuncio);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime instante = LocalDateTime.now();
		String instantePedido = instante.format(formatter);

		pedido.setStatusPedido("AGUARDANDO_PAGAMENTO");
		pedido.setUsuario(usuario);
		pedido.setDataPedido(instantePedido);
		pedidoRepository.save(pedido);

		ped = pedido;

		itemPedido.setAnuncio(anuncio);
		itemPedido.setPedido(pedido);
		itemPedido.setPreco(anuncio.getValorAnuncio());
		itemPedido.setQuantidade(quantidade);
		itemPedidoRepository.save(itemPedido);

		itemPed = itemPedido;

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/compra/finalizar");
		return mv;
	}

	// Tela para finalizar compra
	@GetMapping("/usuario/{cpf}/compra/finalizar")
	public ModelAndView comprar(@PathVariable("cpf") Long cpf, Integer quantidade) {
		Usuario usuario = usuarioService.findById(cpf);

		ModelAndView mv = new ModelAndView("compra/finalizar-compra");

		Endereco endereco = enderecoService.findByUsuario(usuario);

		if (endereco.getCodEnd() == null) {
			ModelAndView mve = new ModelAndView("usuario/perfil-geral");
			mve.addObject("alerta", "Cadastre seu endereço antes de finalizar a compra");
			mve.addObject("usuario", usuario);
			mve.addObject("endereco", enderecoService.findByUsuario(usuario));
			return mve;
		}

		if (ped != null) {
			Pedido pedido = pedidoService.findById(ped.getCodPedido());
			List<ItemPedido> itemPedido = itemPedidoRepository.findByIdPedido(pedido);

			int quant = 1;
			double total = 0;

			for (ItemPedido ip : itemPedido) {
				total = ip.getPreco();
			}

			mv.addObject("quant", quant);
			mv.addObject("total", total);
		} else {

			Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);
			List<ItemCarrinho> itensCarrinho = itemCarrinhoRepository.findByCarrinho(carrinho);

			int quant = itensCarrinho.size();

			double total = 0;
			for (ItemCarrinho item : itensCarrinho) {
				total += item.getAnuncio().getValorAnuncio() * item.getQuantidade();
			}

			mv.addObject("quant", quant);
			mv.addObject("total", total);
		}

		mv.addObject("endereco", endereco);

		return mv;
	}

	// Realizar a compra
	@PostMapping("/usuario/{cpf}/compra/finalizar")
	public ModelAndView terminarCompra(@PathVariable("cpf") Long cpf, Pedido pedido, ItemPedido item,
			Pagamento pagamento, @RequestParam("tipo") String pag) {
		Usuario usuario = usuarioService.findById(cpf);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/compras");

		if (ped != null) {

			pedido.setDataPedido(ped.getDataPedido());
			pedido.setStatusPedido("PAGAMENTO_EFETUADO");
			pedido.setUsuario(ped.getUsuario());
			pedido.setCodPedido(ped.getCodPedido());
			pedidoRepository.save(pedido);

			item.setAnuncio(itemPed.getAnuncio());
			item.setPedido(pedido);
			item.setPreco(itemPed.getPreco());
			item.setQuantidade(itemPed.getQuantidade());
			itemPedidoRepository.save(item);

			ped = null;
			itemPed = null;

		} else {

			Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);
			List<ItemCarrinho> itensCarrinho = itemCarrinhoRepository.findByCarrinho(carrinho);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime instante = LocalDateTime.now();
			String instantePedido = instante.format(formatter);

			pedido.setUsuario(usuario);
			pedido.setDataPedido(instantePedido);
			pedido.setStatusPedido("PAGAMENTO_EFETUADO");
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
		}
		
		List<ItemPedido> itensPedidos = itemPedidoRepository.findByIdPedido(pedido);
		
		for(ItemPedido it : itensPedidos) {
			Anuncio anuncio = anuncioService.findById(it.getAnuncio().getCodAnuncio());
			anuncio.setQuantVendida(anuncio.getQuantVendida() + it.getQuantidade());
			anuncio.setQuantAnuncio(anuncio.getQuantAnuncio() - it.getQuantidade());
			anuncioRepository.save(anuncio);
		}

		pagamento.setPedido(pedido);
		pagamento.setTipo(pag);
		pagamentoRepository.save(pagamento);

		return mv;
	}

	// Cancelar a compra do usuario
	@PostMapping("/usuario/{cpf}/compra/{id}/cancelar")
	public ModelAndView cancelarCompra(@PathVariable("cpf") Long cpf, @PathVariable("id") ItemPedidoPK id) {

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/compras");

		return mv;
	}
}
