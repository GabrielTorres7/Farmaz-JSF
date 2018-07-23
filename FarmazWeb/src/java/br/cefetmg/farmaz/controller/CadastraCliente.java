/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.ClienteDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.dominio.Endereco;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.serviceImpl.ManterClienteImpl;

/**
 *
 * @author Gabriel
 */
public class CadastraCliente {

    
    public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {
            Endereco endereco;
            Cliente cliente;
            ManterClienteImpl manterCliente;

            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String documentoIdentificacao = request.getParameter("documento_identificacao");
            String telefone = request.getParameter("telefone");
            String senha = request.getParameter("senha");
            String rua = request.getParameter("rua");
            String numero = request.getParameter("numero");
            String bairro = request.getParameter("bairro");

            cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setDocumentoIdentificacao(documentoIdentificacao);
            cliente.setNumeroTelefone(telefone);
            cliente.setSenha(senha);

            manterCliente = new ManterClienteImpl(ClienteDAOImpl.getInstance());
            manterCliente.cadastrarCliente(cliente);
            
            request.setAttribute("email", email);
            request.setAttribute("senha", senha);
            request.setAttribute("tipo", "cadastro");
            
            jsp = Login.executa(request);

        } catch (PersistenciaException | LogicaNegocioException ex) {
             System.out.println(ex);
             jsp = "";
        }
        
        return jsp;

    }
}