package br.com.transcrip.bean;

import org.springframework.stereotype.Component;

@Component
public class PermissoesBean {

	private Integer id;
	private UsuarioBean idPermissao;
	private UsuarioBean idPermitido;
	private ArquivoBean idArquivo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioBean getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(UsuarioBean idPermissao) {
		this.idPermissao = idPermissao;
	}

	public UsuarioBean getIdPermitido() {
		return idPermitido;
	}

	public void setIdPermitido(UsuarioBean idPermitido) {
		this.idPermitido = idPermitido;
	}

	public ArquivoBean getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(ArquivoBean idArquivo) {
		this.idArquivo = idArquivo;
	}

}
