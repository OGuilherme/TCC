package br.com.transcript.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.transcript.bean.ArquivoBean;
import br.com.transcript.bean.DadosBean;
import br.com.transcript.bean.PermissoesBean;
import br.com.transcript.bean.UsuarioBean;
import br.com.transcript.converter.ArquivoConverter;
import br.com.transcript.converter.UsuarioConverter;
import br.com.transcript.dao.PermissoesDAO;
import br.com.transcript.entity.PermissoesEntity;

@Component
public class PermissoesBusiness {

	@Autowired
	private UsuarioConverter usuarioConverter;
	
	@Autowired
	private ArquivoConverter arquivoConverter;
	
	@Autowired
	private PermissoesDAO permissoesDAO;
	
	@Transactional
	public void inserir(PermissoesBean permissoes) {
		PermissoesEntity entity = new PermissoesEntity();
		entity.setIdArquivo(arquivoConverter.convertBeanToEntity(permissoes.getIdArquivo()));
		entity.setIdPermissao(usuarioConverter.convertBeanToEntity(permissoes.getIdPermissao()));
		entity.setIdPermitido(usuarioConverter.convertBeanToEntity(permissoes.getIdPermitido()));
		
		permissoesDAO.insert(entity);
	}

	@Transactional
	public List<UsuarioBean> obterPermitidos(Integer idArquivo) {
		List<PermissoesEntity> entity = permissoesDAO.findByNamedQuery("obterPermitidos", idArquivo);
		List<UsuarioBean> beans = new ArrayList<UsuarioBean>();
		for (PermissoesEntity permissoesEntity : entity) {
			UsuarioBean bean = usuarioConverter.convertEntityToBean(permissoesEntity.getIdPermitido());
			beans.add(bean);
		}
		return beans;
	}

	@Transactional
	public String removerPermissao(DadosBean dados) {
		List<PermissoesEntity> entity = permissoesDAO.findByNamedQuery("obterPermitido", dados.getId(), dados.getIdPermitido());
		permissoesDAO.remove(entity.get(0));
		
		return entity.get(0).getIdPermitido().getNome();
	}

	@Transactional
	public List<ArquivoBean> obterArquivosPermitidos(UsuarioBean usuario, Integer id) {
		List<PermissoesEntity> entities = permissoesDAO.findByNamedQuery("obterArquivoPermitido", id, usuario.getId());
		List<ArquivoBean> arquivos = new ArrayList<ArquivoBean>();
		for (PermissoesEntity permissoesEntity : entities) {
			ArquivoBean bean = arquivoConverter.convertEntityToBean(permissoesEntity.getIdArquivo());
			arquivos.add(bean);
		}
		return arquivos;
	}

	@Transactional
	public void excluirPermissoes(Integer idUsuario) {
		List<PermissoesEntity> entity = permissoesDAO.findByNamedQuery("obterPermissao", idUsuario);
		for (PermissoesEntity permissoesEntity : entity) {
			permissoesDAO.remove(permissoesEntity);
		}
	}
	
	@Transactional
	public void excluirPermissao(Integer idArquivo) {
		List<PermissoesEntity> entity = permissoesDAO.findByNamedQuery("obterPermitidos", idArquivo);
		if(entity != null && entity.size() > 0){
			for (PermissoesEntity permissoesEntity : entity) {
				permissoesDAO.remove(permissoesEntity);
			}
		}
	}
}
