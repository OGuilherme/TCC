package br.com.transcript.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.transcript.bean.ArquivoBean;
import br.com.transcript.bean.UsuarioBean;
import br.com.transcript.business.ArquivoBusiness;
import br.com.transcript.exception.BusinessException;

@Controller
@RequestMapping(value = "/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoBusiness arquivoBusiness;

	@GetMapping(value = "/enviar")
	public String enviarArquivo() {
		return "enviar-arquivo";
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarArquivo(@ModelAttribute("arquivoBean") ArquivoBean arquivoBean,
			BindingResult result, @RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redirectAttrs) throws BusinessException {
			String caminho = uploadArquivo(file);
			arquivoBean.setCaminho(caminho);
			Long kb = file.getSize();
			arquivoBean.setTamanho(kb.doubleValue());
			arquivoBusiness.salvarArquivo(arquivoBean);
			redirectAttrs.addFlashAttribute("sucesso", "Arquivo salvo com sucesso!");
		return "redirect:/arquivo/enviar";
	}
	
	private String uploadArquivo(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				File dir = new File("arquivo" + File.separator);
				if (!dir.exists()){
					dir.mkdirs();
				}
				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				return serverFile.getAbsolutePath();
			} catch (Exception e) {
				return "";
			}
		} else {
			return "";
		}
	}
	
	@GetMapping(value = "/meus-arquivos")
	public String meusArquivos() {
		return "meus-arquivos";
	}
}
