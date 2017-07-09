package br.com.transcript.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Permissoes")
public class PermissoesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPermissao")
	private UsuarioEntity idPermissao;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPermitido")
	private List<UsuarioEntity> idPermitido;

	@OneToOne(fetch = FetchType.LAZY)
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

	public List<UsuarioEntity> getIdPermitido() {
		return idPermitido;
	}

	public void setIdPermitido(List<UsuarioEntity> idPermitido) {
		this.idPermitido = idPermitido;
	}

	public ArquivoEntity getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(ArquivoEntity idArquivo) {
		this.idArquivo = idArquivo;
	}
}
