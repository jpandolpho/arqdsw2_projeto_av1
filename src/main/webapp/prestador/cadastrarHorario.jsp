<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Cadastrar Horário de Disponibilidade</h2>

<% String msg = (String) request.getAttribute("msg"); %>
<% if (msg != null) { %>
    <p style="color: green;"><%= msg %></p>
<% } %>

<form action="controller" method="post">
    <input type="hidden" name="command" value="CadastrarHorario">

    <label for="diaSemana">Dia da Semana:</label>
    <select name="diaSemana" id="diaSemana" required>
        <option value="SEGUNDA">Segunda-feira</option>
        <option value="TERCA">Terça-feira</option>
        <option value="QUARTA">Quarta-feira</option>
        <option value="QUINTA">Quinta-feira</option>
        <option value="SEXTA">Sexta-feira</option>
        <option value="SABADO">Sábado</option>
        <option value="DOMINGO">Domingo</option>
    </select>
    <br><br>

    <label for="horaInicio">Hora de Início:</label>
    <input type="time" name="horaInicio" id="horaInicio" required>
    <br><br>

    <label for="horaFim">Hora de Fim:</label>
    <input type="time" name="horaFim" id="horaFim" required>
    <br><br>

    <input type="submit" value="Cadastrar Horário">
</form>

<a href="prestador/home.jsp">Voltar à Home</a>

</html>