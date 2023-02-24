package com.belval.avaliacaogames.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.Cad_Produto;
import com.belval.avaliacaogames.entities.Comentario;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Item_Troca;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.Cad_ProdutoRepository;
import com.belval.avaliacaogames.repositories.ComentarioRepository;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.Item_TrocaRepository;
import com.belval.avaliacaogames.repositories.ProdutoRepository;
import com.belval.avaliacaogames.repositories.TrocaRepository;
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

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private Item_TrocaRepository item_TrocaRepository;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(89854665412L, "Abner", "Pereira", "abner@gmail.com", "11978889934", "123456",
				"Masculino");
		Usuario u2 = new Usuario(47898763225L, "Gabriel", "Barbosa", "gabriel@gmail.com", "11945658523", "123456",
				"Masculino");
		Usuario u3 = new Usuario(12365478932L, "Luiz", "Camargo", "luiz@gmail.com", "11978894456", "123456",
				"Masculino");
		Usuario u4 = new Usuario(78965432112L, "Arthur", "Felipe", "arthur@gmail.com", "1194563214", "123456",
				"Masculino");

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

		Anuncio an1 = new Anuncio(1L, "Outlast", "Jogo novo, quase sem utilização", 72.50, 5, true, u2,
				"1Assalariado.png", "Terror");
		Anuncio an2 = new Anuncio(2L, "GTA V", "Jogo novo, quase sem utilização", 60.50, 5, true, u1,
				"2Assalariado.png", "Ação");
		Anuncio an3 = new Anuncio(3L, "Elder Ring", "Jogo novo, quase sem utilização", 50.00, 5, true, u1,
				"3Assalariado.png", "Aventura");
		Anuncio an4 = new Anuncio(4L, "God of War", "Jogo novo, quase sem utilização", 55.50, 5, true, u3,
				"4Assalariado.png", "Ação");
		Anuncio an5 = new Anuncio(5L, "Cuphead", "Jogo novo, quase sem utilização", 100.50, 5, true, u2,
				"5Assalariado.png", "Aventura");
		Anuncio an6 = new Anuncio(6L, "Red Dead Redeptiom 2", "Jogo novo, quase sem utilização", 150.50, 5, true, u3,
				"6Assalariado.png", "Ação");
		Anuncio an7 = new Anuncio(7L, "StarCraft", "Jogo novo, quase sem utilização", 60.50, 5, true, u1,
				"7Assalariado.png", "Aventura");
		Anuncio an8 = new Anuncio(8L, "GTA V", "Jogo novo, quase sem utilização", 78.50, 5, true, u4,
				"8Assalariado.png", "Ação");

		anuncioRepository.saveAll(Arrays.asList(an1, an2, an3, an4, an5, an6, an7, an8));

		Comentario cm1 = new Comentario(1L, "Muito bom esse jogo!", 4.0, u1, an1);
		Comentario cm2 = new Comentario(2L, "Da para o gasto!", 3.0, u2, an2);
		Comentario cm3 = new Comentario(3L, "Muito bom!", 5.0, u1, an3);
		Comentario cm4 = new Comentario(4L, "Não gostei!", 2.0, u4, an4);
		Comentario cm5 = new Comentario(5L, "Tinha mais expectativas!", 3.5, u3, an5);
		Comentario cm6 = new Comentario(6L, "Péssimo!", 2.0, u4, an6);
		Comentario cm7 = new Comentario(7L, "Muito bom, amei!", 5.0, u2, an7);
		Comentario cm8 = new Comentario(8L, "Horrivel!", 1.0, u4, an2);
		Comentario cm9 = new Comentario(9L, "Mais ou menos!", 2.5, u3, an3);
		Comentario cm10 = new Comentario(10L, "Muito top!", 4.0, u2, an3);

		comentarioRepository.saveAll(Arrays.asList(cm1, cm2, cm3, cm4, cm5, cm6, cm7, cm8, cm9, cm10));

		Troca t1 = new Troca(1L, "GTA V sem risco, original", "Esta em perfeito estado, com 3 meses de uso", true, u1,
				cp1, null);
		Troca t2 = new Troca(4L, "GTA V sem risco, original", "Esta em perfeito estado, com 3 meses de uso", true, u2,
				cp3, null);
		Troca t3 = new Troca(5L, "GTA V sem risco, original", "Esta em perfeito estado, com 3 meses de uso", true, u1,
				cp2, null);
		Troca t4 = new Troca(6L, "GTA V sem risco, original", "Esta em perfeito estado, com 3 meses de uso", true, u4,
				cp5, null);
		Troca t5 = new Troca(6L, "GTA V sem risco, original", "Esta em perfeito estado, com 3 meses de uso", true, u3,
				cp4, null);
		Troca t6 = new Troca(6L, "GTA V sem risco, original", "Esta em perfeito estado, com 3 meses de uso", true, u2,
				cp8, null);
		

		trocaRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6));

		Item_Troca it1 = new Item_Troca(1L, t1, p9);
		Item_Troca it2 = new Item_Troca(2L, t1, p8);
		Item_Troca it3 = new Item_Troca(3L, t2, p5);
		Item_Troca it4 = new Item_Troca(4L, t3, p6);
		Item_Troca it5 = new Item_Troca(5L, t3, p1);
		Item_Troca it6 = new Item_Troca(6L, t3, p2);
		Item_Troca it7 = new Item_Troca(7L, t4, p7);
		Item_Troca it8 = new Item_Troca(8L, t5, p1);
		Item_Troca it9 = new Item_Troca(9L, t6, p2);
		Item_Troca it10 = new Item_Troca(10L, t6, p7);
		Item_Troca it11 = new Item_Troca(11L, t5, p10);

		item_TrocaRepository.saveAll(Arrays.asList(it1, it2, it3, it4, it5, it6, it7, it8, it9, it10, it11));
	}

}
