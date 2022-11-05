package com.belval.avaliacaogames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PessoaController {

	@GetMapping("/pessoa")
	public String novo() {
		return "pessoa/form";
	}
}
