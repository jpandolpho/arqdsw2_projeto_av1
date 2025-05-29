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
    margin: 0;
    padding: 40px 20px;
    color: var(--texto);
  }

  .container {
    max-width: 600px;
    margin: auto;
    background: white;
    padding: 30px 40px;
    border-radius: 12px;
    box-shadow: 0 0 18px rgba(0,0,0,0.08);
    text-align: center;
  }

  h1 {
    color: var(--primaria);
    margin-bottom: 30px;
  }

  nav {
    display: flex;
    flex-direction: column;
    gap: 18px;
  }

  nav a {
    text-decoration: none;
    background-color: var(--primaria-clara);
    color: white;
    padding: 14px 22px;
    border-radius: 8px;
    font-weight: bold;
    font-size: 16px;
    transition: background-color 0.3s ease;
  }

  nav a:hover {
    background-color: var(--primaria);
  }
</style>
<body>
 <div class="container">
	<h1> Área do Prestador </h1>
	<nav>
		<a href="prestadores?action=perfil">Perfil</a>
		<a href="prestadores?action=addFoto">Adicionar Fotos</a>
		<a href="prestadores?action=cadastrarHorario">Cadastrar horarios</a>
		<a href="prestadores?action=listarAgendamentos">Listar Agendamentos</a>
		<a href="prestadores?action=logout">Deslogar</a>
	</nav>
</div>
</body>
</html>