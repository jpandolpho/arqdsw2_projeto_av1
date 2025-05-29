<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../includes/head.html"/>
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
    padding: 30px 20px;
    color: var(--texto);
  }

  .container {
    max-width: 600px;
    margin: auto;
    background: white;
    padding: 25px 30px;
    border-radius: 12px;
    box-shadow: 0 0 18px rgba(0,0,0,0.1);
  }

  h2 {
    text-align: center;
    color: var(--primaria);
    margin-bottom: 25px;
  }

  form label {
    display: block;
    margin: 10px 0 5px;
    font-weight: bold;
  }

  form input,
  form select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 6px;
    margin-bottom: 15px;
  }

  form input[type="submit"] {
    background-color: var(--primaria);
    color: white;
    border: none;
    cursor: pointer;
    font-weight: bold;
    transition: background 0.3s;
  }

  form input[type="submit"]:hover {
    background-color: var(--primaria-clara);
  }

  .btn-voltar {
    display: inline-block;
    margin-top: 20px;
    background-color: var(--primaria);
    color: white;
    padding: 10px 20px;
    border-radius: 8px;
    text-decoration: none;
    font-weight: bold;
    transition: background 0.3s;
  }

  .btn-voltar:hover {
    background-color: var(--primaria-clara);
  }
</style>
<body>
	<div class="container">
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
    
     <a href="prestadores?action=home" class="btn-voltar">← Voltar à Home</a>
    </div>
</body>
</html>