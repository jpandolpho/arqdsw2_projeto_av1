package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.DisponibilidadeDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.ImagemServDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PrestadorDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Disponibilidade;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaibaMaisPrestadorCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("prestadorId");
        if (idParam != null) {
        try {
            int id = Integer.parseInt(idParam);

            PrestadorDao prestadorDao = new PrestadorDao();
            Prestador prestador = prestadorDao.findById(id);
            
            System.out.println("Prestador ID recebido: " + idParam);
            System.out.println("Buscando imagens para: " + prestador.getEmail());
            
            ImagemServDao imagemDao = new ImagemServDao();
            var imagens = imagemDao.retriveImagens(prestador.getEmail());
            
            DisponibilidadeDao dispDao = new DisponibilidadeDao();
            List<Disponibilidade> dispon = dispDao.buscarPorPrestador(id);

            request.setAttribute("prestador", prestador);
            request.setAttribute("imagens", imagens);
            request.setAttribute("disponibilidades", dispon);
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao buscar dados do prestador.");
            return "/cliente/erro.jsp"; 
        }
    }
        return "cliente/saibaMais.jsp";
}}
