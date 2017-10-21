package br.com.transcrip.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.transcrip.bean.ArquivoBean;
import br.com.transcrip.bean.UsuarioBean;
import br.com.transcrip.business.ArquivoBusiness;
import br.com.transcrip.business.UsuarioBusiness;

@Controller
@RequestMapping(value = "/configuracao")
public class ConfiguracaoController {

	@Autowired
	private UsuarioBusiness usuarioBusiness;
	
	@Autowired
	private ArquivoBusiness arquivoBusiness;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public String configuracoes() {
		return "configuracoes";
	}
	
	@RequestMapping(value = "/excluirConta", method = RequestMethod.POST)
	public String excluirConta(UsuarioBean usuarioBean, RedirectAttributes model) {
		usuarioBean = usuarioBusiness.obterPorId(usuarioBean.getId());
		for(ArquivoBean arquivo : usuarioBean.getArquivos()){
			arquivoBusiness.excluirArquivos(arquivo);
		}
		usuarioBusiness.excluirConta(usuarioBean);
		model.addFlashAttribute("log1", "Conta excluida com sucesso");
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/atualizarConta", method = RequestMethod.POST)
	public String atualizarConta(UsuarioBean usuarioBean, RedirectAttributes model) {
		usuarioBusiness.atualizarDados(usuarioBean);
		model.addAttribute("log", "Dados atualizados com sucesso");
		session.removeAttribute("usuarioLogado");
		session.setAttribute("usuarioLogado", usuarioBean);
		return "redirect:/configuracao";
	}
}
