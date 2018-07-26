<%-- 
    Document   : TelaCadastro
    Created on : 22/07/2018, 17:20:08
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/estilo.css" rel="stylesheet" type="text/css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Farmaz</title>
    </head>
    <body>
        <div class="container">
            <center>
                <h2>Formulário de cadastro</h2>
            </center>
         
            <form action="ServletWeb" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="nome">Nome completo</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" name="nome" id="nome" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label"  for="documento_identificacao">Documento de identificação</label>
                    <div class="col-sm-10">    
                        <input class="form-control" type="text" name="documento_identificacao" id="documento_identificacao" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="telefone">Telefone</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="tel" name="telefone" id="telefone" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="email">Email</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="email" name="email" id="email" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="senha">Senha</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="password" name="senha" id="senha" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="cep">CEP</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="number" name="cep" id="cep" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="rua">Rua</label>
                    
                    <div class="col-sm-6">
                        <input class="form-control" type="text" name="rua" id="rua" required>
                    </div>
                    
                    <label class="col-sm-2 control-label" for="numero">Número</label>
                    <div class="col-sm-2">
                        <input class="form-control" type="number" name="numero" id="numero" size="5" required>
                    </div>
                </div>
                
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="bairro">Bairro</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" name="bairro" id="bairro" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="bairro">Cidade</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" name="cidade" id="cidade" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="estado">Estado</label>
                    <div class="col-sm-10">
                        <select class="form-control" name="estado" id="estado" required>
                            <option value="AC">Acre</option>
                            <option value="MG">Minas Gerais</option>
                            <option value="SP">São Paulo</option>
                            <option value="RJ">Rio de Janeiro</option>
                            <option value="BA">Bahia</option>
                            <option value="RS">Rio Grande do Sul</option>
                        </select>
                    </div>
                </div>

                    <input type="hidden" name="acao" id="acao" value="CadastraCliente">
                    
                    <div class="form-group">
                        <div class="col-sm-10 col-sm-offset-2">
                            <input class='btn btn-primary btn-block' type="submit" name="botao" id="botao" value="Finalizar Cadastro">
                        </div>
                    </div>
            </form>
        </div>
    </body>
</html>
