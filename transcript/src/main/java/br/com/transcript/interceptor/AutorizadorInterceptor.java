package br.com.transcript.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.transcript.bean.UsuarioBean;

@Component
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {


	@Autowired
	public UsuarioBean user;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		String uri = request.getRequestURI();

		if (uri.endsWith("login/") || uri.endsWith("/autenticar") || uri.contains("resources/")
				|| uri.endsWith("/servico") || uri.endsWith("/registrar") || uri.endsWith("/recuperarSenha")) {
			return true;
		}

		if (request.getSession().getAttribute("usuarioLogado") != null) {
			request.setAttribute("usuarioLogado", request.getSession().getAttribute("usuarioLogado"));
			user = (UsuarioBean) request.getSession().getAttribute("usuarioLogado");

			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/login/");
			return false;
		}
	}
}