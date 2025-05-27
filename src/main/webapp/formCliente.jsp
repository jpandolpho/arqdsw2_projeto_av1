<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includes/head.html"/>
<body>
	<form action="front?action=saveCliente" method="post" enctype="multipart/form-data">
		<jsp:include page="/includes/formUser.jsp"/>
		
		<label for="cpf">CPF(apenas números):</label>
		<input type="text" name="cpf" id="cpf">
		<br><br>
		
		<label for="contato">Contato:</label>
		<input type="text" name="contato" id="contato">
		<br><br>
		
		<button type="submit">Cadastrar-se</button>
	</form>
</body>
</html>