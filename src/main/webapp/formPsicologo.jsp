<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includes/head.html"/>
<body>
	<form action="front?action=savePsicologo" method="post" enctype="multipart/form-data">
		<jsp:include page="/includes/formUser.jsp"/>
		
		<label for="nomeFantasia">Nome Fantasia:</label>
		<input type="text" name="nomeFantasia" id="nomeFantasia">
		<br><br>
		
		<label for="descricao">Descrição:</label>
		<input type="text" name="descricao" id="descricao">
		<br><br>
		
		<label for="fotoPerfil">Foto de Perfil:</label>
		<input type="file" name="fotoPerfil" id="fotoPerfil" accept="image/png, image/jpeg">
		<br><br>
		
		<label for="especialidade">Especialidade:</label>
		<input type="text" name="especialidade" id="especialidade">
		<br><br>
		
		<button type="submit">Cadastrar-se</button>
	</form>
</body>
</html>