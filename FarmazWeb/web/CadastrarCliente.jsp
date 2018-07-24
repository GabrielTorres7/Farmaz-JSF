<%-- 
    Document   : TelaCadastro
    Created on : 22/07/2018, 17:20:08
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
            <label for="nome">Nome completo</label>
            <input type="text" name="nome" id="nome" required><br><br>
           
            <label for="documento_identificacao">Documento de identificação</label>
            <input type="text" name="documento_identificacao" id="documento_identificacao" required><br><br>
           
            <label for="telefone">Telefone</label>
            <input type="tel" name="telefone" id="telefone" required><br><br>
            
            <label for="email">Email</label>
            <input type="email" name="email" id="email" required><br><br>
            
            <label for="senha">Senha</label>
            <input type="password" name="senha" id="senha" required><br><br>
            
            <label for="cep">CEP</label>
            <input type="number" name="cep" id="cep" required><br><br>
            
            <label for="rua">Rua</label>
            <input type="text" name="rua" id="rua" required>
            
            <label for="numero">Número</label>
            <input type="number" name="numero" id="numero" size="5" required><br><br>
            
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
                
            <input type="hidden" name="acao" id="acao" value="CadastraCliente">
            
            <input type="submit" name="botao" id="botao" value="Finalizar Cadastro">
              
        </form>
    </body>
</html>
