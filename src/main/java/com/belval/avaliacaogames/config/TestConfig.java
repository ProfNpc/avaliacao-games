package com.belval.avaliacaogames.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.belval.avaliacaogames.entities.Anuncio;
import com.belval.avaliacaogames.entities.CadProduto;
import com.belval.avaliacaogames.entities.Comentario;
import com.belval.avaliacaogames.entities.Endereco;
import com.belval.avaliacaogames.entities.Imagem;
import com.belval.avaliacaogames.entities.ItemTroca;
import com.belval.avaliacaogames.entities.Produto;
import com.belval.avaliacaogames.entities.Troca;
import com.belval.avaliacaogames.entities.Usuario;
import com.belval.avaliacaogames.repositories.AnuncioRepository;
import com.belval.avaliacaogames.repositories.CadProdutoRepository;
import com.belval.avaliacaogames.repositories.ComentarioRepository;
import com.belval.avaliacaogames.repositories.EnderecoRepository;
import com.belval.avaliacaogames.repositories.ImagemRepository;
import com.belval.avaliacaogames.repositories.ItemTrocaRepository;
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
	private CadProdutoRepository cad_produtoRepository;

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private TrocaRepository trocaRepository;

	@Autowired
	private ItemTrocaRepository item_TrocaRepository;

	@Autowired
	private ImagemRepository imagemRepository;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(89854665412L, "Abner", "Pereira", "abner@gmail.com", "11978889934", "123456");
		Usuario u2 = new Usuario(47898763225L, "Gabriel", "Barbosa", "gabriel@gmail.com", "11945658523", "123456");
		Usuario u3 = new Usuario(12365478932L, "Luiz", "Camargo", "luiz@gmail.com", "11978894456", "123456");
		Usuario u4 = new Usuario(78965432112L, "Arthur", "Felipe", "arthur@gmail.com", "1194563214", "123456");
		Usuario u5 = new Usuario(123L, "Testador", "adm", "adm@gmail.com", "1194563123", "123456");

		Endereco e1 = new Endereco(1L, "06634080", 222, "Avenida Comandante", "Km 18", "Osasco", "São Paulo", "Brasil",
				u2);
		Endereco e2 = new Endereco(2L, "06192080", 198, "Rua Yang", "Rochdale", "Osasco", "São Paulo", "Brasil", u1);
		Endereco e3 = new Endereco(3L, "06192020", 200, "Rua Maria", "Maria Helena", "Barueri", "São Paulo", "Brasil",
				u3);
		Endereco e4 = new Endereco(4L, "06563080", 100, "Rua Xang", "Vila do Conde", "Barueri", "São Paulo", "Brasil",
				u4);

		Imagem img1 = new Imagem(1l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img2 = new Imagem(2l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img3 = new Imagem(3l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img4 = new Imagem(4l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img5 = new Imagem(5l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img6 = new Imagem(6l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img7 = new Imagem(7l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img8 = new Imagem(8l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img9 = new Imagem(9l, "GTA V.png", "image/png", 516994L, "xj7ero5ay2bhwajzem1c",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678474848/xj7ero5ay2bhwajzem1c.png");
		Imagem img10 = new Imagem(10l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img11 = new Imagem(11l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img12 = new Imagem(12l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img13 = new Imagem(13l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img14 = new Imagem(14l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img15 = new Imagem(15l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img16 = new Imagem(16l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img17 = new Imagem(17l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img18 = new Imagem(18l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img19 = new Imagem(19l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img20 = new Imagem(20l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img21 = new Imagem(21l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img22 = new Imagem(22l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img23 = new Imagem(23l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");
		Imagem img24 = new Imagem(24l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"http://res.cloudinary.com/da1uhb7h7/image/upload/v1678043271/a2xbrbspxnprdd9oxx8v.jpg");

		imagemRepository.saveAll(Arrays.asList(img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11,
				img12, img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24));

		Produto p1 = new Produto(1L, "GTA V", 50,
				"Grand Theft Auto V é um jogo eletrônico de ação-aventura desenvolvido pela Rockstar North e publicado pela Rockstar Games.",
				img9);
		Produto p2 = new Produto(2L, "CS:GO", 20,
				"Counter-Strike: Global Offensive é um jogo online desenvolvido pela Valve Corporation e pela Hidden Path Entertainment, sendo uma sequência de Counter-Strike: Source. É o quarto título principal da franquia.",
				img10);
		Produto p3 = new Produto(3L, "Outlast", 70,
				"O inferno é um experimento do qual não há escapatória em Outlast, um jogo de terror de sobrevivência em primeira pessoa da Red Barrels. Como o jornalista investigativo Miles Upshur, explore o Mount Massive Asylum e tente sobreviver tempo o bastante para",
				img11);
		Produto p4 = new Produto(4L, "Red Dead Redemption 2", 120,
				"Red Dead é uma série de jogos de vídeo game de ação e aventura no velho oeste americano aclamados pela crítica. ",
				img12);
		Produto p5 = new Produto(5L, "Elder Ring", 100,
				"Elden Ring é um jogo eletrônico de RPG de ação em terceira pessoa, desenvolvido pela FromSoftware e publicado pela Bandai Namco Entertainment.",
				img13);
		Produto p6 = new Produto(6L, "God of War", 230,
				"God of War é um jogo eletrônico de ação-aventura desenvolvido pela Santa Monica Studio e publicado pela Sony Interactive Entertainment.",
				img14);
		Produto p7 = new Produto(7L, "Tony Hawk's", 40,
				"Tony Hawk's é uma série de jogos digitais com foco em Skate tendo o skatista profissional Tony Hawk como principal personalidade.",
				img15);
		Produto p8 = new Produto(8L, "Hitman 3", 60,
				"Hitman 3 é um jogo eletrônico de furtividade desenvolvido e publicado pela IO Interactive. É o oitavo título principal da série Hitman.",
				img16);
		Produto p9 = new Produto(9L, "StarCraft", 95,
				"StarCraft é uma franquia de ficção científica militar criada por Chris Metzen e James Phinney, e de propriedade da Blizzard Entertainment.",
				img17);
		Produto p10 = new Produto(10L, "Cuphead", 78,
				"Cuphead é um jogo eletrônico de run and gun e plataforma criado pelos irmãos canadenses Chad e Jared Moldenhauer através da Studio MDHR.",
				img18);

		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
		System.out.println("Id do endereço 1 é " + e1.getCodEnd());
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

		CadProduto cp1 = new CadProduto(1L, p2, 5L, u1);
		CadProduto cp2 = new CadProduto(2L, p1, 2L, u1);
		CadProduto cp3 = new CadProduto(3L, p3, 6L, u2);
		CadProduto cp4 = new CadProduto(4L, p5, 1L, u3);
		CadProduto cp5 = new CadProduto(5L, p4, 9L, u4);
		CadProduto cp6 = new CadProduto(6L, p7, 1L, u1);
		CadProduto cp7 = new CadProduto(7L, p8, 2L, u3);
		CadProduto cp8 = new CadProduto(8L, p8, 2L, u2);
		CadProduto cp9 = new CadProduto(9L, p8, 2L, u2);
		CadProduto cp10 = new CadProduto(10L, p9, 2L, u2);
		CadProduto cp11 = new CadProduto(11L, p5, 1L, u1);

		cad_produtoRepository.saveAll(Arrays.asList(cp1, cp2, cp3, cp4, cp5, cp6, cp7, cp8, cp9, cp10, cp11));

		Anuncio an1 = new Anuncio(1L, "Outlast", "Jogo novo, quase sem utilização", 72.50, 5, 0, true, u2, img1,
				"Terror");
		Anuncio an2 = new Anuncio(2L, "GTA V", "Jogo novo, quase sem utilização", 60.50, 5, 3, true, u1, img2, "Ação");
		Anuncio an3 = new Anuncio(3L, "Elder Ring", "Jogo novo, quase sem utilização", 50.00, 5, 2, true, u1, img3,
				"Aventura");
		Anuncio an4 = new Anuncio(4L, "God of War", "Jogo novo, quase sem utilização", 55.50, 5, 1, true, u3, img4,
				"Ação");
		Anuncio an5 = new Anuncio(5L, "Cuphead", "Jogo novo, quase sem utilização", 100.50, 5, 4, true, u2, img5,
				"Aventura");
		Anuncio an6 = new Anuncio(6L, "Red Dead Redeptiom 2", "Jogo novo, quase sem utilização", 150.50, 5, 1, true, u3,
				img6, "Ação");
		Anuncio an7 = new Anuncio(7L, "StarCraft", "Jogo novo, quase sem utilização", 60.50, 5, 0, true, u1, img7,
				"Aventura");
		Anuncio an8 = new Anuncio(8L, "GTA V", "Jogo novo, quase sem utilização", 78.50, 5, 3, true, u4, img8, "Ação");

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

		Troca t1 = new Troca(1L, "CS:GO 1.6 modificado", "Com 9 meses de uso", true, u1, cp1, img19);
		Troca t2 = new Troca(2L, "Outlast sem risco, original", "Esta em perfeito estado, com 3 meses de uso", true, u2,
				cp3, img20);
		Troca t3 = new Troca(3L, "GTA V Xbox 360", "Novinho, com 5 meses de uso", true, u1, cp2, img21);
		Troca t4 = new Troca(4L, "Red Dead Redemption 2 novo", "Troco meu Red Dead de 6 meses de uso", true, u4, cp5,
				img22);
		Troca t5 = new Troca(5L, "Elder Ring PS3", "Apenas 1 meses de uso", true, u3, cp4, img23);
		Troca t6 = new Troca(6L, "Hitman 3 original", "Tem 2 anos de uso", true, u2, cp8, img24);

		trocaRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6));

		ItemTroca it1 = new ItemTroca(1L, t1, p9);
		ItemTroca it2 = new ItemTroca(2L, t1, p8);
		ItemTroca it3 = new ItemTroca(3L, t2, p5);
		ItemTroca it4 = new ItemTroca(4L, t3, p6);
		ItemTroca it5 = new ItemTroca(5L, t3, p1);
		ItemTroca it6 = new ItemTroca(6L, t3, p2);
		ItemTroca it7 = new ItemTroca(7L, t4, p7);
		ItemTroca it8 = new ItemTroca(8L, t5, p1);
		ItemTroca it9 = new ItemTroca(9L, t6, p2);
		ItemTroca it10 = new ItemTroca(10L, t6, p7);
		ItemTroca it11 = new ItemTroca(11L, t5, p10);

		item_TrocaRepository.saveAll(Arrays.asList(it1, it2, it3, it4, it5, it6, it7, it8, it9, it10, it11));

	}

}
