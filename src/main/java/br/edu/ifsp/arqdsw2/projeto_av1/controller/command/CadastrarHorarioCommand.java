package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.DisponibilidadeDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.enums.DiaSemana;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Disponibilidade;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CadastrarHorarioCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
        	if (req.getParameter("dia_mes") == null) {
                //não veio formulário preenchido: mostra o form
                return "/prestador/cadastrarHorario.jsp";
            }
        	
            HttpSession session = req.getSession();
            Prestador prestador = (Prestador) session.getAttribute("user");

            System.out.println("Prestador: " + prestador.getNome() + ", ID: " + prestador.getId());
            
            String diaMesStr = req.getParameter("dia_mes");
            String diaSemanaStr = req.getParameter("dia_semana");
            String horaInicioStr = req.getParameter("hora_inicio");
            String horaFimStr = req.getParameter("hora_fim");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date diaMes = sdf.parse(diaMesStr);

            DiaSemana diaSemana = DiaSemana.valueOf(diaSemanaStr);
            LocalTime horaInicio = LocalTime.parse(horaInicioStr);
            LocalTime horaFim = LocalTime.parse(horaFimStr);

            Disponibilidade disponibilidade = new Disponibilidade();
            disponibilidade.setPrestadorId(prestador.getId());
            disponibilidade.setDiaMes(diaMes);
            disponibilidade.setDiaSemana(diaSemana);
            disponibilidade.setHoraInicio(horaInicio);
            disponibilidade.setHoraFim(horaFim);

            DisponibilidadeDao dao = new DisponibilidadeDao();
            dao.inserir(disponibilidade);

            req.setAttribute("mensagem", "Horário cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagem", "Erro ao cadastrar horário.");
        }

        return "/prestador/home.jsp";
    }
}