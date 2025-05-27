package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PerfilCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var session = request.getSession(false);
		Prestador p = (Prestador) session.getAttribute("user");
		request.setAttribute("prestador", p);
		return "/prestador/perfil.jsp";
	}

}
