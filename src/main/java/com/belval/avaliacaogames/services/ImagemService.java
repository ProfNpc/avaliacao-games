package com.belval.avaliacaogames.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.belval.avaliacaogames.entities.Imagem;
import com.belval.avaliacaogames.repositories.ImagemRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImagemService {

	@Autowired
	private final Cloudinary cloudinary;

	@Autowired
	private final ImagemRepository imagemRepository;

	@Autowired
	public ImagemService(Cloudinary cloudinary, ImagemRepository imagemRepository) {
		this.cloudinary = cloudinary;
		this.imagemRepository = imagemRepository;
	}

	public List<Imagem> findAll() {
		return imagemRepository.findAll();
	}

	public Imagem findById(Long id) {
		Optional<Imagem> obj = imagemRepository.findById(id);
		return obj.get();
	}

	// @SuppressWarnings("rawtypes") 
	public Imagem upload(MultipartFile file) throws IOException {
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

		Imagem imagem = new Imagem();
		imagem.setName(file.getOriginalFilename());
		imagem.setType(file.getContentType());
		imagem.setSize(file.getSize());
		imagem.setLocation((String) uploadResult.get("public_id"));
		imagem.setUrl((String) uploadResult.get("url"));

		imagemRepository.save(imagem);

		return imagem;
	}

	public Imagem getImagem(Long id) {
		return imagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
	}
}
