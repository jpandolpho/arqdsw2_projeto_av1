package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.LogAgendamentoDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.LogAgendamento;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VisualizarLogCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		LogAgendamentoDao dao = new LogAgendamentoDao();
		List<LogAgendamento> logs = dao.retrieve(id);
		request.setAttribute("logs", logs);
		
		return "/prestador/logAgendamento.jsp";
	}

}
