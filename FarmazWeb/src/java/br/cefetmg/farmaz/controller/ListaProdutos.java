/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.ProdutoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.model.serviceImpl.ManterProdutoImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class ListaProdutos {
    
    public static String executa(HttpServletRequest request) {
        String jsp;
        
        try {    
            // Monta a lista de contatos
      //      ManterProduto manterProduto = new ManterProdutoImpl(ProdutoDAOImpl.getInstance());
        //    List<Produto> listProduto = manterProduto.listAll();
            // Guarda a lista no request
          //  request.setAttribute("produtos", listProduto);
            
            jsp = "listaProdutos.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
