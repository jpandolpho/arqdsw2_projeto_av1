package br.edu.ifsp.arqdsw2.projeto_av1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.AgendarCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.BuscarPrestadoresCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.Command;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.HomeClienteCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.HomePsicologoCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.LogoutCommand;
import br.edu.ifsp.arqdsw2.projeto_av1.controller.command.SaibaMaisPrestadorCommand;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {
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
			command = new HomeClienteCommand();
		}else if("logout".equals(action)) {
			command = new LogoutCommand();
		} else if("buscarPrestadores".equals(action)) {
		    command = new BuscarPrestadoresCommand();
		} else if("agendar".equals(action)) {
		    command = new AgendarCommand();
		} else if("saibamais".equals(action)) {
			command = new SaibaMaisPrestadorCommand();
		}
		
		String view = command.execute(request, response);
		var dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
