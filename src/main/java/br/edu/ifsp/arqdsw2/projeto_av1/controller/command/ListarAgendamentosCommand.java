package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.AgendamentoDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PrestadorDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Agendamento;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListarAgendamentosCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = request.getParameter("page");
		int page = 0;
		if(pagina!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String filtro = request.getParameter("filter");
		if(filtro==null) {
			filtro="";
		}
		
		var session = request.getSession(false);
		Prestador p = (Prestador)session.getAttribute("user");
		PrestadorDao pDao = new PrestadorDao();
		int id = pDao.findIdByEmail(p.getEmail());
		
		AgendamentoDao dao = new AgendamentoDao();
		List<Agendamento> agendamentos = dao.retrieve(id,page,filtro);
		int totalPaginas = dao.retrieve(id,filtro);
		
		request.setAttribute("agendamentos", agendamentos);
		request.setAttribute("page", page);
		request.setAttribute("filter", filtro);
		request.setAttribute("totalPaginas", totalPaginas);
		return "/prestador/listaAgendamentos.jsp";
	}

}
