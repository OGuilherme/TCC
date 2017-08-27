package br.com.transcript.util;

import br.com.transcript.bean.UsuarioBean;

public class Mensagem {
	
	EncriptaDecriptaApacheCodec codec = new EncriptaDecriptaApacheCodec();

	public String gerador(UsuarioBean usuario, String acao) {
		if(acao.equals("recuperarSenha")){
			String mensagem = "Olá " + usuario.getNome() 
							+ " ,\nSua nova senha é: " + codec.decodificaBase64Decoder(usuario.getSenha())
							+ " recomendamos que você altere sua senha no próximo acesso"
							+ "\n\nAtenciosamente, equipe TransCript.";
			return mensagem;
		}else{
			String mensagem = "Olá " + usuario.getNome()
							+ " ,\nInformamos que seu cadastro foi realizado com sucesso."
							+ "\n\nAtenciosamente, equipe TransCript.";
			return mensagem;
		}
	}
}