package br.edu.ifsp.arqdsw2.projeto_av1.controller;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.Command;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.HomeCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.LoginCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.SaveClienteCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.SavePsicologoCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.CadastroClienteCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.CadastroPsicologoCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/front")
@MultipartConfig(
		 fileSizeThreshold = 1024 * 1024 * 2,
		 maxFileSize = 1024 * 1024 * 2,
		 maxRequestSize = 1024 * 1024 * 2
		)
public class FrontServlet extends HttpServlet {
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
		
		if("login".equals(action)) {
			command = new LoginCommand();
		}else if("cadCliente".equals(action)) {
			command = new CadastroClienteCommand();
		}else if("cadPsicologo".equals(action)) {
			command = new CadastroPsicologoCommand();
		}else if("savePsicologo".equals(action)) {
			command = new SavePsicologoCommand();
		}else if("saveCliente".equals(action)) {
			command = new SaveClienteCommand();
		}else if("home".equals(action)) {
			command = new HomeCommand();
		}
		
		String view = command.execute(request, response);
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
