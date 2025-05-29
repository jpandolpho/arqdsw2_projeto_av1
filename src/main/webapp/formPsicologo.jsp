<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/includes/head.html"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f2f8;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }

        .card {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 600px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #5e4b8b;
        }

        input, select, textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 14px;
        }

        textarea {
            resize: vertical;
            min-height: 80px;
        }

        button {
            background-color: #7b5eb1;
            color: white;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #6a4ca4;
        }
    </style>
</head>
<body>
    <div class="card">
        <form action="front?action=savePsicologo" method="post" enctype="multipart/form-data">
            <jsp:include page="/includes/formUser.jsp"/>

            <label for="nomeFantasia">Nome Fantasia:</label>
            <input type="text" name="nomeFantasia" id="nomeFantasia">

            <label for="descricao">Descrição:</label>
            <textarea name="descricao" id="descricao"></textarea>

            <label for="fotoPerfil">Foto de Perfil:</label>
            <input type="file" name="fotoPerfil" id="fotoPerfil" accept="image/png, image/jpeg">

            <label for="especialidade">Especialidade:</label>
            <input type="text" name="especialidade" id="especialidade">

            <button type="submit">Cadastrar-se</button><br><br>
            <button type="button" onclick="window.location.href='index.jsp'">Voltar para Início</button>
            
        </form>
    </div>
</body>
</html>