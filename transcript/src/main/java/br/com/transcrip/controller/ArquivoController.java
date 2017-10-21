package br.com.transcrip.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.transcrip.bean.ArquivoBean;
import br.com.transcrip.bean.DadosBean;
import br.com.transcrip.bean.PermissoesBean;
import br.com.transcrip.bean.UsuarioBean;
import br.com.transcrip.business.ArquivoBusiness;
import br.com.transcrip.business.PermissoesBusiness;
import br.com.transcrip.business.UsuarioBusiness;
import br.com.transcrip.exception.BusinessException;
import br.com.transcrip.util.CriptografiaArquivos;

@Controller
@RequestMapping(value = "/arquivo")
public class ArquivoController {

	@Autowired
	private ArquivoBusiness arquivoBusiness;

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private PermissoesBusiness permissoesBusiness;
	
	@Autowired
	private HttpSession session;

	@GetMapping(value = "/enviar")
	public String enviarArquivo() {
		return "enviar-arquivo";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvarArquivo(@ModelAttribute("arquivoBean") ArquivoBean arquivoBean, BindingResult result,
			@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttrs)
			throws BusinessException {
		String caminho = uploadArquivo(file, arquivoBean.getUsuario().getId());
		arquivoBean.setCaminho(caminho);
		Long kb = file.getSize();
		arquivoBean.setTamanho(kb.doubleValue());
		arquivoBusiness.salvarArquivo(arquivoBean);
		redirectAttrs.addFlashAttribute("sucesso", "Arquivo salvo com sucesso!");
		return "redirect:/arquivo/enviar";
	}

	private String uploadArquivo(MultipartFile file, int idUsuario) {
		CriptografiaArquivos cript = new CriptografiaArquivos();
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				File dir = new File("arquivo/" + idUsuario + File.separator);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				File dir2 = new File("arquivo/" + idUsuario + File.separator + "Criptografado"+ File.separator);
				if (!dir2.exists()) {
					dir2.mkdirs();
				}
				
				String caminho = dir.getAbsolutePath()+ File.separator + "Criptografado" + File.separator + file.getOriginalFilename();
				
				cript.Cript(serverFile.getAbsolutePath(), caminho, "transcrip", 'c');
				
				serverFile.delete();
				
				return caminho;
			} catch (Exception e) {
				return "";
			}
		} else {
			return "";
		}
	}

	@RequestMapping(value = "/meus-arquivos/{id}", method = RequestMethod.GET)
	public String meusArquivos(Model model, @PathVariable Integer id) {
		List<ArquivoBean> arquivos = arquivoBusiness.obterTodos(id);
		model.addAttribute("arquivos", arquivos);
		return "meus-arquivos";
	}

	@RequestMapping(value = "/autorizar/{id}", method = RequestMethod.GET)
	public String autorizar(Model model, @PathVariable Integer id) {
		ArquivoBean arquivo = arquivoBusiness.obterPorId(id);
		model.addAttribute("arquivo", arquivo);
		return "autorizar-usuarios";
	}

	@RequestMapping(value = "/confirmarAutorizacao", method = RequestMethod.POST)
	public String confirmarAutorizacao(RedirectAttributes redirects, DadosBean dados) {
		ArquivoBean arquivo = arquivoBusiness.obterPorId(dados.getId());
		
		UsuarioBean usuario = new UsuarioBean();
		usuario.setUsuario(dados.getUsuario());
		List<UsuarioBean> usuarios = usuarioBusiness.consultarUsuarios(usuario);
		
		if (usuarios != null && usuarios.size() > 0) {
			List<UsuarioBean> usuariosPermitidos = permissoesBusiness.obterPermitidos(arquivo.getId());
			for (UsuarioBean usuarioBean : usuariosPermitidos) {
				if(usuarioBean.getId() == usuarios.get(0).getId()){
					redirects.addFlashAttribute("log",
							"Usuario: " + dados.getUsuario() + " já possui autorização");
					return "redirect:/arquivo/autorizar/"+dados.getId();
				}
			}
			
			PermissoesBean permissoes = new PermissoesBean();
			permissoes.setIdArquivo(arquivo);
			permissoes.setIdPermissao(arquivo.getUsuario());
			permissoes.setIdPermitido(usuarios.get(0));

			permissoesBusiness.inserir(permissoes);
			redirects.addFlashAttribute("log",
					"Usuario: " + dados.getUsuario() + " foi autorizado com sucesso");
			return "redirect:/arquivo/meus-arquivos/"+dados.getIdUsuario();
		} else {
			redirects.addFlashAttribute("log", "Usuario: " + arquivo.getUsuario().getUsuario() + " não foi localizado");
			return "redirect:/arquivo/autorizar/" + arquivo.getId();
		}
	}
	
	@RequestMapping(value = "/remover-autorizacao/{id}", method = RequestMethod.GET)
	public String removerAutorizacao(Model model, @PathVariable Integer id) {
		ArquivoBean arquivo = arquivoBusiness.obterPorId(id);
		model.addAttribute("arquivo", arquivo);
		
		List<UsuarioBean> usuarios = permissoesBusiness.obterPermitidos(id);
		model.addAttribute("usuarios", usuarios);
		
		return "desautorizar-usuario";
	}
	
	@RequestMapping(value = "/confirmarRemover", method = RequestMethod.POST)
	public String confirmarRemover(Model model, DadosBean dados) {
		String nome = permissoesBusiness.removerPermissao(dados);
		
		model.addAttribute("log", "Usuário "+nome+" não tem mais permissão para ver o arquivo "+dados.getNome());
		
		return "desautorizar-usuario";
	}
	
	@RequestMapping(value = "/consultar/{id}", method = RequestMethod.GET)
	public String consultar(Model model, @PathVariable Integer id) {
		UsuarioBean usuario = (UsuarioBean) session.getAttribute("usuarioLogado");
		List<ArquivoBean> arquivos = permissoesBusiness.obterArquivosPermitidos(usuario, id);
		
		model.addAttribute("arquivos", arquivos);
		return "meus-arquivos";
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(String caminho, HttpServletResponse response) {
		CriptografiaArquivos cript = new CriptografiaArquivos();
		String[] nome = caminho.split("\\\\");
		try {

			File dir2 = new File("arquivo/" + nome[nome.length-3] + File.separator);
			if (!dir2.exists()) {
				dir2.mkdirs();
			}
			
			String caminhoDesc = dir2.getAbsolutePath()+ File.separator + nome[nome.length-1];
			
			cript.Cript(caminho, caminhoDesc, "transcrip", 'd');
			
			File file = new File(caminhoDesc);
			response.addHeader("Content-Disposition", "attachment; filename=" + nome[nome.length-1]);
			InputStream is = new FileInputStream(file);
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();

			is.close();
			
			file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/downloadCriptografado", method = RequestMethod.GET)
	public void downloadCriptografado(String caminho, HttpServletResponse response) {
		String[] nome = caminho.split("\\\\");
		try {
			File file = new File(caminho);
			response.addHeader("Content-Disposition", "attachment; filename=" + nome[nome.length-1]);
			InputStream is = new FileInputStream(file);
			org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/excluir-arquivo/{id}", method = RequestMethod.GET)
	public String excluirArquivo(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
		ArquivoBean arquivo = arquivoBusiness.obterPorId(id);
		arquivoBusiness.excluirArquivos(arquivo);
		arquivoBusiness.excluirArquivo(arquivo);
		
		redirectAttrs.addFlashAttribute("log", "Arquivo "+ arquivo.getNome() +" excluido com sucesso");
		return "redirect:/arquivo/meus-arquivos/"+arquivo.getUsuario().getId();
	}
	
	@RequestMapping(value = "/meus-arquivos/filtrar", method = RequestMethod.POST)
	public String filtarArquivos(Model model, DadosBean dados){
		List<ArquivoBean> arquivos = arquivoBusiness.filtrar(dados);
		
		model.addAttribute("arquivos", arquivos);
		return "meus-arquivos";
	}
}