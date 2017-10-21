package br.com.transcrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "Permissoes")
@NamedQueries({
	@NamedQuery(name = "obterPermitidos", query = "SELECT p FROM PermissoesEntity p WHERE p.idArquivo.id = ?1"),
	@NamedQuery(name = "obterPermitido", query = "SELECT p FROM PermissoesEntity p WHERE p.idArquivo.id = ?1 AND p.idPermitido.id = ?2"),
	@NamedQuery(name = "obterArquivoPermitido", query = "SELECT p FROM PermissoesEntity p WHERE p.idPermissao.id = ?1 AND p.idPermitido.id = ?2"),
	@NamedQuery(name = "obterPermissao", query = "SELECT p FROM PermissoesEntity p WHERE p.idPermissao.id = ?1")
})
public class PermissoesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPermissao")
	private UsuarioEntity idPermissao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPermitido")
	private UsuarioEntity idPermitido;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idArquivo")
	private ArquivoEntity idArquivo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioEntity getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(UsuarioEntity idPermissao) {
		this.idPermissao = idPermissao;
	}

	public UsuarioEntity getIdPermitido() {
		return idPermitido;
	}

	public void setIdPermitido(UsuarioEntity idPermitido) {
		this.idPermitido = idPermitido;
	}

	public ArquivoEntity getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(ArquivoEntity idArquivo) {
		this.idArquivo = idArquivo;
	}
}
