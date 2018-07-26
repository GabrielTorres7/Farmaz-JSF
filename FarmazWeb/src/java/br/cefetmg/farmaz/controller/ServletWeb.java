/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class ServletWeb extends HttpServlet {
   private String jsp = "";

   @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");

        if(acao.equals("Login"))
            jsp = Login.executa(request);
        else if(acao.equals("CadastraCliente"))
            jsp = CadastrarCliente.executa(request);
        else if(acao.equals("CadastraFarmacia"))
            jsp = CadastrarFarmacia.executa(request);
        else if(acao.equals("ListarFarmaciasComProduto"))
            jsp = ListarFarmacias.executa(request);
        else if(acao.equals("MostrarMapa"))
            jsp = MostrarMapa.executa(request);
        else if(acao.equals("Finalizar Compra"))
            jsp = "";
        else if(acao.equals("Adicionar ao Meu Carrinho"))
            jsp = "";
        
            RequestDispatcher rd = request.getRequestDispatcher(jsp);
            rd.forward(request, response);  
        
    }   
}
