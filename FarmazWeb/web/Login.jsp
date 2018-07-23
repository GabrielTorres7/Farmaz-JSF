<%-- 
    Document   : Login
    Created on : 17/07/2018, 23:06:42
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farmaz</title>
    </head>
    <body>        
        <form action="ServletWeb" method="post">
            
            <label for="email">Email</label>
            <input type="text" id="email" name="email" required>
            <br><br>
            
            <label for="senha">Senha</label>
            <input type="password" id="senha" name="senha" required>
            <br><br>
            
            <input type="hidden" id="acao" name="acao" value="Login">
            
            <input type="submit" id="botao" name="botao">
        </form>
        
        <p>Novo usuário?<a href="TelaCadastro.jsp"> Registre-se aqui</a></p>
    </body>
</html>
