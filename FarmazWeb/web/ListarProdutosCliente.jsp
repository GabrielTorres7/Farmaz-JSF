<%-- 
    Document   : listaProdutos
    Created on : 21/07/2018, 22:22:46
    Author     : Gabriel
--%>

<%@page import="java.util.List"%>
<%@page import="br.cefetmg.farmaz.model.dominio.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Farmaz</title>
    </head>
    <body>
       <center>
            <h3>Lista de Produtos</h3>

            <form name="frmProduto" method='post' action='/farmaz/ServletWeb'>
                <input type='hidden' name='acao' value=''>
                <input type='hidden' name='cod' value=''>
                <input type='hidden' name='table' value='Produto'>
                <table>
                    <tr>
                        <td>
                            Código
                        </td>
                        <td>
                            Nome
                        </td>
                        <td>
                            Receita
                        </td>
                        <td>
                            Descricao
                        </td>
                        <td>
                            Laboratorio
                        </td>
                        <td>
                            Cadastro da Anvisa
                        </td>
                    </tr>
                    <%
                        List<Produto> listProduto = (List<Produto>) request.getAttribute("produtos");
                        for (Produto produto: listProduto) {
                    %>
                            <tr>
                                <td>
                                    <a href="/farmaz/ServletWeb?acao=ListarFarmaciasComProduto=<%=produto.getId()%>"><%=produto.getId()%>
                                </td>
                                <td>
                                    <%=produto.getNome()%>
                                </td>
                                <td>
                                    <%=produto.isReceita()%>
                                </td>
                                <td>
                                    <%=produto.getDescricao()%>
                                </td>
                                <td>
                                    <%=produto.getLaboratorio()%>
                                </td>
                                <td>
                                    <%=produto.getCadastroAnvisa()%>
                                </td>
                  
                            </tr>
                    <%  } %>
                </table>
            </form>
        </center> 
    </body>
</html>
