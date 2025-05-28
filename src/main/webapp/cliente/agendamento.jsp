<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Agendamento</title>
</head>
<body>
    <h1>Agendamento</h1>

    <form action="clientes" method="post">
        <input type="hidden" name="action" value="agendar"/>
        <label>Data: <input type="date" name="data" required/></label><br/>
        <label>Hora início: <input type="time" name="horaInicio" required/></label><br/>
        <label>Hora fim: <input type="time" name="horaFim" required/></label><br/>
        <label>Disponibilidade ID: <input type="number" name="disponibilidadeId" required/></label><br/>
        <label>Prestador ID: <input type="number" name="prestadorId" required/></label><br/>
        <label>Cliente ID: <input type="number" name="clienteId" required/></label><br/>
        <button type="submit">Agendar</button>
    </form>

    <p>${mensagem}</p>
</body>
</html>
