package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.ImagemServDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PrestadorDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SaibaMaisPrestadorCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("prestadorId");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);

            PrestadorDao prestadorDao = new PrestadorDao();
            Prestador prestador = prestadorDao.findById(id);
            
            System.out.println("Prestador ID recebido: " + idParam);
            System.out.println("Buscando imagens para: " + prestador.getEmail());
            
            ImagemServDao imagemDao = new ImagemServDao();
            var imagens = imagemDao.retriveImagens(prestador.getEmail());

            request.setAttribute("prestador", prestador);
            request.setAttribute("imagens", imagens);
        }

        return "cliente/saibaMais.jsp";
    }
}
