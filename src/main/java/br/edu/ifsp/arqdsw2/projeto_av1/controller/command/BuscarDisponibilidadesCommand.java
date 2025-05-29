package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.DisponibilidadeDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Disponibilidade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BuscarDisponibilidadesCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
    	try {
            int prestadorId = Integer.parseInt(req.getParameter("prestadorId"));
            DisponibilidadeDao dao = new DisponibilidadeDao();
            List<Disponibilidade> disponibilidades = dao.buscarPorPrestador(prestadorId);
            req.setAttribute("disponibilidades", disponibilidades);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao buscar disponibilidades.");
        } catch (NumberFormatException e) {
            req.setAttribute("erro", "ID do prestador inválido.");
        }

        return "/cliente/modal-agendamento.jsp";
    }
}


