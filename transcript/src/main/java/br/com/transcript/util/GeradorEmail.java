package br.com.transcript.util;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.transcript.bean.UsuarioBean;
import br.com.transcript.exception.BusinessException;

//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
public class GeradorEmail {

	private final String de = "equipetranscript@gmail.com";
	private final String senha = "TransCript@123";
	private String mailSMTPServer;
	private String mailSMTPServerPort;

	/*
	 * quando instanciar um Objeto ja sera atribuido o servidor SMTP do GMAIL e
	 * a porta usada por ele
	 */
	public GeradorEmail() { // smtp do domínio de emails da verity:
		mailSMTPServer = "smtp.gmail.com";
		mailSMTPServerPort = "587";
	}

	/*
	 * caso queira mudar o servidor e a porta, so enviar para o contrutor os
	 * valor como string
	 */

	public void sendMail(String from, String to, String subject, String message) {

		Properties properties = setProperties(from);

		// Cria um autenticador que sera usado a seguir
		SimpleAuth authenticator = null;
		authenticator = new SimpleAuth("seu_email@provedor.com", "sua_senha");

		// Session - objeto que ira realizar a conexão com o servidor
		/*
		 * Como há necessidade de autenticação é criada uma autenticacao que é
		 * responsavel por solicitar e retornar o usuário e senha para
		 * autenticação
		 */
		Session session = Session.getDefaultInstance(properties, authenticator);
		session.setDebug(true);
		// durante o
		// envio do email

		// Objeto que contém a mensagem
		MimeMessage msg = new MimeMessage(session);

		try {

			List<String> listaEmails = Arrays.asList(to.split("\\s*,\\s*"));

			// Setando os destinatários:
			for (String email : listaEmails) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(email));
			}

			// Setando a origem do email
			msg.setFrom(new InternetAddress(from));
			// Setando o assuntos
			msg.setSubject(subject, "utf-8");
			// Setando o conteúdo/corpo do email
			msg.setContent(message, "text/plain");

		} catch (Exception e) {
			System.out.println(">> Erro: Completar Mensagem");
			e.printStackTrace();
		}

		// Objeto encarregado de enviar os dados para o email
		Transport transport;
		try {
			transport = session.getTransport("smtp"); // define smtp para
														// transporte
			/*
			 * 1 - define o servidor smtp 2 - seu nome de usuario 3 - sua senha
			 */
			transport.connect(mailSMTPServer, from, senha);
			msg.saveChanges(); // don't forget this
			// envio da mensagem
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println(">> Erro ao enviar mensagem");
			e.printStackTrace();
		}
	}

	private Properties setProperties(String from) {
		Properties properties = new Properties();

		properties.put("mail.transport.protocol", "smtp"); // define protocolo
															// de
															// envio como SMTP
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", mailSMTPServer); // server SMTP do
															// email
		properties.put("mail.smtp.auth", "true"); // ativa autenticacao
		properties.put("mail.smtp.user", from); // usuario ou seja, a conta que
												// esta
		// enviando o email
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.port", mailSMTPServerPort); // porta
		properties.put("mail.smtp.socketFactory.port", mailSMTPServerPort); // mesma
		// porta
		// para
		// o
		// socket
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "true");

		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		return properties;
	}

	// Para alterações em status de candidatos:
	public void enviarEmail(UsuarioBean usuario, String acao) throws BusinessException {

		Mensagem mensagem = new Mensagem();

		String mensagemtexto = mensagem.gerador(usuario, acao);

		new Thread() {
			public void run() {
				sendMail(de, usuario.getEmail(), "TransCript (Não responda)", mensagemtexto);
			}
		}.start();
	}
}