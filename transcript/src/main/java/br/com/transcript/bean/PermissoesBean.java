package br.com.transcript.bean;

import org.springframework.stereotype.Component;

@Component
public class PermissoesBean {

	private Integer id;
	private UsuarioBean idPermissao;
	private UsuarioBean idPermitido;
	private UsuarioBean idArquivo;

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

	public UsuarioBean getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(UsuarioBean idArquivo) {
		this.idArquivo = idArquivo;
	}

}
