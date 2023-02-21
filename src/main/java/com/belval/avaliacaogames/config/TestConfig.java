package com.belval.avaliacaogames.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.Cad_ProdutoRepository;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.ProdutoRepository;
import com.belval.avaliacaogames.repositories.UsuarioRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private Cad_ProdutoRepository cad_produtoRepository;

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(89854665412L, "Abner", "Pereira", "abner@gmail.com", "11978889934", "123456");
		Usuario u2 = new Usuario(47898763225L, "Gabriel", "Barbosa", "gabriel@gmail.com", "11945658523", "123456");
		Usuario u3 = new Usuario(12365478932L, "Luiz", "Camargo", "luiz@gmail.com", "11978894456", "123456");
		Usuario u4 = new Usuario(78965432112L, "Arthur", "Felipe", "arthur@gmail.com", "1194563214", "123456");

		Endereco e1 = new Endereco(1L, "06634080", 222, "Avenida Comandante", "Km 18", "Osasco", "São Paulo", "Brasil",
				u2);
		Endereco e2 = new Endereco(2L, "06192080", 198, "Rua Yang", "Rochdale", "Osasco", "São Paulo", "Brasil", u1);
		Endereco e3 = new Endereco(3L, "06192020", 200, "Rua Maria", "Maria Helena", "Barueri", "São Paulo", "Brasil",
				u3);
		Endereco e4 = new Endereco(4L, "06563080", 100, "Rua Xang", "Vila do Conde", "Barueri", "São Paulo", "Brasil",
				u4);

		Produto p1 = new Produto(1L, "GTA V", 50,
				"Grand Theft Auto V é um jogo eletrônico de ação-aventura desenvolvido pela Rockstar North e publicado pela Rockstar Games.");
		Produto p2 = new Produto(2L, "CS:GO", 20,
				"Counter-Strike: Global Offensive é um jogo online desenvolvido pela Valve Corporation e pela Hidden Path Entertainment, sendo uma sequência de Counter-Strike: Source. É o quarto título principal da franquia.");
		Produto p3 = new Produto(3L, "Outlast", 70,
				"O inferno é um experimento do qual não há escapatória em Outlast, um jogo de terror de sobrevivência em primeira pessoa da Red Barrels. Como o jornalista investigativo Miles Upshur, explore o Mount Massive Asylum e tente sobreviver tempo o bastante para");
		Produto p4 = new Produto(4L, "Red Dead Redemption 2", 120,
				"Red Dead é uma série de jogos de vídeo game de ação e aventura no velho oeste americano aclamados pela crítica. ");
		Produto p5 = new Produto(5L, "Elder Ring", 100,
				"Elden Ring é um jogo eletrônico de RPG de ação em terceira pessoa, desenvolvido pela FromSoftware e publicado pela Bandai Namco Entertainment.");
		Produto p6 = new Produto(6L, "God of War", 230,
				"God of War é um jogo eletrônico de ação-aventura desenvolvido pela Santa Monica Studio e publicado pela Sony Interactive Entertainment.");
		Produto p7 = new Produto(7L, "Tony Hawk's", 40,
				"Tony Hawk's é uma série de jogos digitais com foco em Skate tendo o skatista profissional Tony Hawk como principal personalidade.");
		Produto p8 = new Produto(8L, "Hitman 3", 60,
				"Hitman 3 é um jogo eletrônico de furtividade desenvolvido e publicado pela IO Interactive. É o oitavo título principal da série Hitman.");
		Produto p9 = new Produto(9L, "StarCraft", 95,
				"StarCraft é uma franquia de ficção científica militar criada por Chris Metzen e James Phinney, e de propriedade da Blizzard Entertainment.");
		Produto p10 = new Produto(10L, "Cuphead", 78,
				"Cuphead é um jogo eletrônico de run and gun e plataforma criado pelos irmãos canadenses Chad e Jared Moldenhauer através da Studio MDHR.");

		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

		Cad_Produto cp1 = new Cad_Produto(1L, p2, 5L, u1, true);
		Cad_Produto cp2 = new Cad_Produto(2L, p1, 2L, u1, true);
		Cad_Produto cp3 = new Cad_Produto(3L, p3, 6L, u2, true);
		Cad_Produto cp4 = new Cad_Produto(4L, p5, 1L, u3, true);
		Cad_Produto cp5 = new Cad_Produto(5L, p4, 9L, u4, true);
		Cad_Produto cp6 = new Cad_Produto(6L, p7, 8L, u1, true);
		Cad_Produto cp7 = new Cad_Produto(7L, p8, 2L, u3, true);
		Cad_Produto cp8 = new Cad_Produto(8L, p8, 2L, u2, true);

		cad_produtoRepository.saveAll(Arrays.asList(cp1, cp2, cp3, cp4, cp5, cp6, cp7, cp8));

		Anuncio an1 = new Anuncio(1L, "Outlast", "Jogo novo, quase sem utilização", 72.50, 5, "Venda", true, u2,
				"1Assalariado.png");
		Anuncio an2 = new Anuncio(2L, "GTA V", "Jogo novo, quase sem utilização", 60.50, 5, "Venda", true, u1,
				"2Assalariado.png");
		Anuncio an3 = new Anuncio(3L, "Elder Ring", "Jogo novo, quase sem utilização", 50.00, 5, "Venda", true, u1,
				"3Assalariado.png");
		Anuncio an4 = new Anuncio(4L, "God of War", "Jogo novo, quase sem utilização", 55.50, 5, "Venda", true, u3,
				"4Assalariado.png");
		Anuncio an5 = new Anuncio(5L, "Cuphead", "Jogo novo, quase sem utilização", 100.50, 5, "Venda", true, u2,
				"5Assalariado.png");
		Anuncio an6 = new Anuncio(6L, "Red Dead Redeptiom 2", "Jogo novo, quase sem utilização", 150.50, 5, "Venda",
				true, u3, "6Assalariado.png");
		Anuncio an7 = new Anuncio(7L, "StarCraft", "Jogo novo, quase sem utilização", 60.50, 5, "Venda", true, u1,
				"7Assalariado.png");
		Anuncio an8 = new Anuncio(8L, "GTA V", "Jogo novo, quase sem utilização", 78.50, 5, "Venda", true, u4,
				"8Assalariado.png");

		anuncioRepository.saveAll(Arrays.asList(an1, an2, an3, an4, an5, an6, an7, an8));
	}

}
