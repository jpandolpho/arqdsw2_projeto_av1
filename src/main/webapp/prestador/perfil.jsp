<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
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
    max-width: 700px;
    margin: auto;
    background: white;
    padding: 25px 30px;
    border-radius: 12px;
    box-shadow: 0 0 18px rgba(0,0,0,0.1);
    text-align: center;
  }

  h1 {
    color: var(--primaria);
    margin-bottom: 25px;
  }

  .perfil-img {
    width: 180px;
    height: 180px;
    object-fit: cover;
    border-radius: 50%;
    border: 3px solid var(--primaria-clara);
    margin-bottom: 20px;
  }

  .consultorio-fotos {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
  }

  .consultorio-fotos img {
    width: 200px;
    height: 140px;
    object-fit: cover;
    border-radius: 12px;
    border: 2px solid var(--primaria-clara);
    box-shadow: 0 0 6px rgba(156, 77, 204, 0.4);
  }
  
  .btn-voltar {
    display: inline-block;
    margin-top: 30px;
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
	<img alt="Foto de Perfil" class="perfil-img" src="image?name=${prestador.fotoPerfil}&type=profile">
	<h1>${prestador.nomeFantasia}</h1>
	<br>
	<h2>Fotos do Consultório</h2>
	    <div class="consultorio-fotos">
		<c:forEach var="foto" items="${prestador.imagens}">
			<img alt="${foto.descricao }" src="image?name=${foto.caminho}&type=office">
			<br>
	</c:forEach>
	</div>
	<a href="prestadores?action=home" class="btn-voltar">← Voltar à Home</a>
	</div>
</body>
</html>