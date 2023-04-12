package com.belval.avaliacaogames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Comentario;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.ComentarioRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class ComentarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private ComentarioRepository comentarioRepository;

	// Abre a tela para comentar
	@GetMapping("/usuario/{cpf}/anuncio/{codAnuncio}/comentar")
	public ModelAndView adicionarComentario(@PathVariable("cpf") Long cpf,
			@PathVariable("codAnuncio") Long codAnuncio) {

		Anuncio anuncio = anuncioService.findById(codAnuncio);

		ModelAndView mv = new ModelAndView("comentario/adicionar-comentario");
		mv.addObject("anuncio", anuncio);
		return mv;
	}

	// Cadastra o comentario no banco de dados
	@PostMapping("/usuario/{cpf}/anuncio/{codAnuncio}/comentar")
	public ModelAndView adicionarComentario(@PathVariable("cpf") Long cpf, @PathVariable("codAnuncio") Long codAnuncio,
			Comentario comentario) {

		Usuario usuario = usuarioService.findById(cpf);
		Anuncio anuncio = anuncioService.findById(codAnuncio);

		comentario.setAnuncio(anuncio);
		comentario.setUsuario(usuario);
		comentarioRepository.save(comentario);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/compras");

		return mv;
	}

	@GetMapping("/usuario/{cpf}/anuncio/{codAnuncio}/descartar")
	public ModelAndView descartarComentario(@PathVariable("cpf") Long cpf) {

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/compras");

		return mv;
	}

}
