package br.com.transcript.converter;

import org.springframework.stereotype.Component;

import br.com.transcript.bean.ArquivoBean;
import br.com.transcript.entity.ArquivoEntity;

@Component
public class ArquivoConverter implements Converter<ArquivoEntity, ArquivoBean> {

	@Override
	public ArquivoEntity convertBeanToEntity(ArquivoBean bean) {
		// TODO Auto-generated method stub
		if(bean == null){
			return null;
		}
		
		ArquivoEntity entity = new ArquivoEntity();
		
		entity.setId(bean.getId());
		entity.setNome(bean.getNome());
		entity.setCaminho(bean.getCaminho());
		entity.setTamanho(bean.getTamanho());
		entity.setData(bean.getData());
		
		return entity;
	}

	@Override
	public ArquivoBean convertEntityToBean(ArquivoEntity entity) {
		// TODO Auto-generated method stub
		if(entity == null){
			return null;
		}
		
		ArquivoBean bean = new ArquivoBean();
		
		bean.setId(entity.getId());
		bean.setNome(entity.getNome());
		bean.setCaminho(entity.getCaminho());
		bean.setTamanho(entity.getTamanho());
		bean.setData(entity.getData());
		
		return bean;
	}
}
