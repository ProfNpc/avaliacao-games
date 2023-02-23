package com.belval.avaliacaogames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TrocaController {

	@GetMapping("/usuario/{cpf}/trocas")
	public String minhasTrocas(@PathVariable("cpf") Long cpf) {
		return "";
	}
}
