package br.com.transcrip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Arquivo")
@NamedQueries({ @NamedQuery(name = "arquivosUsuario", query = "SELECT a FROM ArquivoEntity a WHERE a.usuario.id = ?1"),
		@NamedQuery(name = "filtro", query = "SELECT a FROM ArquivoEntity a WHERE a.nome like (?1) AND a.data >= ?2 AND a.data <= ?3"
				+ " AND a.usuario.id = ?4") })

// SELECT * FROM Arquivo where nome like ('%%') AND data BETWEEN '2016-08-20'
// AND '2019-08-20'
public class ArquivoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "caminho")
	private String caminho;

	@Column(name = "tamanho")
	private Double tamanho;

	@Column(name = "data")
	@Temporal(value = TemporalType.DATE)
	private Date data;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUsuario")
	private UsuarioEntity usuario;

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

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public Double getTamanho() {
		return tamanho;
	}

	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

}
