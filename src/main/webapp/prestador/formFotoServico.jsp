<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="../includes/head.html"/>
<body>
	<form action="prestadores?action=salvarFoto" method="post" enctype="multipart/form-data">
		<h1>Adicione Fotos do seu Ambiente de Atendimento! Apenas uma foto por vez.</h1>
		<label for="fotoServico">Foto:</label>
		<input type="file" name="fotoServico" id="fotoServico" accept="image/png, image/jpeg">
		<br><br>
		<label for="descricao">Descricao da Foto:</label>
		<input type="text" name="descricao" id="descricao">
		<br><br>
		<button type="submit">Submeter</button>
	</form>
</body>
</html>