package com.belval.avaliacaogames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.UsuarioRepository;
import com.belval.avaliacaogames.services.EnderecoService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioService service;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	// Home
	@GetMapping("/")
	public String indice() {
		return "home/home";
	}

	// Home (quando estiver logado)
	@GetMapping("/home/{cpf}")
	public String logado(@PathVariable("cpf") Long cpf, Model model) {

		Usuario usuario = service.findById(cpf);
		model.addAttribute(usuario);

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

		if (usuario == null) {
			return "usuario/usuario-nao-existe";
		}

		model.addAttribute("endereco", endereco);
		model.addAttribute("usuario", usuario);

		return "usuario/perfil-geral-edit";
	}

	// Confirma as alterações
	@PostMapping("/usuario/{cpf}/edit")
	public ModelAndView editConfirm(Usuario usuario, Endereco endereco) {
		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}");

		Usuario usuarioOld = service.findById(usuario.getCpf());
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
		if (endereco.getCep_end() == null)
			endereco.setCep_end(enderecoOld.getCep_end());
		if (endereco.getPais_end() == null)
			endereco.setPais_end(enderecoOld.getPais_end());
		if (endereco.getEstado_end() == null)
			endereco.setEstado_end(enderecoOld.getEstado_end());
		if (endereco.getCidade_end() == null)
			endereco.setCidade_end(enderecoOld.getCidade_end());
		if (endereco.getBairro_end() == null)
			endereco.setBairro_end(enderecoOld.getBairro_end());
		if (endereco.getRua_end() == null)
			endereco.setRua_end(enderecoOld.getRua_end());
		if (endereco.getNum_end() == null)
			endereco.setNum_end(enderecoOld.getNum_end());
		if (endereco.getUsuario() == null)
			endereco.setUsuario(usuario);

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

		repository.deleteById(cpf);

		ModelAndView mv = new ModelAndView("redirect:/");

		return mv;
	}

	// Ver lista de usuários
	@GetMapping("/usuario/list")
	public String list(Model model) {
		model.addAttribute("usuarios", repository.findAll());
		return "usuario/lista-usuario";
	}

}
