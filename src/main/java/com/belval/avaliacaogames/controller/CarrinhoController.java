package com.belval.avaliacaogames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarrinhoController {

	@GetMapping("/usuario/{cpf}/carrinho")
	public String carrinho(@PathVariable("cpf") Long cpf) {
		return "carrinho/carrinho";
	}
}
