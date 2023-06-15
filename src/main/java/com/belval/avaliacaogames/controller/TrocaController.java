package com.belval.avaliacaogames.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Imagem;
import com.belval.avaliacaogames.entities.ItemPedidoTroca;
import com.belval.avaliacaogames.entities.ItemTroca;
import com.belval.avaliacaogames.entities.PedidoTroca;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.CadProdutoRepository;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.ItemPedidoTrocaRepository;
import com.belval.avaliacaogames.repositories.ItemTrocaRepository;
import com.belval.avaliacaogames.repositories.PedidoTrocaRepository;
import com.belval.avaliacaogames.repositories.ProdutoRepository;
import com.belval.avaliacaogames.repositories.TrocaRepository;
import com.belval.avaliacaogames.services.CadProdutoService;
import com.belval.avaliacaogames.services.EnderecoService;
import com.belval.avaliacaogames.services.ImagemService;
import com.belval.avaliacaogames.services.ItemTrocaService;
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
	private CadProdutoService cadProdutoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemTrocaService itemTrocaService;

	@Autowired
	private ImagemService imagemService;

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemTrocaRepository itemTrocaRepository;

	@Autowired
	private CadProdutoRepository cadProdutoRepository;

	@Autowired
	private PedidoTrocaRepository pedidoTrocaRepository;

	@Autowired
	private ItemPedidoTrocaRepository itemPedidoTrocaRepository;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	// Formatador de data e hora
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	// public static Troca troca;
	public List<ItemTroca> itens_troca = new ArrayList<>();

	public Troca trocas;

	// Tela de minhas trocas
	@GetMapping("/usuario/{cpf}/trocas")
	public ModelAndView minhasTrocas(@PathVariable("cpf") Long cpf, Model model) {

		List<Troca> trocas = trocaRepository.findAllByUsuarioCpfAndStatusTrocaTrue(cpf);

		ModelAndView mv = new ModelAndView("troca/minhas-trocas");

		mv.addObject("trocas", trocas);

		return mv;
	}

	// Tela de anuncio de troca
	@GetMapping("/usuario/{cpf}/troca/{codTroca}")
	public String printTroca(@PathVariable("cpf") Long cpf, @PathVariable("codTroca") Long codTroca, Model model) {

		List<Troca> trocas = trocaService.findAll();
		Troca troca = trocaService.findById(codTroca);

		Usuario usuario = usuarioService.findById(troca.getCodUsuario());
		Usuario usuarioAtual = usuarioService.findById(cpf);

		List<ItemTroca> itens_troca = itemTrocaService.findByTroca(troca);

		// Cria a lista dos produtos que o usuário tem
		List<Produto> produtosUsuario = new ArrayList<Produto>();
		for (CadProduto cad : cadProdutoService.findByUsuario(usuarioAtual)) {
			produtosUsuario.add(cad.getProduto());
		}

		// Cria a lista dos produtos da troca
		List<Produto> produtosTroca = new ArrayList<Produto>();
		for (ItemTroca item : itens_troca) {
			produtosTroca.add(item.getProduto());
		}

		// Compara as duas listas
		boolean podeTrocar = true;
		for (Produto produtoTroca : produtosTroca) {
			boolean usuarioPossui = false;

			for (Produto produtoUsuario : produtosUsuario) {
				if (produtoTroca.equals(produtoUsuario)) {
					usuarioPossui = true;
					break;
				}
			}

			if (!usuarioPossui) {
				podeTrocar = false;
				break;
			}
		}

		model.addAttribute("itens_troca", itens_troca);
		model.addAttribute("usuario", usuario);
		model.addAttribute("troca", troca);
		model.addAttribute("trocas", trocas);
		model.addAttribute("produtosUsuario", produtosUsuario);
		model.addAttribute("naoPodeTrocar", !podeTrocar);

		return "troca/troca";
	}

	// Abre a tela de pesquisar produto para trocar
	@GetMapping("/usuario/{cpf}/trocar/{codCadProd}/pesquisar")
	public String pesquisarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			Troca troca) {

		Usuario usuario = usuarioService.findById(cpf);
		CadProduto cad_produto = cadProdutoService.findById(codCadProd);

		if (trocaService.findByCadProduto(cad_produto) == null) {
			troca.setUsuario(usuario);
			troca.setCad_produto(cad_produto);
			trocaRepository.save(troca);

			trocas = troca;
		}

		return "troca/troca-pesquisado";
	}

	// Pesquisar produtos para trocar
	@PostMapping("/usuario/{cpf}/trocar/{codCadProd}/pesquisar")
	public ModelAndView pesquisarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			String nomeProd) {

		ModelAndView mv = new ModelAndView("troca/troca-pesquisado");

		List<Produto> produtos = produtoRepository.findByNomeProdContainingIgnoreCase(nomeProd);

		mv.addObject("produtos", produtos);

		return mv;
	}

	// Adicionar produto para trocar
	@PostMapping("/usuario/{cpf}/trocar/{codCadProd}/adicionar/{codProd}")
	public ModelAndView adicionarItem_Troca(@PathVariable("cpf") Long cpf, @PathVariable("codProd") Long codProd,
			@PathVariable("codCadProd") Long codCadProd, ItemTroca item_troca) {

		Produto produto = produtoService.findById(codProd);
		CadProduto cadProduto = cadProdutoService.findById(codCadProd);
		Troca troca = trocaService.findByCadProduto(cadProduto);

		if (itemTrocaService.findByProdutoAndTroca(produto, troca) == null) {
			item_troca.setTroca(troca);
			item_troca.setProduto(produto);

			itemTrocaRepository.save(item_troca);
		}

		List<ItemTroca> itens = itemTrocaService.findByTroca(troca);
		ModelAndView mv = new ModelAndView("troca/troca-pesquisado");
		mv.addObject("itens", itens);

		return mv;
	}

	// Abre a tela para cadastrar uma troca
	@GetMapping("/usuario/{cpf}/trocar/{codCadProd}/cadastrar")
	public String cadastrarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			Model model) {

		CadProduto cad_produto = cadProdutoService.findById(codCadProd);
		Troca troca = trocaService.findByCadProduto(cad_produto);
		List<ItemTroca> itens = itemTrocaService.findByTroca(troca);

		model.addAttribute("cad_produto", cad_produto);
		model.addAttribute("itens", itens);

		return "troca/anuncio-troca";
	}

	// Cadastra a troca
	@PostMapping("/usuario/{cpf}/trocar/{codCadProd}/cadastrar")
	public ModelAndView cadastroTroca(@PathVariable("cpf") Long cpf, @PathVariable("codCadProd") Long codCadProd,
			Troca troca, Model model, @RequestParam("file") MultipartFile file) {

		System.out.println("Cadastro");

		CadProduto cad_produto = cadProdutoService.findById(codCadProd);

		Troca novaTroca = trocaService.findByCadProduto(cad_produto);
		novaTroca.setNomeTroca(troca.getNomeTroca());
		novaTroca.setDescTroca(troca.getDescTroca());
		novaTroca.setStatusTroca(true);
		// novaTroca.setNomeImagem(troca.getNomeImagem());

		Imagem imagem = null;

		try {
			imagem = imagemService.upload(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		novaTroca.setImagem(imagem);

		trocaRepository.save(novaTroca);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/trocas");
		return mv;
	}

	// Deleta troca
	@PostMapping("/usuario/{cpf}/trocar/{codTroca}/deletar")
	public ModelAndView deletarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codTroca") Long codTroca) {

		Troca troca = trocaService.findById(codTroca);

		troca.setCad_produto(null);
		troca.setStatusTroca(false);

		trocaRepository.save(troca);

		//trocaRepository.deleteById(codTroca);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/trocas");

		return mv;
	}

	@GetMapping("/usuario/{cpf}/trocar/{codTroca}/finalizar")
	public ModelAndView finalizarTroca(@PathVariable("cpf") Long cpf, @PathVariable("codTroca") Long codTroca) {

		Troca troca = trocaService.findById(codTroca);
		List<ItemTroca> itensTroca = itemTrocaRepository.findByTroca(troca);

		// Verifica se o usuário tem um endereço cadastrado
		Usuario usuario = usuarioService.findById(cpf);
		Optional<Endereco> obj = enderecoRepository.findByUsuario(usuario);
		if (!obj.isPresent()) {
			ModelAndView mv = new ModelAndView("usuario/perfil-geral");
			mv.addObject("alerta", "Cadastre seu endereço antes de finalizar a troca");
			mv.addObject("usuario", usuario);
			mv.addObject("endereco", enderecoService.findByUsuario(usuario));
			return mv;
		}

		// Montar strings de endereço
		Endereco enderecoDest = enderecoService.findByUsuario(troca.getUsuario());
		String enderecoDestString = enderecoDest.getRuaEnd() + ", " + enderecoDest.getNumEnd() + ", "
				+ enderecoDest.getBairroEnd() + ", " + enderecoDest.getCidadeEnd() + ", " + enderecoDest.getEstadoEnd()
				+ ", " + enderecoDest.getPaisEnd();

		Endereco enderecoRem = enderecoService.findByUsuario(usuarioService.findById(cpf));
		String enderecoRemString = enderecoRem.getRuaEnd() + ", " + enderecoRem.getNumEnd() + ", "
				+ enderecoRem.getBairroEnd() + ", " + enderecoRem.getCidadeEnd() + ", " + enderecoRem.getEstadoEnd()
				+ ", " + enderecoRem.getPaisEnd();

		ModelAndView mv = new ModelAndView("troca/confirmar-troca");

		mv.addObject("itensTroca", itensTroca);
		mv.addObject("troca", troca);
		mv.addObject("enderecoDest", enderecoDestString);
		mv.addObject("enderecoRem", enderecoRemString);

		return mv;
	}

	@Transactional
	@PostMapping("/usuario/{cpf}/trocar/{codTroca}/finalizar")
	public ModelAndView finalizarTrocae(@PathVariable("cpf") Long cpf, @PathVariable("codTroca") Long codTroca) {

		// Adquire o usuário, a troca e os itens da troca
		Usuario usuario = usuarioService.findById(cpf);
		Troca troca = trocaService.findById(codTroca);
		List<ItemTroca> itensTroca = itemTrocaRepository.findByTroca(troca);

		// Cria PedidoTroca e define a data
		PedidoTroca pedidoTroca = new PedidoTroca();
		LocalDateTime now = LocalDateTime.now();
		pedidoTroca.setDataPedidoTroca(dtf.format(now));
		pedidoTroca.setStatusRemetente("PREPARANDO");
		pedidoTroca.setStatusDestinatario("PREPARANDO");
		pedidoTroca.setNomeTroca(troca.getNomeTroca());
		pedidoTroca.setImagem(troca.getImagem());
		pedidoTroca = pedidoTrocaRepository.save(pedidoTroca);

		// Adiciona o set de ItensPedidoTroca
		Set<ItemPedidoTroca> itens = new HashSet<>();

		System.out.println("Cad : " + pedidoTroca.getCodPedidoTroca());

		ItemPedidoTroca iptUsuario = new ItemPedidoTroca();
		iptUsuario.setUsuario(usuario);
		iptUsuario.setProduto(troca.getCad_produto().getProduto());
		iptUsuario.setPedido(pedidoTroca);
		iptUsuario = itemPedidoTrocaRepository.save(iptUsuario);
		itens.add(iptUsuario);

		for (ItemTroca item_Troca : itensTroca) {
			ItemPedidoTroca iptAnunciante = new ItemPedidoTroca();
			iptAnunciante.setUsuario(troca.getUsuario());
			iptAnunciante.setProduto(
					cadProdutoRepository.findByUsuarioAndProduto(usuario, item_Troca.getProduto()).getProduto());
			iptAnunciante.setPedido(pedidoTroca);
			iptAnunciante = itemPedidoTrocaRepository.save(iptAnunciante);
			itens.add(iptAnunciante);

			// Deleta o itemTroca
			itemTrocaRepository.delete(item_Troca);
		}
		pedidoTroca.setItens(itens);

		// Define outros atributos
		pedidoTroca.setUsuario(usuario);
		pedidoTroca.setStatusDestinatario("PREPARANDO");
		pedidoTroca.setStatusRemetente("PREPARANDO");
		// pedidoTroca.setTroca(troca);

		// Salva no banco de dados
		pedidoTrocaRepository.save(pedidoTroca);

		// "Deleta" a troca
		troca.setStatusTroca(false);
		trocaRepository.save(troca);

		// Deleta os CadProdutos
		for (ItemPedidoTroca item : itens) {
			cadProdutoService.removeCadProdutoFromUsuario(item.getProduto(), usuario);
		}

		/*
		 * if (!trocaRepository.findById(codTroca).isPresent()) {
		 * System.out.println("Sucesso"); } else { System.out.println("Falhou: " +
		 * e.get); }
		 */

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/biblioteca");

		return mv;

	}

	// Abre a tela ver mais trocas
	@GetMapping("/usuario/{cpf}/mais/trocas")
	public String verMaisTrocas(@PathVariable("cpf") Long cpf, Model model) {

		List<Troca> trocas = trocaService.findAllValidAnunciosExcetoUsuario(cpf);
		model.addAttribute("trocas", trocas);

		return "troca/mais-trocas";
	}
}
