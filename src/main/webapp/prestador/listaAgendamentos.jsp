<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<jsp:include page="../includes/head.html"/>
<style>
  :root {
    --primaria: #6a1b9a;
    --primaria-clara: #9c4dcc;
    --fundo: #f5f3f7;
    --texto: #333;
  }

  body {
    font-family: Arial, sans-serif;
    background-color: var(--fundo);
    margin: 0;
    padding: 30px 20px;
    color: var(--texto);
  }

  .container {
    max-width: 1100px;
    margin: auto;
    background: white;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 0 18px rgba(0, 0, 0, 0.1);
  }

  h1 {
    text-align: center;
    color: var(--primaria);
    margin-bottom: 25px;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
  }

  table, th, td {
    border: 1px solid #ccc;
  }

  th, td {
    padding: 10px;
    text-align: center;
  }

  th {
    background-color: var(--primaria-clara);
    color: white;
  }

  form {
    display: inline;
  }

  button {
    background-color: var(--primaria);
    color: white;
    border: none;
    padding: 6px 12px;
    border-radius: 6px;
    cursor: pointer;
    transition: background 0.3s;
  }

  button:disabled {
    background-color: #ccc;
    cursor: default;
  }

  button:hover:not(:disabled) {
    background-color: var(--primaria-clara);
  }

  select {
    padding: 6px;
    border-radius: 6px;
    border: 1px solid #ccc;
  }

  .pagination {
    margin-top: 25px;
    text-align: center;
  }

  .pagination a {
    display: inline-block;
    margin: 0 10px;
    padding: 10px 16px;
    background-color: var(--primaria);
    color: white;
    text-decoration: none;
    border-radius: 8px;
    transition: background 0.3s;
  }

  .pagination a:hover {
    background-color: var(--primaria-clara);
  }

  .btn-voltar {
    display: inline-block;
    margin-top: 30px;
    background-color: var(--primaria);
    color: white;
    padding: 10px 20px;
    border-radius: 8px;
    text-decoration: none;
    font-weight: bold;
    transition: background 0.3s;
  }

  .btn-voltar:hover {
    background-color: var(--primaria-clara);
  }
</style>
<body>
  <div class="container">
    <h1>Lista de Agendamentos</h1>

    <form action="prestadores?action=listarAgendamentos" method="post">
      <label for="filter"><strong>Filtro:</strong></label>
      <select name="filter">
      	<option value=""></option>
        <option value="solicitado">Solicitado</option>
        <option value="aceito">Aceito</option>
        <option value="concluido">Concluído</option>
        <option value="rejeitado">Rejeitado</option>
      </select>
      <button type="submit">Filtrar</button>
    </form>

    <table>
      <thead>
        <tr>
          <th>Dia</th>
          <th>Horário de Início</th>
          <th>Horário de Fim</th>
          <th>Data de Criação</th>
          <th>Estado</th>
          <th>Histórico de Mudanças</th>
          <th colspan="3">Ações</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="agendamento" items="${agendamentos}">
          <tr>
            <td>${agendamento.diaMes}</td>
            <td>${agendamento.horaInicio}</td>
            <td>${agendamento.horaFim}</td>
            <td>${agendamento.criacao}</td>
            <td>${agendamento.estado}</td>
            <td><a href="prestadores?action=visualizarLog&id=${agendamento.id}">Log</a></td>
            <td>
              <form action="prestadores?action=aceitarAgendamento&id=${agendamento.id}" method="post">
                <button type="submit" ${agendamento.estado != 'SOLICITADO' ? 'disabled' : '' }>Aceitar</button>
              </form>
            </td>
            <td>
              <form action="prestadores?action=rejeitarAgendamento&id=${agendamento.id}" method="post">
                <button type="submit" ${agendamento.estado != 'SOLICITADO' ? 'disabled' : '' }>Rejeitar</button>
              </form>
            </td>
            <td>
              <form action="prestadores?action=concluirAgendamento&id=${agendamento.id}" method="post">
                <button type="submit" ${agendamento.estado != 'ACEITO' ? 'disabled' : '' }>Concluir</button>
              </form>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <div class="pagination">
      <%
        int paginaAtual = 0;
        if (request.getAttribute("page") != null) {
          paginaAtual = (int) request.getAttribute("page");
        }
        if (paginaAtual > 0) {
      %>
        <a href="prestadores?action=listarAgendamentos&page=<%=paginaAtual - 1%>&filter=${param.filter}">← Página Anterior</a>
      <%
        }
        int totalPaginas = (int) request.getAttribute("totalPaginas");
        if (totalPaginas > paginaAtual) {
      %>
        <a href="prestadores?action=listarAgendamentos&page=<%=paginaAtual + 1%>&filter=${param.filter}">Próxima Página →</a>
      <%
        }
      %>
    </div>

    <a href="prestadores?action=home" class="btn-voltar">← Voltar à Home</a>
  </div>
</body>
</html>
