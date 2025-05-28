<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head><title>Detalhes do Prestador</title></head>
<body>
  <h2>${prestador.nomeFantasia}</h2>
  <p><strong>Especialidade:</strong> ${prestador.especialidade}</p>
  <p><strong>Email:</strong> ${prestador.email}</p>
  <p><strong>Descrição:</strong> ${prestador.descricao}</p>

  <h3>Galeria de Serviços</h3>
  <div style="display:flex; flex-wrap:wrap;">
    <c:forEach var="img" items="${imagens}">
      <div style="margin:10px;text-align:center;">
        <img src="${pageContext.request.contextPath}/locations/${img.caminho}" 
             style="width:150px;height:150px;object-fit:cover;" />
        <p>${img.descricao}</p>
      </div>
    </c:forEach>
  </div>

  <h3>Agendar Horário</h3>
  <c:if test="${empty disponibilidades}">
    <p>Sem horários disponíveis.</p>
  </c:if>
  <c:if test="${not empty disponibilidades}">
    <form action="${pageContext.request.contextPath}/clientes" method="post">
      <input type="hidden" name="action" value="agendar"/>
      <input type="hidden" name="prestadorId" value="${prestador.id}"/>
      <%-- clienteId vindo da sessão: --%>

       <label for="disp">Escolha um horário:</label><br/>
      <select name="disponibilidadeId" id="disp" required>
        <c:forEach var="d" items="${disponibilidades}">
          <option value="${d.id}">
            <fmt:formatDate value="${d.diaMes}" pattern="dd/MM/yyyy"/> — 
            ${d.horaInicio} às ${d.horaFim}
          </option>
        </c:forEach>
      </select>
      <br/><br/>
      <button type="submit">Confirmar Agendamento</button>
    </form>
  </c:if>

  <p style="color: red;">${mensagem}</p>

  <br/><a href="${pageContext.request.contextPath}/clientes?action=home">Voltar</a>
</body>
</html>