package br.com.transcrip.dao;

import org.springframework.stereotype.Repository;

import br.com.transcrip.entity.UsuarioEntity;

@Repository
public class UsuarioDAO  extends GenericDAOImpl<UsuarioEntity, Integer> {
	public Boolean existeUsuario(String usuario) {
		return !this.findByNamedQuery("obterPorUsuario", usuario).isEmpty();
	}

	public boolean existeEmail(String email) {
		return !this.findByNamedQuery("obterPorEmail", email).isEmpty();
	}
}
