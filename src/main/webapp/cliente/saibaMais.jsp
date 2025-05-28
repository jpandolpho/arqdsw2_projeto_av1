<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalhes do Prestador</title>
</head>
<body>
    <h2>${prestador.nomeFantasia}</h2>
    <p><strong>Especialidade:</strong> ${prestador.especialidade}</p>
    <p><strong>Email:</strong> ${prestador.email}</p>
    <p><strong>Descrição:</strong> ${prestador.descricao}</p>
    
    <h3>Serviços do Prestador:</h3>
    <c:forEach var="img" items="${imagens}">
        <div>
            <img src="${pageContext.request.contextPath}/locations/${img.caminho}" width="200" />
            <p>${img.descricao}</p>
        </div>
    </c:forEach>

    <br><a href="clientes?action=home">Voltar</a>
</body>
</html>

