package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.AgendamentoDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.LogAgendamentoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RejeitarAgendamentoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		AgendamentoDao dao = new AgendamentoDao();
		boolean updated = dao.update("rejeitado", id);
		if(updated) {
			LogAgendamentoDao logDao = new LogAgendamentoDao();
			boolean saved = logDao.insert("rejeitado", id);
			if(saved) {
				System.out.println("tudo certo!");
			}else {
				System.out.println("zuou o log");
			}
		}else {
			System.out.println("zuou o update");
		}
		
		return "prestadores?action=listarAgendamentos";
	}

}
