package br.com.transcript.bean;

import org.springframework.stereotype.Component;

@Component
public class DadosBean {
	private int id;
	private String nome;
	private int idUsuario;
	private String usuario;
	private int idPermitido;
	private String de;
	private String para;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getIdPermitido() {
		return idPermitido;
	}

	public void setIdPermitido(int idPermitido) {
		this.idPermitido = idPermitido;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

}
