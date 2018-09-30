/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

/**
 *
 * @author Arthur
 */


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

 
@ManagedBean(name = "MapaBean")
@RequestScoped
public class MapaBean {
    
    private Double quantidade;
    
    public MapaBean(){
        
    }
    
    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    
    public void finalizar(){
        
    }
    
    
}
