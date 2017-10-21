package br.com.transcrip.business;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.transcrip.bean.ArquivoBean;
import br.com.transcrip.bean.DadosBean;
import br.com.transcrip.converter.ArquivoConverter;
import br.com.transcrip.converter.UsuarioConverter;
import br.com.transcrip.dao.ArquivoDAO;
import br.com.transcrip.entity.ArquivoEntity;

@Component
public class ArquivoBusiness {

	@Autowired
	private ArquivoDAO arquivoDAO;

	@Autowired
	private ArquivoConverter arquivoConverter;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@Autowired
	PermissoesBusiness permissoesBusiness;

	@Transactional
	public void salvarArquivo(ArquivoBean arquivoBean) {
		arquivoBean.setData(new Date());
		ArquivoEntity entity = arquivoConverter.convertBeanToEntity(arquivoBean);
		entity.setUsuario(usuarioConverter.convertBeanToEntity(arquivoBean.getUsuario()));
		arquivoDAO.insert(entity);
	}

	@Transactional
	public List<ArquivoBean> obterTodos(int idUsuario) {
		List<ArquivoEntity> entity = arquivoDAO.findByNamedQuery("arquivosUsuario", idUsuario);
		List<ArquivoBean> arquivos = arquivoConverter.convertEntityToBean(entity);

		for (int i = 0; i < arquivos.size(); i++) {
			arquivos.get(i).setUsuario(usuarioConverter.convertEntityToBean(entity.get(i).getUsuario()));
		}

		return arquivos;
	}

	@Transactional
	public void excluirTodos(Integer idUsuario) {
		permissoesBusiness.excluirPermissoes(idUsuario);
		List<ArquivoEntity> entity = arquivoDAO.findByNamedQuery("arquivosUsuario", idUsuario);
		for (ArquivoEntity arquivoEntity : entity) {
			arquivoDAO.remove(arquivoEntity);
		}
	}

	@Transactional
	public ArquivoBean obterPorId(Integer id) {
		ArquivoEntity entity = arquivoDAO.findById(id);
		ArquivoBean bean = arquivoConverter.convertEntityToBean(entity);
		bean.setUsuario(usuarioConverter.convertEntityToBean(entity.getUsuario()));
		return bean;
	}

	public void excluirArquivos(ArquivoBean arquivo) {
		String caminho = arquivo.getCaminho();
		File dir = new File(caminho);
		dir.delete();
	}

	@Transactional
	public void excluirArquivo(ArquivoBean arquivoBean) {
		permissoesBusiness.excluirPermissao(arquivoBean.getId());
		ArquivoEntity entity = arquivoDAO.findById(arquivoBean.getId());

		arquivoDAO.remove(entity);
	}

	public List<ArquivoBean> filtrar(DadosBean dados) {
		String nmArquivo = "%%";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date de = new Date();
		Date para = new Date();
		
		try {
			de = new java.sql.Date(format.parse("2000-01-01").getTime());
			para = new java.sql.Date(format.parse("2099-12-31").getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (dados.getNome() != null && !dados.getNome().equals("")) {
			nmArquivo = "%" + dados.getNome() + "%";
		}
		if (dados.getDe() != null && !dados.getDe().equals("")) {
			try {
				de = new java.sql.Date(format.parse(dados.getDe()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (dados.getPara() != null && !dados.getPara().equals("")) {
			try {
				para = new java.sql.Date(format.parse(dados.getPara()).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		List<ArquivoEntity> entity = arquivoDAO.findByNamedQuery("filtro", nmArquivo, de, para, dados.getIdUsuario());
		List<ArquivoBean> beans = arquivoConverter.convertEntityToBean(entity);

		return beans;
	}
}
