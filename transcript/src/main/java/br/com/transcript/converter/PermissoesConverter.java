package br.com.transcript.converter;

import org.springframework.stereotype.Component;

import br.com.transcript.bean.ArquivoBean;
import br.com.transcript.bean.PermissoesBean;
import br.com.transcript.entity.ArquivoEntity;
import br.com.transcript.entity.PermissoesEntity;

@Component
public class PermissoesConverter implements Converter<PermissoesEntity, PermissoesBean> {

	@Override
	public PermissoesEntity convertBeanToEntity(PermissoesBean bean) {
		// TODO Auto-generated method stub
		if (bean == null) {
			return null;
		}

		PermissoesEntity entity = new PermissoesEntity();

		entity.setId(bean.getId());

		return entity;
	}

	@Override
	public PermissoesBean convertEntityToBean(PermissoesEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			return null;
		}

		PermissoesBean bean = new PermissoesBean();

		bean.setId(entity.getId());

		return bean;

	}

}
