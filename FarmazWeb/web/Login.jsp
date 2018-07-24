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
        
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        <title>Farmaz</title>
    </head>
    <body>
        <div class="container">
            <h2>Login</h2>
            <form action="ServletWeb" method="post">
                
                <div class="form-group">
                    <label for="email">Email</label>
                    <input class="form-control" type="text" id="email" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="senha">Senha</label>
                    <input class="form-control" type="password" id="senha" name="senha" required>
                </div>

                <input type="hidden" id="acao" name="acao" value="Login">

                <input class='btn btn-primary' type="submit" id="botao" name="botao">
            </form>
            
            <br>
            <p>Novo usuário?<a href="CadastrarCliente.jsp"> Registre-se aqui</a></p>
            <p>Quer ser nosso parceiro?<a href="CadastrarFarmacia.jsp"> Cadastre sua farmácia aqui</a></p>
        </div>
    </body>
</html>
