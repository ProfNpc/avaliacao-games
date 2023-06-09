package com.belval.avaliacaogames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.CadProdutoRepository;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.TrocaRepository;
import com.belval.avaliacaogames.repositories.UsuarioRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.CadProdutoService;
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
	private CadProdutoService cadProdutoService;

	@Autowired
	private TrocaService trocaService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private CadProdutoRepository cadProdutoRepository;

	@Autowired
	private TrocaRepository trocaRepository;

	// Home
	@GetMapping("/")
	@Transactional
	public String indice(Model model) {

		List<Anuncio> anuncios = anuncioService.findAllValidAnuncios();
		model.addAttribute("anuncios", anuncios);

		List<Troca> trocas = trocaService.findAllValidAnuncios();
		model.addAttribute("trocas", trocas);

		return "home/home";
	}

	// Home (quando estiver logado)
	@GetMapping("/home/{cpf}")
	@Transactional
	public String logado(@PathVariable("cpf") Long cpf, Model model) {

		Usuario usuario = service.findById(cpf);
		model.addAttribute(usuario);

		List<Anuncio> anuncios = anuncioService.findAllValidAnunciosExcetoUsuario(cpf);
		model.addAttribute("anuncios", anuncios);

		List<Troca> trocas = trocaService.findAllValidAnunciosExcetoUsuario(cpf);
		model.addAttribute("trocas", trocas);

		return "home/home-logado";
	}

	// Metodo para cadastrar
	@GetMapping("/usuario/cadastrar")
	public String cadastrarUsuario(Model model, Long cpf) {

		// testar
		model.addAttribute("usuario", new Usuario());

		return "usuario/cadastro";
	}

	// Salvar o usuario
	/*
	 * @PostMapping("/usuario/cadastrar") public ModelAndView form(Usuario usuario)
	 * {
	 * 
	 * if (service.existsById(usuario.getCpf())) { ModelAndView model = new
	 * ModelAndView("usuario/cadastro"); model.addObject("usuario", usuario);
	 * model.addObject("alerta", "Já existe um usuário com este CPF"); return model;
	 * } else if (service.existsByEmail(usuario.getEmail())) { ModelAndView model =
	 * new ModelAndView("usuario/cadastro"); model.addObject("usuario", usuario);
	 * model.addObject("alerta", "Já existe um usuário com este e-mail"); return
	 * model; }
	 * 
	 * repository.save(usuario);
	 * 
	 * ModelAndView model = new ModelAndView("redirect:/usuario/login"); return
	 * model; }
	 */

	// Salvar usuario - otimizado
	@PostMapping("/usuario/cadastrar")
	public ModelAndView cadastrarUsuario(@Valid Usuario usuario, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("usuario/cadastro", "usuario", usuario);
		}

		if (service.existsById(usuario.getCpf())) {
			return new ModelAndView("usuario/cadastro", "alerta", "Já existe um usuário com este CPF");
		} else if (service.existsByEmail(usuario.getEmail())) {
			return new ModelAndView("usuario/cadastro", "alerta", "Já existe um usuário com este e-mail");
		}

		repository.save(usuario);

		return new ModelAndView("redirect:/usuario/login");
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
			model = new ModelAndView("usuario/login-usuario");
			model.addObject("alerta", "Não existe uma conta com esse e-mail registrado");
			model.addObject("email", data.getEmail());
			return model;
		}

		Usuario usuario = service.findByEmail(email);

		if (data.getSenha().equals(usuario.getSenha())) {
			model = new ModelAndView("redirect:/home/" + usuario.getCpf());
			return model;
		} else {
			model = new ModelAndView("usuario/login-usuario");
			model.addObject("alerta", "Senha incorreta");
			model.addObject("email", data.getEmail());
			return model;
		}
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

		model.addAttribute("endereco", endereco);
		model.addAttribute("usuario", usuario);

		return "usuario/perfil-geral-edit";
	}

	// Confirma as alterações
	/*
	 * @PostMapping("/usuario/{cpf}/edit")
	 * 
	 * @Transactional public ModelAndView editConfirm(@PathVariable("cpf") Long cpf,
	 * Usuario usuario, Endereco endereco) { ModelAndView mv = new
	 * ModelAndView("redirect:/usuario/{cpf}");
	 * 
	 * Usuario usuarioOld = service.findById(cpf); Endereco enderecoOld =
	 * enderecoService.findByUsuario(usuarioOld);
	 * 
	 * // Usuario if (usuario.getNome() == null)
	 * usuario.setNome(usuarioOld.getNome()); if (usuario.getSobrenome() == null)
	 * usuario.setSobrenome(usuarioOld.getSobrenome()); if (usuario.getEmail() ==
	 * null) usuario.setEmail(usuarioOld.getEmail()); if (usuario.getCelular() ==
	 * null) usuario.setCelular(usuarioOld.getCelular()); if (usuario.getSenha() ==
	 * null) usuario.setSenha(usuarioOld.getSenha());
	 * 
	 * // Endereco if (endereco.getCepEnd() == null)
	 * endereco.setCepEnd(enderecoOld.getCepEnd()); if (endereco.getPaisEnd() ==
	 * null) endereco.setPaisEnd(enderecoOld.getPaisEnd()); if
	 * (endereco.getEstadoEnd() == null)
	 * endereco.setEstadoEnd(enderecoOld.getEstadoEnd()); if
	 * (endereco.getCidadeEnd() == null)
	 * endereco.setCidadeEnd(enderecoOld.getCidadeEnd()); if
	 * (endereco.getBairroEnd() == null)
	 * endereco.setBairroEnd(enderecoOld.getBairroEnd()); if (endereco.getRuaEnd()
	 * == null) endereco.setRuaEnd(enderecoOld.getRuaEnd()); if
	 * (endereco.getNumEnd() == null) endereco.setNumEnd(enderecoOld.getNumEnd());
	 * if (endereco.getUsuario() == null) endereco.setUsuario(usuario); if
	 * (endereco.getCodEnd() == null) endereco.setCodEnd(enderecoOld.getCodEnd());
	 * 
	 * repository.save(usuario); enderecoRepository.save(endereco);
	 * 
	 * return mv; }
	 */

	// Confirma as alterações - otimizado
	@PostMapping("/usuario/{cpf}/edit")
	@Transactional
	public ModelAndView editConfirm(@PathVariable("cpf") Long cpf, @ModelAttribute("usuario") Usuario usuario,
			@ModelAttribute("endereco") Endereco endereco) {
		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}");

		Usuario usuarioOld = service.findById(cpf);
		Endereco enderecoOld = enderecoService.findByUsuario(usuarioOld);

		usuario.setNome(Optional.ofNullable(usuario.getNome()).orElse(usuarioOld.getNome()));
		usuario.setSobrenome(Optional.ofNullable(usuario.getSobrenome()).orElse(usuarioOld.getSobrenome()));
		usuario.setEmail(Optional.ofNullable(usuario.getEmail()).orElse(usuarioOld.getEmail()));
		usuario.setCelular(Optional.ofNullable(usuario.getCelular()).orElse(usuarioOld.getCelular()));
		usuario.setSenha(Optional.ofNullable(usuario.getSenha()).orElse(usuarioOld.getSenha()));

		endereco.setCepEnd(Optional.ofNullable(endereco.getCepEnd()).orElse(enderecoOld.getCepEnd()));
		endereco.setPaisEnd(Optional.ofNullable(endereco.getPaisEnd()).orElse(enderecoOld.getPaisEnd()));
		endereco.setEstadoEnd(Optional.ofNullable(endereco.getEstadoEnd()).orElse(enderecoOld.getEstadoEnd()));
		endereco.setCidadeEnd(Optional.ofNullable(endereco.getCidadeEnd()).orElse(enderecoOld.getCidadeEnd()));
		endereco.setBairroEnd(Optional.ofNullable(endereco.getBairroEnd()).orElse(enderecoOld.getBairroEnd()));
		endereco.setRuaEnd(Optional.ofNullable(endereco.getRuaEnd()).orElse(enderecoOld.getRuaEnd()));
		endereco.setNumEnd(Optional.ofNullable(endereco.getNumEnd()).orElse(enderecoOld.getNumEnd()));
		endereco.setUsuario(Optional.ofNullable(endereco.getUsuario()).orElse(usuario));
		endereco.setCodEnd(Optional.ofNullable(endereco.getCodEnd()).orElse(enderecoOld.getCodEnd()));

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

		List<CadProduto> cad_produtos = cadProdutoService.findByUsuario(usuario);

		for (CadProduto cp : cad_produtos) {
			Troca t = trocaRepository.findByCadProduto(cp);
			if (t != null) {
				t.setCad_produto(null);
				t.setStatusTroca(false);
				trocaRepository.save(t);
			}
			cadProdutoRepository.delete(cp);
		}
		cadProdutoRepository.deleteAll(cad_produtos);

		Endereco enderecos = enderecoService.findByUsuario(usuario);
		enderecoRepository.delete(enderecos);

		List<Troca> trocas = trocaRepository.findByUsuario(usuario);
		for (Troca troca : trocas) {
			troca.setUsuario(null);
			trocaRepository.save(troca);
		}

		repository.deleteById(cpf);

		ModelAndView mv = new ModelAndView("redirect:/");

		return mv;
	}
}
