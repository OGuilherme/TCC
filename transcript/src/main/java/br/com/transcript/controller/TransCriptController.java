package br.com.transcript.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransCriptController {
	
	@RequestMapping(value = "/pagina-inicial")
	public String PagianInicial() {
		return "home-page";
	}
}
