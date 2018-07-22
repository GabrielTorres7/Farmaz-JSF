/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.ClienteDAOImpl;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.serviceImpl.ManterClienteImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class Login {
    
    public static String executa(HttpServletRequest request){

        String jsp = "";

        try {
            String email;
            String senha;
            
            email = request.getParameter("email");
            senha = request.getParameter("senha");
                       
            
            jsp = ListaProdutos.executa(request);
          
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }

}
