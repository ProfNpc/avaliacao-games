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
import com.belval.avaliacaogames.services.EnderecoService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class EnderecoController {

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	EnderecoService enderecoService;

	// Metodo para cadastrar endereco
	@GetMapping("/usuario/{cpf}/cadastrar/endereco")
	public String formEndereco(@PathVariable("cpf") Long cpf, Model model) {

		//Usuario usuario = usuarioService.findById(cpf);
		//model.addAttribute(usuario);
		
		model.addAttribute("endereco", new Endereco());

		return "usuario/cadastro-endereco";
	}

	// Salvar o usuario
	@PostMapping("/usuario/{cpf}/cadastrar/endereco")
	public ModelAndView formEndereco(@PathVariable("cpf") Long cpf, Model mod , Endereco endereco) {
		// usuario.getCpf();
		ModelAndView model = new ModelAndView("redirect:/");

		Usuario usuario = usuarioService.findById(cpf);
		endereco.setUsuario(usuario);
		
		enderecoRepository.save(endereco);

		System.out.println(endereco.getCep_end());

		return model;
	}

}