<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<jsp:include page="../includes/head.html"/>
<body>
<h1>Log Agendamento</h1>
<table>
		<thead>
			<tr>
				<th>Data de Alteração</th>
				<th>Estado</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="log" items="${logs}">
				<tr>
					<td>${log.horaMudanca }</td>
					<td>${log.estado }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>