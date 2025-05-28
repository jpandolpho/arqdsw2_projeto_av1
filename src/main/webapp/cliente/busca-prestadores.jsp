<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Buscar Prestadores</title>
</head>
<body>
    <h2>Buscar Prestadores</h2>

    <form action="clientes" method="get">
        <input type="hidden" name="action" value="buscarPrestadores"/>
        
        <label for="cidade">Cidade:</label>
        <select name="cidade" id="cidade">
            <option value="">-- Todas --</option>
            <c:forEach var="cidade" items="${cidades}">
                <option value="${cidade.nome}" ${cidade.nome == cidadeSelecionada ? 'selected' : ''}>${cidade.nome}</option>
            </c:forEach>
        </select>

        <label for="especialidade">Especialidade:</label>
        <input type="text" name="especialidade" id="especialidade" value="${especialidadeSelecionada}"/>

        <button type="submit">Buscar</button>
    </form>

    <h3>Resultados:</h3>
    <c:choose>
        <c:when test="${not empty prestadores}">
            <ul>
                <c:forEach var="p" items="${prestadores}">
                    <li>
                        <strong>${p.nomeFantasia}</strong> - ${p.especialidade} <br/>
                        ${p.descricao} <br/>
                        ${p.endereco}
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>Nenhum prestador encontrado.</p>
        </c:otherwise>
    </c:choose>

</body>
</html>
