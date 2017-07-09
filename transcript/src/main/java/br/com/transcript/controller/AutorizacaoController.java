package br.com.transcript.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/autorizacoes")
public class AutorizacaoController {

	@GetMapping
	public String autorizarUsuario() {
		return "autorizar-usuarios";
	}
	
}
