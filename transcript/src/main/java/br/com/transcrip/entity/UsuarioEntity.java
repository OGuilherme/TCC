package br.com.transcrip.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "usuario")
@NamedQueries({ @NamedQuery(name = "obterPorEmail", query = "SELECT u FROM UsuarioEntity u WHERE u.email like ?1"),
		@NamedQuery(name = "obterPorUsuario", query = "SELECT u FROM UsuarioEntity u WHERE u.usuario like ?1"),
		@NamedQuery(name = "obterPorUsuarioEmail", query = "SELECT u FROM UsuarioEntity u WHERE u.usuario like ?1 AND u.email like ?2")})
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<ArquivoEntity> arquivos;

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

	public List<ArquivoEntity> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ArquivoEntity> arquivos) {
		this.arquivos = arquivos;
	}

}
