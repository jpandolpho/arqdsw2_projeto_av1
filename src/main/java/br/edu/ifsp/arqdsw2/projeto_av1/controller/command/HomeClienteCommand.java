package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PrestadorDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeClienteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			PrestadorDao prestadorDao = new PrestadorDao();
	        List<Prestador> prestadores = prestadorDao.buscarPorCidadeEspecialidade(null, null); // retorna todos
	        request.setAttribute("prestadores", prestadores);
	        return "/cliente/home.jsp";
	}

}
