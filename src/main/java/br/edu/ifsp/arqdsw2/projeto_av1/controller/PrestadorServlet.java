package br.edu.ifsp.arqdsw2.projeto_av1.controller;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.AdicionarFotoCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.Command;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.HomePsicologoCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.LogoutCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.PerfilCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.SalvarFotoCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/prestadores")
@MultipartConfig(
		 fileSizeThreshold = 1024 * 1024 * 2,
		 maxFileSize = 1024 * 1024 * 2,
		 maxRequestSize = 1024 * 1024 * 2
		)
public class PrestadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command command = null;
		String action = request.getParameter("action");
		
		if("home".equals(action)) {
			command = new HomePsicologoCommand();
		}else if("logout".equals(action)) {
			command = new LogoutCommand();
		}else if("addFoto".equals(action)) {
			command = new AdicionarFotoCommand();
		}else if("salvarFoto".equals(action)) {
			command = new SalvarFotoCommand();
		}else if("perfil".equals(action)) {
			command = new PerfilCommand();
		}else {
			command = new HomePsicologoCommand();
		}
		
		String view = command.execute(request, response);
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
