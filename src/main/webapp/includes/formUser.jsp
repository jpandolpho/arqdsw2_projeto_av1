<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<label for="nome">Nome:</label>
<input type="text" name="nome" id="nome">
<br><br>
<label for="endereco">Endereco:</label>
<input type="text" name="endereco" id="endereco">
<br><br>
<label for="email">Email:</label>
<input type="text" name="email" id="email">
<br><br>
<label for="senha">Senha:</label>
<input type="password" name="senha" id="senha">
<br><br>
<label for="senhaConf">Confirme a Senha:</label>
<input type="password" name="senhaConf" id="senhaConf">
<br><br>
<label for="cidade">Cidade:</label>
<select name="cidade">
	<c:forEach var="cidade" items="${cidades}">
		<option value="${cidade.nome}">${cidade.nome}</option>
	</c:forEach>
</select>
<br><br>