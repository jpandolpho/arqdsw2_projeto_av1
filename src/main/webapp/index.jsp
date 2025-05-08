<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<form action="front?action=login" method="post">
		<label for="email">Email:</label>
		<input type="text" name="email" id="email">
		<br><br>
		<label for="senha">Senha:</label>
		<input type="password" name="senha" id="senha">
		<br><br>
		<button type="submit">Entrar</button>
	</form>
	<a href="front?action=cadCliente">Cadastro Cliente</a>
	<br>
	<a href="front?action=cadPsicologo">Cadastro Psicologo</a>
</html>