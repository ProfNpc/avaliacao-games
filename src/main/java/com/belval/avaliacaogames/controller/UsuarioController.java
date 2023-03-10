package com.belval.avaliacaogames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.Cad_ProdutoRepository;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.UsuarioRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.Cad_ProdutoService;
import com.belval.avaliacaogames.services.EnderecoService;
import com.belval.avaliacaogames.services.TrocaService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private Cad_ProdutoService cad_ProdutoService;

	@Autowired
	private TrocaService trocaService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private Cad_ProdutoRepository cad_ProdutoRepository;

	// Home
	@GetMapping("/")
	public String indice(Model model) {

		List<Anuncio> anuncios = anuncioService.findAll();
		model.addAttribute("anuncios", anuncios);

		List<Troca> trocas = trocaService.findAll();
		model.addAttribute("trocas", trocas);

		return "home/home";
	}

	// Home (quando estiver logado)
	@GetMapping("/home/{cpf}")
	public String logado(@PathVariable("cpf") Long cpf, Model model) {

		Usuario usuario = service.findById(cpf);
		model.addAttribute(usuario);

		List<Anuncio> anuncios = anuncioService.findAll();
		model.addAttribute("anuncios", anuncios);

		List<Troca> trocas = trocaService.findAll();
		model.addAttribute("trocas", trocas);

		return "home/home-logado";
	}

	// Metodo para cadastrar
	@GetMapping("/usuario/cadastrar")
	public String form(Model model, Long cpf) {

		// testar
		model.addAttribute("usuario", new Usuario());

		return "usuario/cadastro";
	}

	// Salvar o usuario
	@PostMapping("/usuario/cadastrar")
	public ModelAndView form(Usuario usuario) {
		usuario.getCpf();
		ModelAndView model = new ModelAndView("redirect:/usuario/login");

		repository.save(usuario);

		return model;
	}

	// Metodo para fazer login
	@GetMapping("/usuario/login")
	public String login() {
		return "usuario/login-usuario";
	}

	// Validar login
	@PostMapping("/usuario/login")
	public ModelAndView loginValidar(Usuario data) {
		String email = data.getEmail();

		ModelAndView model;

		if (!service.existsByEmail(email)) {
			model = new ModelAndView("redirect:/usuario/login");
			return model;
		}

		Usuario usuario = service.findByEmail(email);

		if (data.getSenha().equals(usuario.getSenha())) {
			model = new ModelAndView("redirect:/home/" + usuario.getCpf());
		} else {
			model = new ModelAndView("redirect:/usuario/login");
		}

		return model;
	}

	// Metodo para acessar perfil do usuario
	@GetMapping("/usuario/{cpf}")
	public String perfil(@PathVariable("cpf") Long cpf, Model model) {

		Usuario usuario = service.findById(cpf);
		Endereco endereco = enderecoService.findByUsuario(usuario);

		model.addAttribute(usuario);
		model.addAttribute(endereco);

		return "usuario/perfil-geral";
	}

	// Metodo para editar dados do usuario
	@GetMapping("/usuario/{cpf}/edit")
	public String edit(@PathVariable("cpf") Long cpf, Model model) {

		Usuario usuario = service.findById(cpf);

		Endereco endereco = enderecoService.findByUsuario(usuario);

		/*
		 * if (usuario == null) { return "usuario/usuario-nao-existe"; }
		 */

		model.addAttribute("endereco", endereco);
		model.addAttribute("usuario", usuario);

		return "usuario/perfil-geral-edit";
	}

	// Confirma as alterações
	@PostMapping("/usuario/{cpf}/edit")
	public ModelAndView editConfirm(@PathVariable("cpf") Long cpf, Usuario usuario, Endereco endereco) {
		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}");

		Usuario usuarioOld = service.findById(cpf);
		Endereco enderecoOld = enderecoService.findByUsuario(usuarioOld);

		// Usuario
		if (usuario.getNome() == null)
			usuario.setNome(usuarioOld.getNome());
		if (usuario.getSobrenome() == null)
			usuario.setSobrenome(usuarioOld.getSobrenome());
		if (usuario.getEmail() == null)
			usuario.setEmail(usuarioOld.getEmail());
		if (usuario.getCelular() == null)
			usuario.setCelular(usuarioOld.getCelular());
		if (usuario.getSenha() == null)
			usuario.setSenha(usuarioOld.getSenha());

		// Endereco
		if (endereco.getCepEnd() == null)
			endereco.setCepEnd(enderecoOld.getCepEnd());
		if (endereco.getPaisEnd() == null)
			endereco.setPaisEnd(enderecoOld.getPaisEnd());
		if (endereco.getEstadoEnd() == null)
			endereco.setEstadoEnd(enderecoOld.getEstadoEnd());
		if (endereco.getCidadeEnd() == null)
			endereco.setCidadeEnd(enderecoOld.getCidadeEnd());
		if (endereco.getBairroEnd() == null)
			endereco.setBairroEnd(enderecoOld.getBairroEnd());
		if (endereco.getRuaEnd() == null)
			endereco.setRuaEnd(enderecoOld.getRuaEnd());
		if (endereco.getNumEnd() == null)
			endereco.setNumEnd(enderecoOld.getNumEnd());
		if (endereco.getUsuario() == null)
			endereco.setUsuario(usuario);
		if (endereco.getCodEnd() == null)
			endereco.setCodEnd(enderecoOld.getCodEnd());

		repository.save(usuario);
		enderecoRepository.save(endereco);

		return mv;
	}

	// Metodo para deletar conta
	@GetMapping("/usuario/{cpf}/deletar")
	public String delete(@PathVariable("cpf") Long cpf, Model model) {

		model.addAttribute("cpf", cpf);
		return "usuario/deletar-conta";
	}

	// Confirma deletar a conta
	@PostMapping("/usuario/{cpf}/deletar")
	public ModelAndView deleteConfirm(@PathVariable("cpf") Long cpf, Model model) {

		Usuario usuario = service.findById(cpf);

		List<Anuncio> anuncios = anuncioService.findByUsuario(usuario);
		anuncioRepository.deleteAll(anuncios);

		List<Cad_Produto> cad_produtos = cad_ProdutoService.findByUsuario(usuario);
		cad_ProdutoRepository.deleteAll(cad_produtos);

		Endereco enderecos = enderecoService.findByUsuario(usuario);
		enderecoRepository.delete(enderecos);

		repository.deleteById(cpf);

		ModelAndView mv = new ModelAndView("redirect:/");

		return mv;
	}
}
