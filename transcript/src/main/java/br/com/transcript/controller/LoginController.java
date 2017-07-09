package br.com.transcript.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.transcript.bean.UsuarioBean;
import br.com.transcript.business.UsuarioBusiness;
import br.com.transcript.exception.BusinessException;

@Controller
@RequestMapping(value = "login")
public class LoginController {
	
	@Autowired
	private UsuarioBusiness usuarioBusiness;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public String autenticar(UsuarioBean usuarioBean, RedirectAttributes redirectAttrs) {
		UsuarioBean usuario = new UsuarioBean();
		try {
			usuario = usuarioBusiness.autenticar(usuarioBean);
			if(usuario.getAutenticado()){
				session.setAttribute("usuarioLogado", usuario);
				return "redirect:/pagina-inicial";
			}
		} catch (BusinessException ex) {
			redirectAttrs.addFlashAttribute("log", ex.getMessage());
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value="/registrar", method = RequestMethod.POST)
	public String registrar(UsuarioBean usuarioBean, RedirectAttributes redirectAttrs){
		try {
			usuarioBusiness.registrar(usuarioBean);
			if (usuarioBean.getId() == null) {
				redirectAttrs.addFlashAttribute("log1", "Inscrito com sucesso!");
			}
			
		} catch (BusinessException e) {
			redirectAttrs.addFlashAttribute("log2", e.getMessage());
		}
		
		return "redirect:/login";
	}

}
