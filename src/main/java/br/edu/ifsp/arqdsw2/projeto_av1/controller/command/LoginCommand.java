package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.ClienteDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PrestadorDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Usuario user;
		String view;
		if("cliente".equals(tipo)) {
			var dao = new ClienteDao();
			user = dao.findByEmail(email);
			view = "clientes?action=home";
		}else if("psicologo".equals(tipo)) {
			var dao = new PrestadorDao();
			user = dao.findByEmail(email);
			view = "prestadores?action=home";
		}else {
			return "front?action=home";
		}
		var authorized = Usuario.authenticate(user, email, senha);
		if(authorized) {
			var session = request.getSession(true);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(24*60*60);
		}else {
			view = "front?action=home";
		}
		return view;
	}

}
