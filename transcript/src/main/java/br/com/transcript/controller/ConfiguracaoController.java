package br.com.transcript.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.transcript.bean.UsuarioBean;

@Controller
@RequestMapping(value = "/configuracao")
public class ConfiguracaoController {

	@GetMapping
	public String configuracoes() {
		return "configuracoes";
	}
	
	@RequestMapping(value = "/excluirConta", method = RequestMethod.POST)
	public void excluirConta(UsuarioBean usuarioBean, RedirectAttributes redirectAttrs) {
	}
	
	@RequestMapping(value = "/atualizarConta", method = RequestMethod.POST)
	public String atualizarConta(UsuarioBean usuarioBean, RedirectAttributes redirectAttrs) {
		return "redirect:/configuracao";
	}
}
