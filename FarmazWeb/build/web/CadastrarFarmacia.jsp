<%-- 
    Document   : TelaCadastroFarmacia
    Created on : 23/07/2018, 19:10:05
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
        <h2>Formulário de cadastro</h2>
        <form action="ServletWeb" method="post">
            <label for="nome">Nome da farmácia</label>
            <input type="text" name="nome" id="nome" required><br><br>
           
            <label for="cadastro_prefeitura">Cadastro da prefeitura</label>
            <input type="text" name="cadastro_prefeitura" id="cadastro_prefeitura" required><br><br>
           
            <label for="cnpj">CNPJ</label>
            <input type="text" name="cnpj" id="cnpj" required><br><br>
            
            <label for="email">Email</label>
            <input type="email" name="email" id="email" required><br><br>
            
            <label for="senha">Senha</label>
            <input type="password" name="senha" id="senha" required><br><br>
            
            <label for="rua">Rua</label>
            <input type="text" name="rua" id="rua" required>
            
            <label for="numero">Número</label>
            <input type="text" name="numero" id="numero" size="5" required><br><br>
            
            <label for="bairro">Bairro</label>
            <input type="text" name="bairro" id="bairro" required><br><br>
            
            <label for="bairro">Cidade</label>
            <input type="text" name="cidade" id="cidade" required><br><br>
            
            <label for="estado">Estado</label>
            <select name="estado" id="estado" required>
                <option value="AC">Acre</option>
                <option value="MG">Minas Gerais</option>
                <option value="SP">São Paulo</option>
                <option value="RJ">Rio de Janeiro</option>
                <option value="BA">Bahia</option>
                <option value="RS">Rio Grande do Sul</option>
            </select><br><br>
                
            <input type="hidden" name="acao" id="acao" value="CadastraFarmacia">
            
            <input type="submit" name="botao" id="botao" value="Finalizar Cadastro">
              
        </form>
    </body>
</html>
