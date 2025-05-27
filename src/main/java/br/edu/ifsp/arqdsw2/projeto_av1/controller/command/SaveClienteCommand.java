package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.ClienteDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveClienteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String senhaConf = request.getParameter("senhaConf");
		if(!senha.equals(senhaConf)) {
			System.out.println("falha senha");
			return "front?action=cadPsicologo";
		}
		String cidade = request.getParameter("cidade");
		
		String cpf = request.getParameter("cpf");
		String contato = request.getParameter("contato");
		
		Cliente c = new Cliente(cpf,contato,nome,endereco,email,senha);
		
		ClienteDao dao = new ClienteDao();
		var saved = dao.insert(c,cidade);
		if(saved) {
			System.out.println("Salvou");
		}else {
			System.out.println("Não salvou");
		}
		return "index.jsp";
	}

}
