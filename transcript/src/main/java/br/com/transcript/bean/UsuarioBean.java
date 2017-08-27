package br.com.transcript.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UsuarioBean {
	private Integer id;
	private String nome;
	private String usuario;
	private String email;
	private String senha;
	private List<ArquivoBean> arquivos;
	private Boolean autenticado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAutenticado() {
		return autenticado;
	}

	public void setAutenticado(Boolean autenticado) {
		this.autenticado = autenticado;
	}

	public List<ArquivoBean> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ArquivoBean> arquivos) {
		this.arquivos = arquivos;
	}

}
