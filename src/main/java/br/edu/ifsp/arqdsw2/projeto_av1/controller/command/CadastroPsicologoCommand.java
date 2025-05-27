package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.CidadeDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Cidade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastroPsicologoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CidadeDao dao = new CidadeDao();
		List<Cidade> cidades = dao.fetchAll();
		request.setAttribute("cidades", cidades);
		return "formPsicologo.jsp";
	}

}
