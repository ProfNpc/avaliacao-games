package com.belval.avaliacaogames.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Imagem;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.ImagemService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class AnuncioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ImagemService imagemService;

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private AnuncioRepository anuncioRepository;

	// Tela com anuncios do usuario
	@GetMapping("/usuario/{cpf}/anuncios")
	public String meusAnuncios(@PathVariable("cpf") Long cpf, Model model) {
		model.addAttribute(cpf);

		Usuario usuario = usuarioService.findById(cpf);

		List<Anuncio> anuncios = anuncioService.findByUsuario(usuario);
		List<Anuncio> anuncio = new ArrayList<Anuncio>();

		for (Anuncio an : anuncios) {
			Long id = an.getCodAnuncio();
			Anuncio anun = anuncioService.findById(id);
			// Imagem m = imagemService.getImagem(anun.getCodImagem());
			anuncio.add(anun);
		}
		// Imagem img = imagemService.findById(1L);

		// model.addAttribute("imagem", m.getUrl());
		model.addAttribute("anuncio", anuncio);

		return "anuncio/meus-anuncios";
	}

	// Abre a tela para cadastrar um anuncio
	@GetMapping("/usuario/{cpf}/anunciar")
	public String anunciar(@PathVariable("cpf") Long cpf, Model model) {
		model.addAttribute(cpf);

		return "anuncio/anuncio-venda";
	}

	// Cadastra o anuncio no banco de dados
	@PostMapping("/usuario/{cpf}/anunciar")
	public ModelAndView cadastrarAnuncio(@PathVariable("cpf") Long cpf, Model model, Anuncio anuncio,
			@RequestParam("file") MultipartFile file) {

		Usuario usuario = usuarioService.findById(cpf);
		anuncio.setQuantVendida(0);
		anuncio.setUsuario(usuario);
		anuncio.setStatusAnuncio(true);

		Imagem imagem = null;

		try {
			imagem = imagemService.upload(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		anuncio.setImagem(imagem);

		anuncioRepository.save(anuncio);
		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/anuncios");

		return mv;
	}

	// Abre a tela ver mais anuncios
	@GetMapping("/usuario/{cpf}/mais/anuncios")
	public String verMaisAnuncios(@PathVariable("cpf") Long cpf, Model model) {

		List<Anuncio> anuncios = anuncioService.findAllValidAnunciosExcetoUsuario(cpf);
		model.addAttribute("anuncios", anuncios);

		return "anuncio/mais-anuncios";
	}

	// Deletar anuncio do banco de dados
	@PostMapping("/usuario/{cpf}/anuncio/{codAnuncio}/deletar")
	public ModelAndView deletarAnuncio(@PathVariable("cpf") Long cpf, @PathVariable("codAnuncio") Long codAnuncio) {

		anuncioRepository.deleteById(codAnuncio);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/anuncios");

		return mv;
	}

}
