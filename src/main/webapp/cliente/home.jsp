<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Cliente</title>
    <style>
        .card {
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 15px;
            margin: 10px;
            width: 300px;
            display: inline-block;
            vertical-align: top;
            text-align: center;
            background-color: #f9f9f9;
        }
        .card img {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border-radius: 50%;
        }
    </style>
</head>
	<h2>Prestadores disponíveis</h2>
    <c:forEach var="prestador" items="${prestadores}">
        <div class="card">
            <img src="${prestador.fotoPerfil}" alt="Foto de ${prestador.nomeFantasia}" />
            <h3>${prestador.nomeFantasia}</h3>
            <p><strong>Especialidade:</strong> ${prestador.especialidade}</p>
            <p><strong>Descrição:</strong> ${prestador.descricao}</p>
            <p><strong>Email:</strong> ${prestador.email}</p>
            <form action="clientes" method="get">
                <input type="hidden" name="action" value="agendar" />
                <input type="hidden" name="prestadorId" value="${prestador.id}" />
                <button type="submit">Agendar</button>
            </form>
        </div>
    </c:forEach>
    
<a href="clientes?action=logout">DESLOGAR</a>
</body>
</html>