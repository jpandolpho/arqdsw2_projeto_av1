<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>Detalhes do Prestador</title>
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
      padding: 20px;
    }

    .container {
      max-width: 900px;
      margin: auto;
      background: white;
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 0 15px rgba(0,0,0,0.08);
    }

    h2, h3 {
      color: var(--primaria);
      margin-bottom: 10px;
    }

    p {
      font-size: 16px;
      margin: 8px 0;
      color: var(--texto);
    }

    .galeria {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
      margin-top: 15px;
    }

    .galeria-item {
      background: #f0e6f7;
      padding: 10px;
      border-radius: 10px;
      text-align: center;
      width: 160px;
    }

    .galeria-item img {
      width: 150px;
      height: 150px;
      object-fit: cover;
      border-radius: 8px;
      border: 2px solid var(--primaria-clara);
    }

    label {
      font-weight: bold;
      color: var(--texto);
    }

    select, button {
      padding: 10px;
      font-size: 16px;
      border-radius: 6px;
      border: 1px solid var(--primaria-clara);
      width: 100%;
      margin-top: 10px;
      box-sizing: border-box;
    }

    select {
      background-color: #fafafa;
    }

    button {
      background-color: var(--primaria);
      color: white;
      border: none;
      cursor: pointer;
      transition: background 0.3s ease;
    }

    button:hover {
      background-color: #4a0072;
    }

    .mensagem {
      color: red;
      margin-top: 15px;
    }

    .voltar {
      display: inline-block;
      margin-top: 20px;
      text-decoration: none;
      color: white;
      background: var(--primaria-clara);
      padding: 8px 12px;
      border-radius: 6px;
      transition: background 0.3s ease;
    }

    .voltar:hover {
      background: var(--primaria);
    }
  </style>
</head>
<body>
<div class="container">
  <h2>${prestador.nomeFantasia}</h2>
  <p><strong>Especialidade:</strong> ${prestador.especialidade}</p>
  <p><strong>Email:</strong> ${prestador.email}</p>
  <p><strong>Descrição:</strong> ${prestador.descricao}</p>

  <h3>Galeria de Serviços</h3>
  <div class="galeria">
    <c:forEach var="img" items="${imagens}">
      <div class="galeria-item">
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

  <p class="mensagem">${mensagem}</p>
	<a class="voltar" href="${pageContext.request.contextPath}/clientes?action=home">Voltar</a>
  </div>
</body>
</html>