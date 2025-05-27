package br.edu.ifsp.arqdsw2.projeto_av1.controller;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Cliente;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/prestadores", "/prestador/*"})
public class PrestadorFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		if (session != null && session.getAttribute("user") != null && session.getAttribute("user") instanceof Prestador) {
			chain.doFilter(request, response);
		} else {
			if(session.getAttribute("user") != null && session.getAttribute("user") instanceof Cliente) {
				var dispatcher = request.getRequestDispatcher("clientes?action=home");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("msg", "Acesso permitido apenas para usuário logado.");
				
				var dispatcher = request.getRequestDispatcher("front?action=home");
				dispatcher.forward(request, response);
			}
		}
	}
}