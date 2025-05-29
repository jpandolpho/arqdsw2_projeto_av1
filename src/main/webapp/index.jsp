<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/includes/head.html"/>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f2f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .card {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 320px;
            text-align: center;
        }
        
        .logo {
            margin-bottom: 20px;
        }

        .logo img {
            max-width: 160px;
            height: auto;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #5e4b8b;
            text-align: left;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
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

        .links {
            margin-top: 15px;
            font-size: 14px;
        }

        .links a {
            display: inline-block;
            color: #7b5eb1;
            text-decoration: none;
            margin: 5px 0;
        }

        .links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="card">
    
    	<div class="logo">
            <img src="https://i.ibb.co/d8NGw4Z/calmind.png" alt="Logo Calmind">
        </div>
    
        <form action="front?action=login" method="post">
            <label for="email">Email:</label>
            <input type="text" name="email" id="email">

            <label for="senha">Senha:</label>
            <input type="password" name="senha" id="senha">

            <label for="tipo">Sou:</label>
            <select name="tipo" id="tipo">
                <option value="cliente">Cliente</option>
                <option value="psicologo">Psicólogo</option>
            </select>

            <button type="submit">Entrar</button>
        </form>

        <div class="links">
            <a href="front?action=cadCliente">Cadastro Cliente</a><br>
            <a href="front?action=cadPsicologo">Cadastro Psicólogo</a>
        </div>
    </div>
</body>
</html>
