/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.daoImpl.FarmaciaDAOImpl;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import br.cefetmg.farmaz.model.serviceImpl.ManterFarmaciaImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarFarmacias {
    
    public static String executa(HttpServletRequest request) {
        String jsp;
        
        try {    
            Long produtoId = Long.parseLong(request.getParameter("CodProduto"));
            Long clienteId = (Long) request.getSession().getAttribute("cod_cliente");
            
            // Monta a lista de contatos
            ManterFarmacia manterFarmacia = new ManterFarmaciaImpl(FarmaciaDAOImpl.getInstance());
            List<Farmacia> listFarmacia = manterFarmacia.listAll();
            // Guarda a lista no request
            request.setAttribute("farmacias", listFarmacia);
            
            
            jsp = "ListarFarmacias.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
}
