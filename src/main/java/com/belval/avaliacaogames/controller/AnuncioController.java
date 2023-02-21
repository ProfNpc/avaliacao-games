package com.belval.avaliacaogames.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.services.AnuncioService;
import com.belval.avaliacaogames.services.UsuarioService;

@Controller
public class AnuncioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private AnuncioRepository anuncioRepository;

	private static String caminhoImagens = "D:/TCC-INFORMATICA/imagens/";

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
			anuncio.add(anun);
		}

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
			@RequestParam("file") MultipartFile arquivo) {

		Usuario usuario = usuarioService.findById(cpf);
		anuncio.setUsuario(usuario);
		anuncio.setStatusAnuncio(true);
		anuncio.setTipoAnuncio("Venda");

		anuncioRepository.save(anuncio);

		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(anuncio.getCodAnuncio()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				anuncio.setNomeImagem(String.valueOf(anuncio.getCodAnuncio()) + arquivo.getOriginalFilename());
				anuncioRepository.save(anuncio);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/anuncios");

		return mv;
	}

	// Deletar anuncio do banco de dados
	@PostMapping("/usuario/{cpf}/anuncio/{codAnuncio}/deletar")
	public ModelAndView deletarAnuncio(@PathVariable("cpf") Long cpf, @PathVariable("codAnuncio") Long codAnuncio) {

		anuncioRepository.deleteById(codAnuncio);

		ModelAndView mv = new ModelAndView("redirect:/usuario/{cpf}/anuncios");

		return mv;
	}

	// Mostar imagem
	@SuppressWarnings("null") // Em caso de erro remover o SuppressWarning
	@GetMapping("/usuario/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}

}
