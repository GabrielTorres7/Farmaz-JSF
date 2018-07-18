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

/**
 *
 * @author Gabriel
 */
public class Login {
    
    @SuppressWarnings("static-access")
    public static String execute(HttpServletRequest request) {

        String jsp = "";

        try {
            String email;
            String senha;
            
            email = request.getParameter("email");
            senha = request.getParameter("senha");
                       
            ManterClienteImpl manterCliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
            Cliente cliente = manterCliente.getClienteByEmail(email);

            if (cliente == null) {
                String erro = "Usuario nao encontrado!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            } else if(senha.equals(cliente.getSenha())){
                jsp = ListarProdutos.execute(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
