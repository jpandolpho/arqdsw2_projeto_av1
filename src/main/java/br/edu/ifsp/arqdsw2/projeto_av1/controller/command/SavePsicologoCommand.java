package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PsicologoDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class SavePsicologoCommand implements Command {

	private List<String> allowedTypes = Arrays.asList("image/png", "image/jpeg");
	
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
		
		String nomeFantasia = request.getParameter("nomeFantasia");
		String especialidade = request.getParameter("especialidade");
		String descricao = request.getParameter("descricao");
		
		
		Part fotoPart = request.getPart("fotoPerfil");
		if(!allowedTypes.contains(fotoPart.getContentType())) {
			System.out.println("falha tipo");
			return "front?action=cadPsicologo";
		}
		String fileName = UUID.randomUUID().toString() + "_" + Paths.get(fotoPart.getSubmittedFileName()).getFileName().toString();
		String uploadPath = "C:\\calmind";
		Files.createDirectories(Paths.get(uploadPath));
		fotoPart.write(uploadPath + File.separator + fileName);

		Prestador p = new Prestador(nomeFantasia,fileName,especialidade,descricao,nome,endereco,email,senha);
		
		PsicologoDao dao = new PsicologoDao();
		var saved = dao.insert(p, cidade);
		if(saved) {
			System.out.println("Salvou");
		}else {
			System.out.println("Não salvou");
		}
		return "index.jsp";
	}

}
