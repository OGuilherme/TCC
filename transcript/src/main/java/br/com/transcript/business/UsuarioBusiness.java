package br.com.transcript.business;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.transcript.bean.UsuarioBean;
import br.com.transcript.converter.ArquivoConverter;
import br.com.transcript.converter.UsuarioConverter;
import br.com.transcript.dao.UsuarioDAO;
import br.com.transcript.entity.UsuarioEntity;
import br.com.transcript.exception.BusinessException;
import br.com.transcript.util.EncriptaDecriptaApacheCodec;
import br.com.transcript.util.GeradorDeSenha;
import br.com.transcript.util.GeradorEmail;

@Component
public class UsuarioBusiness {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioConverter usuarioConverter;
	
	@Autowired
	private ArquivoBusiness arquivoBusiness;
	
	@Autowired
	private ArquivoConverter arquivoConverter;

	EncriptaDecriptaApacheCodec codec = new EncriptaDecriptaApacheCodec();
	
	@Transactional
	public UsuarioBean autenticar(UsuarioBean usuarioBean) throws BusinessException {
		List<UsuarioEntity> usuarios = usuarioDAO.findByNamedQuery("obterPorEmail", usuarioBean.getEmail());
		
		usuarioBean.setSenha(codec.codificaBase64Encoder(usuarioBean.getSenha()));

		if (usuarios != null && usuarios.size() > 0) {
			for (UsuarioEntity usuarioEntity : usuarios) {
				if (usuarioBean.getSenha().equals(usuarioEntity.getSenha())) {
					usuarioBean = usuarioConverter.convertEntityToBean(usuarioEntity);
					usuarioBean.setAutenticado(true);
				}else{
					usuarioBean.setAutenticado(false);
				}
			}
			if (!usuarioBean.getAutenticado()) {
				throw new BusinessException("Senha inválida");
			}
		}else{
			throw new BusinessException("Usuário inválido");
		}
		return usuarioBean;
	}

	@Transactional
	public void registrar(UsuarioBean usuarioBean) throws BusinessException  {
		usuarioBean.setSenha(codec.codificaBase64Encoder(usuarioBean.getSenha()));

		UsuarioEntity entity = usuarioConverter.convertBeanToEntity(usuarioBean);

		if (usuarioDAO.existeUsuario(entity.getUsuario())) {
			throw new BusinessException(
					"Usuário " + entity.getUsuario() + " já está cadastrado.");
		}else if (usuarioDAO.existeEmail(entity.getEmail())) {
			throw new BusinessException(
					"E-mail " + entity.getEmail() + " já está cadastrado.");
		}
		usuarioDAO.insert(entity);
		
		enviarEmail(usuarioConverter.convertEntityToBean(entity), "cadastro");
	}

	@Transactional
	public List<UsuarioBean> consultarUsuarios(UsuarioBean usuarioBean) {
		List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
		if(usuarioBean.getUsuario() != null && usuarioBean.getEmail() != null &&
				!usuarioBean.getUsuario().equals("") && !usuarioBean.getEmail().equals("")){
			usuarios = usuarioDAO.findByNamedQuery("obterPorUsuarioEmail", usuarioBean.getUsuario(), usuarioBean.getEmail());
		}else if(usuarioBean.getUsuario() != null && !usuarioBean.getUsuario().equals("")){
			usuarios = usuarioDAO.findByNamedQuery("obterPorUsuario", usuarioBean.getUsuario());
		}else if(usuarioBean.getEmail() != null && !usuarioBean.getEmail().equals("")){
			usuarios = usuarioDAO.findByNamedQuery("obterPorEmail", usuarioBean.getEmail());
		}
		return usuarioConverter.convertEntityToBean(usuarios);
	}

	@Transactional
	public void atualizarDados(UsuarioBean usuarioBean) {
		UsuarioEntity entity = usuarioDAO.findById(usuarioBean.getId());
		if(usuarioBean.getNome() != entity.getNome()){
			entity.setNome(usuarioBean.getNome());
		}
		if(usuarioBean.getUsuario() != entity.getUsuario()){
			entity.setUsuario(usuarioBean.getUsuario());
		}
		if(usuarioBean.getSenha() != null && !usuarioBean.getSenha().equals("")){
			usuarioBean.setSenha(codec.codificaBase64Encoder(usuarioBean.getSenha()));
			entity.setSenha(usuarioBean.getSenha());
		}
		usuarioBean.setEmail(entity.getEmail());
		usuarioDAO.update(entity);
	}

	@Transactional
	public void excluirConta(UsuarioBean usuarioBean) {
		arquivoBusiness.excluirTodos(usuarioBean.getId());
		UsuarioEntity entity = usuarioDAO.findById(usuarioBean.getId());
		usuarioDAO.remove(entity);
	}

	@Transactional
	public UsuarioBean obterPorId(Integer id) {
		UsuarioEntity entity = usuarioDAO.findById(id);
		UsuarioBean bean = usuarioConverter.convertEntityToBean(entity);
		bean.setArquivos(arquivoConverter.convertEntityToBean(entity.getArquivos()));
		return bean;
	}

	@Transactional
	public List<UsuarioBean> obertPermiditos(Integer idArquivo) {
		List<UsuarioEntity> entity = usuarioDAO.findByNamedQuery("obterPermitidos", idArquivo);
		return usuarioConverter.convertEntityToBean(entity);
	}

	@Transactional
	public void recuperarSenha(UsuarioBean usuarioBean) {
		GeradorDeSenha gerar = new GeradorDeSenha();
		UsuarioEntity entity = usuarioDAO.findById(usuarioBean.getId());
		entity.setSenha(codec.codificaBase64Encoder(gerar.recuperarSenha()));
		
		usuarioDAO.update(entity);
		
		enviarEmail(usuarioConverter.convertEntityToBean(entity), "recuperarSenha");
	}
	
	public void enviarEmail(UsuarioBean usuario, String acao){
		GeradorEmail email = new GeradorEmail();
		try {
			email.enviarEmail(usuario, acao);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
