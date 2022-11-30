package com.belval.avaliacaogames.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.belval.avaliacaogames.entities.Product;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.ProductRepository;
import com.belval.avaliacaogames.repositories.UsuarioRepository;

@Configuration
//@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(1L, "Abner", "Pereira", "abner@gmail.com", "11978889934", "123456", null);
		Usuario u2 = new Usuario(2L, "Gabriel", "Barbosa", "gabriel@gmail.com", "11945658523", "123456", null);
		
		Product p1 = new Product(null, "GTA V", 50);
		Product p2 = new Product(null, "CS GO", 20);
		Product p3 = new Product(null, "Outlast", 70);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
	
	
}
