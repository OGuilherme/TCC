package br.com.transcript.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.transcript.bean.ArquivoBean;
import br.com.transcript.converter.ArquivoConverter;
import br.com.transcript.dao.ArquivoDAO;

@Component
public class ArquivoBusiness {
	
	@Autowired
	private ArquivoDAO arquivoDAO;
	
	@Autowired
	private ArquivoConverter arquivoConverter;

	public void salvarArquivo(ArquivoBean arquivoBean) {
		arquivoBean.setData(new Date());
		arquivoDAO.insert(arquivoConverter.convertBeanToEntity(arquivoBean));
	}
}
