<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<jsp:include page="../includes/head.html"/>
<body>
	<h1>Lista de Agendamentos</h1>
	<form action="prestadores?action=listarAgendamentos" method="post">
		<label for="filter">Filtro:</label>
			<select name=filter>
				<option value="solicitado">Solicitado</option>
				<option value="aceito">Aceito</option>
				<option value="concluido">Concluido</option>
				<option value="rejeitado">Rejeitado</option>
			</select>
			<button type="submit">Filtrar</button>
	</form>
	<br><br>
	<table>
		<thead>
			<tr>
				<th>Dia</th>
				<th>Horário de Início</th>
				<th>Horário de Fim</th>
				<th>Data de Criação</th>
				<th>Estado</th>
				<th>Histórico de Mudanças</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="agendamento" items="${agendamentos}">
				<tr>
					<td>${agendamento.diaMes }</td>
					<td>${agendamento.horaInicio }</td>
					<td>${agendamento.horaFim }</td>
					<td>${agendamento.criacao }</td>
					<td>${agendamento.estado }</td>
					<td>log</td>
					<td>ações</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%
	int paginaAtual = 0;
	if(request.getAttribute("page")!=null){
		paginaAtual = (int)request.getAttribute("page");
	}
	if(paginaAtual>0){
		%><a href="prestadores?action=listarAgendamentos&page=<%=paginaAtual-1%>&filter=${filter}">Voltar Página</a><%
	}
	int totalPaginas = (int)request.getAttribute("totalPaginas");
	if(totalPaginas>paginaAtual){
		%><a href="prestadores?action=listarAgendamentos&page=<%=paginaAtual+1%>&filter=${filter}">Próxima Página</a><%
	}
	%>
</body>
</html>