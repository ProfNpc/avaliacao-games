package com.belval.avaliacaogames.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.belval.avaliacaogames.entities.Imagem;
import com.belval.avaliacaogames.services.ImagemService;

@RestController
@RequestMapping("/api/images")
public class ImagemController {

	@Autowired
	private final ImagemService imagemService;

	@Autowired
	public ImagemController(ImagemService imagemService) {
		this.imagemService = imagemService;
	}

	@PostMapping
	public ResponseEntity<Imagem> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		Imagem imagem = imagemService.upload(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(imagem);
	}

}
