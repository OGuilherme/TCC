package br.com.transcrip.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.transcrip.bean.UsuarioBean;
import br.com.transcrip.business.UsuarioBusiness;
import br.com.transcrip.exception.BusinessException;

@Controller
@RequestMapping(value = "/login")
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
				return "redirect:/arquivo/meus-arquivos/"+usuario.getId();
			}
		} catch (BusinessException ex) {
			redirectAttrs.addAttribute("log2", ex.getMessage());
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value="/registrar", method = RequestMethod.POST)
	public String registrar(UsuarioBean usuarioBean, Model redirectAttrs){
		try {
			usuarioBusiness.registrar(usuarioBean);
			redirectAttrs.addAttribute("log1", "Inscrito com sucesso!");
		} catch (BusinessException e) {
			redirectAttrs.addAttribute("log2", e.getMessage());
		}
		
		return "/login";
	}

	@RequestMapping(value="/recuperarSenha", method = RequestMethod.POST)
	public String recuperarSenha(UsuarioBean usuarioBean, Model redirectAttrs){
		List<UsuarioBean> usuarios = usuarioBusiness.consultarUsuarios(usuarioBean);
		if(usuarios != null && usuarios.size() > 0){
			usuarioBusiness.recuperarSenha(usuarios.get(0));
			redirectAttrs.addAttribute("log1", "Nova senha encaminha para o e-mail: "+usuarioBean.getEmail());
		}else{
			redirectAttrs.addAttribute("log2", "E-mail: "+usuarioBean.getEmail() +" não localizado");
		}
		return "/login";
	}
}
