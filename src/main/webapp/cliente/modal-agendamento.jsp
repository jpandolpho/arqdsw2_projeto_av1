<%@ page import="br.edu.ifsp.arqdsw2.projeto_av1.model.entity.Disponibilidade" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="clientes" method="post">
    <input type="hidden" name="action" value="agendar" />
    <input type="hidden" name="clienteId" value="${sessionScope.cliente.id}" />
    <input type="hidden" name="prestadorId" value="${param.prestadorId}" />

    <label for="disponibilidadeId">Escolha um horário disponível:</label>
    <select name="disponibilidadeId" required>
        <c:forEach var="d" items="${disponibilidades}">
            <option value="${d.id}">
                ${d.diaMes} - ${d.horaInicio} às ${d.horaFim} (${d.diaSemana})
            </option>
        </c:forEach>
    </select>
    <br><br>

    <button type="submit">Confirmar Agendamento</button>
    <button type="button" onclick="document.getElementById('modal-agendamento').style.display='none'">Cancelar</button>
</form>
