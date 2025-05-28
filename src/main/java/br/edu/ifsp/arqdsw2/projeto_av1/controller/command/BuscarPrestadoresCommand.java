package br.edu.ifsp.arqdsw2.projeto_av1.controller.command;

import java.util.List;

import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.CidadeDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.dao.PrestadorDao;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Cidade;
import br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Prestador;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BuscarPrestadoresCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cidade = request.getParameter("cidade");
        String especialidade = request.getParameter("especialidade");

        PrestadorDao prestadorDao = new PrestadorDao();
        List<Prestador> prestadores = prestadorDao.buscarPorCidadeEspecialidade(cidade, especialidade);

        CidadeDao cidadeDao = new CidadeDao();
        List<Cidade> cidades = cidadeDao.fetchAll();

        request.setAttribute("prestadores", prestadores);
        request.setAttribute("cidades", cidades);
        request.setAttribute("cidadeSelecionada", cidade);
        request.setAttribute("especialidadeSelecionada", especialidade);

        return "/cliente/busca-prestadores.jsp";
    }
}
