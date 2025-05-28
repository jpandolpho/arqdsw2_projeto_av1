package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PrestadorDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class HomeClienteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrestadorDao prestadorDao = new PrestadorDao();

        int page = 0;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try { page = Integer.parseInt(pageParam); }
            catch (NumberFormatException ignored) {}
        }

        List<Prestador> prestadores = prestadorDao.buscarPaginado(null, null, page);
        int total = prestadorDao.countAll(null, null);

        int pageSize = PrestadorDao.PAGE_SIZE;
        int totalPages = (total + pageSize - 1) / pageSize;

        request.setAttribute("prestadores", prestadores);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        return "/cliente/home.jsp";
    }
}
