/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.DisponibilidadeDAOImpl;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.serviceImpl.ManterDisponibilidadeImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class FinalizarCompra {
    
    public static String executa(HttpServletRequest request) throws ServletException, IOException {
        
        String jsp;
        
        try {
            ManterDisponibilidade manterDisponibilidade = new ManterDisponibilidadeImpl(DisponibilidadeDAOImpl.getInstance());
            Disponibilidade item = new Disponibilidade();
            
            if(request.getParameter("ultimoJsp") == "Mapa"){
                item = manterDisponibilidade.getDisponibilidadeById(Long.parseLong(request.getParameter("disponibilidadeId")));
                item.setEstoque(Integer.parseInt(request.getParameter("quantidadeProduto")));
                item.setPreco(item.getPreco() * item.getEstoque());
                
            }else if(request.getParameter("ultimoJsp") == "Carrinho"){
                
            }
            
            jsp = "";
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
