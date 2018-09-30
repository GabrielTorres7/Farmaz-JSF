/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Hiago
 */
@ManagedBean(name="FinalizarCompraMB")
@ViewScoped
public class FinalizarCompraMB {

    /**
     * Creates a new instance of FinalizarCompraMB
     */
    public FinalizarCompraMB() {
    }
    private boolean visibilidade = false;
    
    public void show(){
        visibilidade=true;
    }
    
    public void hide(){
        visibilidade=false;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }
    
}
