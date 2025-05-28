<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../includes/head.html"/>
<body>
    <h2>Cadastrar Horário Disponível</h2>
    <form action="prestadores" method="post">
        <input type="hidden" name="action" value="cadastrarHorario" />
        
        <label>Data (aaaa-mm-dd):</label>
        <input type="date" name="dia_mes" required /><br>

        <label>Dia da Semana:</label>
        <select name="dia_semana" required>
            <option value="SEG">Segunda</option>
            <option value="TER">Terça</option>
            <option value="QUA">Quarta</option>
            <option value="QUI">Quinta</option>
            <option value="SEX">Sexta</option>
            <option value="SAB">Sábado</option>
            <option value="DOM">Domingo</option>
        </select><br>

        <label>Hora Início:</label>
        <input type="time" name="hora_inicio" required /><br>

        <label>Hora Fim:</label>
        <input type="time" name="hora_fim" required /><br><br>

        <input type="submit" value="Cadastrar" />
    </form>
    
    <a href="prestador/home.jsp">Voltar à Home</a>
    
</body>
</html>