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

		Usuario u1 = new Usuario(89854665412L, "Abner", "Pereira", "abner.admin@gmail.com", "11978889934", "123456");
		Usuario u2 = new Usuario(47898763225L, "Gabriel", "Barbosa", "gabriel.admin@gmail.com", "11945658523",
				"123456");
		Usuario u3 = new Usuario(12365478932L, "Luiz", "Camargo", "luiz.admin@gmail.com", "11978894456", "123456");
		Usuario u4 = new Usuario(78965432112L, "Arthur", "Felipe", "arthur.admin@gmail.com", "1194563214", "123456");
		Usuario u5 = new Usuario(123L, "Testador", "adm", "adm@gmail.com", "1194563123", "123456");

		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));

		Endereco e1 = new Endereco(1L, "06634080", 222, "Avenida Comandante", "Km 18", "Osasco", "São Paulo", "Brasil",
				u2);
		Endereco e2 = new Endereco(2L, "06192080", 198, "Rua Yang", "Rochdale", "Osasco", "São Paulo", "Brasil", u1);
		Endereco e3 = new Endereco(3L, "06192020", 200, "Rua Maria", "Maria Helena", "Barueri", "São Paulo", "Brasil",
				u3);
		Endereco e4 = new Endereco(4L, "06563080", 100, "Rua Xang", "Vila do Conde", "Barueri", "São Paulo", "Brasil",
				u4);

		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));

		Imagem img1 = new Imagem(1l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684280967/images_ola1av.jpg");
		Imagem img2 = new Imagem(2l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684280807/hqdefault_toeunz.jpg");
		Imagem img3 = new Imagem(3l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684280727/FLgQOu1XwAcvIHe_cf0zzg.jpg");
		Imagem img4 = new Imagem(4l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684280650/81RfK8Xrm8L._CR0_204_1224_1224_UX256_sz0gtf.jpg");
		Imagem img5 = new Imagem(5l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684280546/cuphead-fisica_dacyoh.jpg");
		Imagem img6 = new Imagem(6l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684280377/cvsn71i0owbqkbhj65lc.jpg");
		Imagem img7 = new Imagem(7l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684280232/s-l1600_ujpozn.jpg");
		Imagem img8 = new Imagem(8l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684280807/hqdefault_toeunz.jpg");
		Imagem img9 = new Imagem(9l, "GTA V.png", "image/png", 516994L, "xj7ero5ay2bhwajzem1c",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198138/library_600x900_2x_ggivsh.jpg");
		Imagem img10 = new Imagem(10l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684197894/library_600x900_2x_dece3p.jpg");
		Imagem img11 = new Imagem(11l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198209/library_600x900_2x_zgrdla.jpg");
		Imagem img12 = new Imagem(12l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198284/library_600x900_2x_fscgev.jpg");
		Imagem img13 = new Imagem(13l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198378/library_600x900_2x_dmsip6.jpg");
		Imagem img14 = new Imagem(14l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198444/library_600x900_2x_oeitkb.jpg");
		Imagem img15 = new Imagem(15l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198627/6f39cf2c11945e47f55e60fa895d5626_mvj8hh.jpg");
		Imagem img16 = new Imagem(16l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684199497/library_600x900_2x_sq4mks.jpg");
		Imagem img17 = new Imagem(17l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198731/bbe3ee49261344a75b4ac2bdec2da19b_wgzyao.jpg");
		Imagem img18 = new Imagem(18l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198828/library_600x900_2x_s1iku6.jpg");
		Imagem img19 = new Imagem(19l, "CSGO.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684197894/library_600x900_2x_dece3p.jpg");
		Imagem img20 = new Imagem(20l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198209/library_600x900_2x_zgrdla.jpg");
		Imagem img21 = new Imagem(21l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198138/library_600x900_2x_ggivsh.jpg");
		Imagem img22 = new Imagem(22l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198284/library_600x900_2x_fscgev.jpg");
		Imagem img23 = new Imagem(23l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684198378/library_600x900_2x_dmsip6.jpg");
		Imagem img24 = new Imagem(24l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684199497/library_600x900_2x_sq4mks.jpg");
		Imagem img25 = new Imagem(25l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684281630/library_600x900_2x_e5xrrz.jpg");
		Imagem img26 = new Imagem(26l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684282231/e4fdf2676a5d6fee5b206603258d15a2_bizgqy.jpg");
		Imagem img27 = new Imagem(27l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684283132/library_600x900_2x_qspoct.jpg");
		Imagem img28 = new Imagem(28l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684283644/library_600x900_2x_w82qdz.jpg");
		Imagem img29 = new Imagem(29l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684284023/library_600x900_2x_yts7tw.jpg");
		Imagem img30 = new Imagem(30l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://cdn2.steamgriddb.com/file/sgdb-cdn/thumb/0ed5055450adbd836945761a6fa43ee0.jpg");
		Imagem img31 = new Imagem(31l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684284989/6abd5c03e1414f4600add2d7956bb442_padw4t.png");
		Imagem img32 = new Imagem(32l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1684285328/8d9f1677ead632bcc3f1a48e6743d984_wciflb.jpg");
		Imagem img33 = new Imagem(33l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1685486759/df827da31d664707840bc6c221f22d72_f6hzth.png");
		Imagem img34 = new Imagem(34l, "ModeloNegocio.jpg", "image/jpeg", 70079L, "a2xbrbspxnprdd9oxx8v",
				"https://res.cloudinary.com/da1uhb7h7/image/upload/v1685486879/fd93fd1de50e084dd7d3b0b9f6950450_uexfgy.png");

		imagemRepository.saveAll(Arrays.asList(img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11,
				img12, img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25, img26,
				img27, img28, img29, img30, img31, img32, img33, img34));

		Produto p1 = new Produto(1L, "Grand Theft Auto V", 50,
				"Grand Theft Auto V é um jogo eletrônico de ação-aventura desenvolvido pela Rockstar North e publicado pela Rockstar Games.",
				img9);
		Produto p2 = new Produto(2L, "Counter-Strike: Global Offensive", 20,
				"Counter-Strike: Global Offensive é um jogo online desenvolvido pela Valve Corporation e pela Hidden Path Entertainment, sendo uma sequência de Counter-Strike: Source. É o quarto título principal da franquia.",
				img10);
		Produto p3 = new Produto(3L, "Outlast", 70,
				"O inferno é um experimento do qual não há escapatória em Outlast, um jogo de terror de sobrevivência em primeira pessoa da Red Barrels. Como o jornalista investigativo Miles Upshur, explore o Mount Massive Asylum e tente sobreviver tempo o bastante para",
				img11);
		Produto p4 = new Produto(4L, "Red Dead Redemption 2", 120,
				"Red Dead Redemption é uma série de jogos de vídeo game de ação e aventura no velho oeste americano aclamados pela crítica. ",
				img12);
		Produto p5 = new Produto(5L, "Elden Ring", 100,
				"Elden Ring é um jogo eletrônico de RPG de ação em terceira pessoa, desenvolvido pela FromSoftware e publicado pela Bandai Namco Entertainment.",
				img13);
		Produto p6 = new Produto(6L, "God of War", 230,
				"God of War é um jogo eletrônico de ação-aventura desenvolvido pela Santa Monica Studio e publicado pela Sony Interactive Entertainment.",
				img14);
		Produto p7 = new Produto(7L, "Tony Hawk's Pro Skater 1+2", 40,
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
		Produto p11 = new Produto(11L, "Resident Evil 4", 250,
				"Resident Evil 4 é o grande novo lançamento da Capcom, reimaginando o clássico de 2005. Jogue como Leon Kennedy em sua busca pela filha do presidente, enquanto luta contra criaturas aterrorizantes.",
				img25);
		Produto p12 = new Produto(12L, "The Legend of Zelda: Tears of the Kingdom", 300,
				"The Legend of Zelda: Tears of the Kingdom é o novo lançamento da Nintendo e um dos jogos mais aguardados do ano. Reviva o mundo de Hyrule após os eventos de Breath of the Wild com novos personagens, mecânicas e um vasto novo mundo.",
				img26);
		Produto p13 = new Produto(13L, "The King of Fighters XV", 100,
				"The King of Fighters, ou KOF para os mais familiarizados, é uma franquia de jogos de luta bastante popular na América do Sul. KOF XV é o jogo mais recente dessa franquia, com gráficos surpreendentes e gameplay enfatizada em técnica.",
				img27);
		Produto p14 = new Produto(14L, "Guilty Gear Strive", 100,
				"Nesse novo jogo da série Guilty Gear, famosa por seus gráficos estilizados em anime e lutas de rítmo acelerado, jogue com uma variedade de personagens antigos e novos, pensado para que jogadores novatos possam jogar sem dificuldades.",
				img28);
		Produto p15 = new Produto(15L, "Guilty Gear Xrd Rev 2", 40,
				"Um marco na indústria dos jogos de luta, jogue esse jogo da série Guilty Gear que se tornou um cult classic devido a seu gameplay acelerado, mecânicas com grande profundidade e excelente netcode para jogar online.",
				img29);
		Produto p16 = new Produto(16L, "Minecraft", 150,
				"Grande clássico dos videogames e jogo mais vendido da história. Solte sua criatividade e explore lugares incríveis em um mundo feito de blocos, sozinho ou com amigos. Aqui sempre tem algo a fazer!",
				img30);
		Produto p17 = new Produto(17L, "Forza Horizon 5", 150,
				"Forza Horizon 5 é um jogo eletrônico de corrida desenvolvido pela Playground Games e publicado pela Xbox Game Studios.",
				img31);
		Produto p18 = new Produto(18L, "Mario Kart 8 Deluxe", 150,
				"Em Mario Kart 8 Deluxe, jogue com vários personagens da franquia Mario, em pistas bastante diversas que incluem seções submersas e antigravidade para o gameplay caótico pelo qual a série é conhecida.",
				img32);
		Produto p19 = new Produto(19L, "Watch Dogs", 150,
				"Em Watch Dogs, a história segue um homem chamado Aiden Pearce, um hacker que, devido a uma tragédia familiar violenta, procura fazer sua própria justiça para com os culpados manipulando o Sistema de Operação Central.",
				img33);
		Produto p20 = new Produto(20L, "God of War Ragnarök", 150,
				"God of War Ragnarök é um jogo eletrônico de ação-aventura desenvolvido pela Santa Monica Studio e publicado pela Sony Interactive Entertainment. Foi lançado em 9 de novembro de 2022 para PlayStation 4 e PlayStation 5.",
				img34);

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16,
				p17, p18, p19, p20));

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
		Anuncio an5 = new Anuncio(5L, "Cuphead midia física Xbox One", "Jogo novo, quase sem utilização", 100.50, 5, 4,
				true, u2, img5, "Aventura");
		Anuncio an6 = new Anuncio(6L, "Red Dead Redemption 2 semi-novo", "Jogo novo, quase sem utilização", 150.50, 5,
				1, true, u3, img6, "Ação");
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
		Comentario cm8 = new Comentario(8L, "Horrivel!", 1.0, u1, an2);
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
