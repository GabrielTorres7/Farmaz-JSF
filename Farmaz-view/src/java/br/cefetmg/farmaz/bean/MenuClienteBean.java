/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hiago
 */
@ManagedBean(name="MenuClienteBean")
@ViewScoped
public class MenuClienteBean {

    /**
     * Creates a new instance of MenuClienteBean
     */
       public void botao() {
           System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaasdasdasdasdasdas");
    voltaLogin();
    }
    public String voltaLogin() {
    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
    return "Login.xhtml";
    }

    }


