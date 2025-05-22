<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includes/head.html"/>
<body>
	<form action="front?action=savePsicologo" method="post" enctype="multipart/form-data">
		<jsp:include page="/includes/formUser.html"/>
		
		<label for="nomeFantasia">Nome Fantasia:</label>
		<input type="text" name="nomeFantasia" id="nomeFantasia">
		<br><br>
	</form>
</body>
</html>