<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String erro;
    erro = (String) request.getAttribute("erro");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
    </head>
    <body>
        <h:form>
            <p:growl id="msgs" showDetail="true" /> 
            <p:panel id="basic" header="Basic" footer="" style="margin-bottom:20px">
                <h:panelGrid columns="2" cellpadding="10">
                <p:graphicImage name="" />
                <h:outputText value="" />
        </h:panelGrid>
            </p:panel>
        <h1>Erro:</h1>
        <h2><%=erro%></h2>
        </h:form>
    </body>
</html>
