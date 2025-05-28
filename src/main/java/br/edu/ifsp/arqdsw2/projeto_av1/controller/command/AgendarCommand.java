package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.AgendamentoDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Agendamento;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.DisponibilidadeDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Disponibilidade;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.LogAgendamento;
import br.edu.ifsp.arqdsw2.projeto_av1.model.enums.Status;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AgendarCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int disponibilidadeId = Integer.parseInt(request.getParameter("disponibilidadeId"));
            int prestadorId = Integer.parseInt(request.getParameter("prestadorId"));
            int clienteId = Integer.parseInt(request.getParameter("clienteId"));

            String diaMesStr = request.getParameter("data");
            String horaInicioStr = request.getParameter("horaInicio");
            String horaFimStr = request.getParameter("horaFim");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date diaMes = dateFormat.parse(diaMesStr);
            Time horaInicio = Time.valueOf(horaInicioStr + ":00");
            Time horaFim = Time.valueOf(horaFimStr + ":00");
            
            DisponibilidadeDao dispDao = new DisponibilidadeDao();
            Disponibilidade disp = dispDao.buscarPorId(disponibilidadeId);
            if (disp == null) {
                request.setAttribute("mensagem", "Disponibilidade não encontrada.");
                return "/cliente/agendamento.jsp";
            }

            AgendamentoDao agendamentoDao = new AgendamentoDao();
            boolean conflito = agendamentoDao.existeConflito(disponibilidadeId, diaMes, horaInicio, horaFim);
            if (conflito) {
                request.setAttribute("mensagem", "Já existe um agendamento nesse horário.");
                return "/cliente/agendamento.jsp";
            }

            Agendamento agendamento = new Agendamento();
            agendamento.setClienteId(clienteId);
            agendamento.setPrestadorId(prestadorId);
            agendamento.setDisponibilidadeId(disponibilidadeId);
            agendamento.setDiaMes(diaMes);
            agendamento.setHoraInicio(horaInicio);
            agendamento.setHoraFim(horaFim);
            agendamento.setCriacao(new Date());
            agendamento.setEstado(Status.SOLICITADO);

            LogAgendamento log = new LogAgendamento(Status.SOLICITADO, new Date());
            agendamento.addMudanca(log);

            agendamentoDao.createWithTransaction(agendamento);

            request.setAttribute("mensagem", "Agendamento realizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao realizar agendamento: " + e.getMessage());
        }

        return "/cliente/agendamento.jsp";
    }
}
