package br.com.transcrip.converter;

import org.springframework.stereotype.Component;

import br.com.transcrip.bean.UsuarioBean;
import br.com.transcrip.entity.UsuarioEntity;

@Component
public class UsuarioConverter implements Converter<UsuarioEntity, UsuarioBean> {

	@Override
	public UsuarioEntity convertBeanToEntity(UsuarioBean bean) {
		// TODO Auto-generated method stub
		if (bean == null) {
			return null;
		}
		UsuarioEntity entity = new UsuarioEntity();

		entity.setId(bean.getId());
		entity.setNome(bean.getNome());
		entity.setEmail(bean.getEmail());
		entity.setUsuario(bean.getUsuario());
		entity.setSenha(bean.getSenha());

		return entity;
	}

	@Override
	public UsuarioBean convertEntityToBean(UsuarioEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			return null;
		}
		UsuarioBean bean = new UsuarioBean();

		bean.setId(entity.getId());
		bean.setNome(entity.getNome());
		bean.setEmail(entity.getEmail());
		bean.setUsuario(entity.getUsuario());
		bean.setSenha(entity.getSenha());

		return bean;
	}

}