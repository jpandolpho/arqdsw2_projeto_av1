package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.ImagemServDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.ImagemServ;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class SalvarFotoCommand implements Command {
	private List<String> allowedTypes = Arrays.asList("image/png", "image/jpeg");
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part fotoPart = request.getPart("fotoServico");
		if(!allowedTypes.contains(fotoPart.getContentType())) {
			System.out.println("falha tipo");
			return "prestadores?action=addFoto";
		}
		String fileName = UUID.randomUUID().toString() + "_" + Paths.get(fotoPart.getSubmittedFileName()).getFileName().toString();
		String uploadPath = "C:\\calmind\\locations";
		Files.createDirectories(Paths.get(uploadPath));
		fotoPart.write(uploadPath + File.separator + fileName);
		
		String descricao = request.getParameter("descricao");
		
		ImagemServ img = new ImagemServ(fileName,descricao);
		
		ImagemServDao dao = new ImagemServDao();
		var session = request.getSession(false);
		Prestador p = (Prestador)session.getAttribute("user");
		var saved = dao.insert(img,p.getEmail());
		if(saved) {
			System.out.println("Salvou");
		}else {
			System.out.println("Não salvou");
		}
		return "prestadores?action=home";
	}

}
