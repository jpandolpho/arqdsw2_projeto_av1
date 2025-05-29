package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.AgendamentoDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Agendamento;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.LogAgendamento;
import br.edu.ifsp.arqdsw2.projeto_av1.model.enums.Status;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AgendarCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int dispId      = Integer.parseInt(request.getParameter("disponibilidadeId"));
            int prestadorId = Integer.parseInt(request.getParameter("prestadorId"));

            HttpSession session = request.getSession(false);
            var usuario = session.getAttribute("user"); 
            if (usuario == null) {
                request.setAttribute("mensagem", "Sessão expirada. Faça login novamente.");
                return "/front?action=home";
            }
            int clienteId = ((br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Cliente) usuario).getId();

            var disp = new br.edu.ifsp.arqdsw2.projeto_av1.model.dao.DisponibilidadeDao()
                            .buscarPorId(dispId);
            if (disp == null) {
                request.setAttribute("mensagem", "Disponibilidade não encontrada.");
                return voltarParaSaibaMais(request, prestadorId);
            }

            Agendamento ag = new Agendamento();
            ag.setClienteId(clienteId);
            ag.setPrestadorId(prestadorId);
            ag.setDisponibilidadeId(dispId);
            ag.setDiaMes(disp.getDiaMes());
            ag.setHoraInicio(Time.valueOf(disp.getHoraInicio()));
            ag.setHoraFim(Time.valueOf(disp.getHoraFim()));
            ag.setCriacao(new Date());
            ag.setEstado(Status.SOLICITADO);
            ag.addMudanca(new LogAgendamento(Status.SOLICITADO, new Date()));

            AgendamentoDao dao = new AgendamentoDao();
            boolean ok = dao.createWithTransaction(ag);
            if (!ok) {
                request.setAttribute("mensagem", "Este horário já está reservado.");
            } else {
                request.setAttribute("mensagem", "Agendamento realizado com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao agendar: " + e.getMessage());
        }


        int prestadorId = Integer.parseInt(request.getParameter("prestadorId"));
        return voltarParaSaibaMais(request, prestadorId);
    }

    private String voltarParaSaibaMais(HttpServletRequest request, int prestadorId) {
        try {
            var pDao = new br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PrestadorDao();
            var prest = pDao.findById(prestadorId);
            request.setAttribute("prestador", prest);

            var imgDao = new br.edu.ifsp.arqdsw2.projeto_av1.model.dao.ImagemServDao();
            request.setAttribute("imagens", imgDao.retriveImagens(prest.getEmail()));

            var dDao = new br.edu.ifsp.arqdsw2.projeto_av1.model.dao.DisponibilidadeDao();
            request.setAttribute("disponibilidades", dDao.buscarPorPrestador(prestadorId));
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensagem", "Erro ao recarregar a página.");
        }
        return "/cliente/saibaMais.jsp";
    }
}
