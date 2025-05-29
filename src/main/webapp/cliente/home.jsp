<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Cliente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f0fa;
            color: #333;
            padding: 20px;
            text-align: center;
        }

        h2 {
            color: #5e4b8b;
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 30px;
            background-color: #fff;
            padding: 15px;
            border-radius: 12px;
            display: inline-block;
            box-shadow: 0 0 8px rgba(0,0,0,0.1);
        }

        label {
            margin-right: 10px;
            font-weight: bold;
            color: #5e4b8b;
        }

        select, input[type="text"] {
            padding: 8px;
            border-radius: 8px;
            border: 1px solid #ccc;
            margin-right: 10px;
        }

        button[type="submit"] {
            background-color: #5e4b8b;
            color: #fff;
            padding: 10px 16px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #452f6b;
        }

        .card {
            border: 1px solid #ccc;
            border-radius: 15px;
            padding: 20px;
            margin: 15px;
            width: 280px;
            display: inline-block;
            background-color: #fff;
            box-shadow: 0 4px 10px rgba(0,0,0,0.05);
        }

        .card img {
            width: 120px;
            height: 120px;
            object-fit: cover;
            border-radius: 50%;
            margin-bottom: 10px;
        }

        .card h3 {
            color: #5e4b8b;
            margin: 10px 0 5px;
        }

        .card p {
            margin: 5px 0;
        }

        .card a {
            display: inline-block;
            margin-top: 10px;
            background-color: #e0d8f0;
            color: #5e4b8b;
            padding: 8px 12px;
            border-radius: 8px;
            text-decoration: none;
        }

        .card a:hover {
            background-color: #d0c0eb;
        }

        .pagination {
            margin: 30px 0 10px;
            text-align: center;
        }

        .pagination a {
            margin: 0 5px;
            padding: 6px 12px;
            border-radius: 6px;
            background-color: #e0d8f0;
            color: #5e4b8b;
            text-decoration: none;
        }

        .pagination a.ativo {
            background-color: #5e4b8b;
            color: #fff;
            pointer-events: none;
        }

        a.logout-link {
            display: inline-block;
            margin-top: 30px;
            background-color: #f8e3f8;
            color: #5e4b8b;
            padding: 10px 16px;
            text-decoration: none;
            border-radius: 10px;
        }

        a.logout-link:hover {
            background-color: #e0d8f0;
        }
    </style>
</head>

	<h2>Profissionais/clínicas disponíveis</h2>
	
    <!-- filtro -->
    <form action="clientes" method="get">
        <input type="hidden" name="action" value="home"/>
        <label for="cidade">Cidade:</label>
        <select name="cidade" id="cidade">
            <option value="">-- Todas --</option>
            <c:forEach var="cid" items="${cidades}">
                <option value="${cid.nome}"
                    ${cid.nome == cidadeSelecionada ? 'selected' : ''}>
                    ${cid.nome}
                </option>
            </c:forEach>
        </select>
        <label for="especialidade">Especialidade:</label>
        <input type="text"
               name="especialidade"
               id="especialidade"
               value="${especialidadeSelecionada}"/>
        <button type="submit">Filtrar</button>
    </form>
	<br><br>
	<!-- cards -->
	<c:if test="${empty prestadores}">
    <p>Nenhum prestador disponível no momento.</p>
	</c:if>
	
    <c:forEach var="prestador" items="${prestadores}">    
        <div class="card">
            <img src="${prestador.fotoPerfil}" alt="Foto de ${prestador.nomeFantasia}" />
            <h3>${prestador.nomeFantasia}</h3>
            <p><strong>Especialidade:</strong> ${prestador.especialidade}</p>
            <p><strong>Email:</strong> ${prestador.email}</p>
            <a href="clientes?action=saibamais&prestadorId=${prestador.id}" >
			    Saiba Mais
			</a>
        </div>
    </c:forEach>
    
    <!-- paginação -->
    <div class="pagination">
  	<c:if test="${currentPage > 0}">
    <a href="clientes?action=home&page=${currentPage-1}">« Anterior</a>
  	</c:if>

	  <c:forEach begin="0" end="${totalPages-1}" var="i">
	    <a href="clientes?action=home&page=${i}"
	       class="${i == currentPage ? 'ativo' : ''}">
	      ${i + 1}
	    </a>
	  </c:forEach>
	
	  <c:if test="${currentPage < totalPages-1}">
	    <a href="clientes?action=home&page=${currentPage+1}">Próxima »</a>
	  </c:if>
</div>
    
<a href="clientes?action=logout" class="logout-link"> Deslogar</a>
</body>
</html>