<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<jsp:include page="../includes/head.html"/>
<body>
	<img alt="Foto de Perfil" src="image?name=${prestador.fotoPerfil}&type=profile">
	<h1>${prestador.nomeFantasia}</h1>
	<br>
	<h1>Fotos do Consultório</h1>
	<c:forEach var="foto" items="${prestador.imagens}">
		<img alt="${foto.descricao }" src="image?name=${foto.caminho}&type=office">
		<br>
	</c:forEach>
</body>
</html>