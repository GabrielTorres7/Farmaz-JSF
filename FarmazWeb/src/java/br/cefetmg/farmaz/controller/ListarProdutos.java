/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.ClienteDAOImpl;
import br.cefetmg.farmaz.model.daoImpl.ProdutoDAOImpl;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.model.serviceImpl.ManterClienteImpl;
import br.cefetmg.farmaz.model.serviceImpl.ManterProdutoImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarProdutos {
    
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        
        try {    
            ManterProduto manterProduto = new ManterProdutoImpl(ProdutoDAOImpl.getInstance());
            List<Produto> listProduto = manterProduto.listAll();
            
            jsp = "notFoward";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
