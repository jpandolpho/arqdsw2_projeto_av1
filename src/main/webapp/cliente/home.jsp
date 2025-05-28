<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
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
        pagination {
	    margin: 20px 0;
	    text-align: center;
	  }
	  	.pagination a {
	    margin: 0 5px;
	    padding: 5px 10px;
	    border: 1px solid #ccc;
	    text-decoration: none;
	    color: #333;
	  }
	  	.pagination a.ativo {
	    background-color: #333;
	    color: #fff;
	    pointer-events: none;
	  }
    </style>
</head>
	<h2>Prestadores disponíveis</h2>
	
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
    
<a href="clientes?action=logout">DESLOGAR</a>
</body>
</html>