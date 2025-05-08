package br.edu.ifsp.arqdsw2.projeto_av1.controller;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/front")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		Command command = null;
		String action = request.getParameter("action");
		
		if("login".equals(action)) {
			command = new LoginCommand();
		}else if("cadCliente".equals(action)) {
			command = new ClienteCadastroCommand();
		}else if("cadPsicologo".equals(action)) {
			command = new PsicologoCadastroCommand();
		}
	}
}
