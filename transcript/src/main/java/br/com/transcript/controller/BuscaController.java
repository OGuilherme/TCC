package br.com.transcript.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.transcript.bean.UsuarioBean;
import br.com.transcript.business.UsuarioBusiness;

@Controller
@RequestMapping(value = "/busca")
public class BuscaController {
	
	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@GetMapping
	public String buscarUsuarios() {
		return "buscar-usuarios";
	}
	
	@RequestMapping(value = "/realizarBusca", method = RequestMethod.POST)
	public String realizarBusca(UsuarioBean usuarioBean, Model model) {
		List<UsuarioBean> usuarios = usuarioBusiness.consultarUsuarios(usuarioBean);
		model.addAttribute("usuarios", usuarios);
		return "/buscar-resultados";
	}
}