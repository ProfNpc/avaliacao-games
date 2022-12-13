package com.belval.avaliacaogames.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.Cad_ProdutoRepository;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.ProductRepository;
import com.belval.avaliacaogames.repositories.UsuarioRepository;

@Configuration
//@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private Cad_ProdutoRepository cad_produtoRepository;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(89854665412L, "Abner", "Pereira", "abner@gmail.com", "11978889934", "123456");
		Usuario u2 = new Usuario(47898763225L,"Gabriel", "Barbosa", "gabriel@gmail.com", "11945658523", "123456");

		Endereco e1 = new Endereco(1L, "06634080", 222, "Avenida Comandante", "Km 18", "Osasco", "São Paulo", "Brasil");
		Endereco e2 = new Endereco(2L, "06192080", 198, "Rua Yang", "Rochdale", "Osasco", "São Paulo", "Brasil");

		userRepository.saveAll(Arrays.asList(u1, u2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));


		u1.getEnderecos().add(e2);
		u2.getEnderecos().add(e1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		Produto p1 = new Produto(null, "GTA V", 50);
		Produto p2 = new Produto(null, "CS GO", 20);
		Produto p3 = new Produto(null, "Outlast", 70);


		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Cad_Produto cp1 = new Cad_Produto(1L, p2, 5L, u1, true);
		Cad_Produto cp2 = new Cad_Produto(2L, p1, 2L, u2, true);
		
		cad_produtoRepository.saveAll(Arrays.asList(cp1,cp2));
	}

}
