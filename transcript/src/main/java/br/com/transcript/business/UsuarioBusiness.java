package br.com.transcript.business;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.transcript.bean.UsuarioBean;
import br.com.transcript.converter.UsuarioConverter;
import br.com.transcript.dao.UsuarioDAO;
import br.com.transcript.entity.UsuarioEntity;
import br.com.transcript.exception.BusinessException;
import br.com.transcript.util.EncriptaDecriptaApacheCodec;

@Component
public class UsuarioBusiness {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@Transactional
	public UsuarioBean autenticar(UsuarioBean usuarioBean) throws BusinessException {
		List<UsuarioEntity> usuarios = usuarioDAO.findByNamedQuery("obterPorEmail", usuarioBean.getEmail());
		
		EncriptaDecriptaApacheCodec codec = new EncriptaDecriptaApacheCodec();
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
		EncriptaDecriptaApacheCodec codec = new EncriptaDecriptaApacheCodec();

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
	}

	public List<UsuarioBean> consultarUsuarios(UsuarioBean usuarioBean) {
		List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
		if(!usuarioBean.getUsuario().equals("") && !usuarioBean.getEmail().equals("")){
			usuarios = usuarioDAO.findByNamedQuery("obterPorUsuarioEmail", usuarioBean.getUsuario(), usuarioBean.getEmail());
		}else if(usuarioBean.getUsuario() != null){
			usuarios = usuarioDAO.findByNamedQuery("obterPorUsuario", usuarioBean.getUsuario());
		}else if(usuarioBean.getEmail() != null){
			usuarios = usuarioDAO.findByNamedQuery("obterPorEmail", usuarioBean.getEmail());
		}
		return usuarioConverter.convertEntityToBean(usuarios);
	}
}
