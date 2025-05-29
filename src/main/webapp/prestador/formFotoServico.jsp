<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    padding: 30px 20px;
    color: var(--texto);
  }

  .form-container {
    max-width: 600px;
    margin: auto;
    background: white;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 0 15px rgba(0,0,0,0.1);
  }

  h1 {
    text-align: center;
    color: var(--primaria);
    margin-bottom: 30px;
  }

  label {
    font-weight: bold;
    color: var(--primaria);
  }

  input[type="file"],
  input[type="text"] {
    width: 100%;
    padding: 10px;
    margin-top: 6px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
  }

  button, .btn-voltar {
    background-color: var(--primaria);
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 16px;
    transition: background 0.3s;
    text-decoration: none;
    display: inline-block;
    margin-top: 10px;
  }

  button:hover, .btn-voltar:hover {
    background-color: var(--primaria-clara);
  }

  .btn-container {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
  }
</style>
<body>
  <div class="form-container">
	<form action="prestadores?action=salvarFoto" method="post" enctype="multipart/form-data">
		<h1> Adicione uma foto do ambiente! </h1>
		<label for="fotoServico">Foto (JPEG OU PNG):</label>
		<input type="file" name="fotoServico" id="fotoServico" accept="image/png, image/jpeg" required>
		<br><br>
		<label for="descricao">Descricao da Foto:</label>
		<input type="text" name="descricao" id="descricao" maxlength="100" required>
		<br><br>
		
 		<div class="btn-container">
	        <a href="prestadores?action=home" class="btn-voltar">← Voltar à Home</a>
	        <button type="submit">Submeter</button>
     	</div>
	</form>
	</div>
</body>
</html>