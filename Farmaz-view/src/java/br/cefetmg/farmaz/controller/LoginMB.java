/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCliente;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */

@Named(value = "LoginBean")
@SessionScoped
public class LoginMB implements Serializable {
    private Registry registry;
    private ManterCliente manterCliente;
    
    private String email;
    private String senha;
    private Cliente cliente;
     
    public LoginMB()throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry("localhost", 2345);
        this.manterCliente = (ManterCliente) registry.lookup("ManterCliente");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSenha(String senha) {
         this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    private Cliente getCliente() throws PersistenciaException, RemoteException, NotBoundException{
       return manterCliente.getClienteByEmailSenha(email, senha);
    }
    
    public void enviar() throws PersistenciaException, IOException, RemoteException, NotBoundException{    
        cliente = getCliente();
        
        if(cliente == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Usuário não encontrado, ", "email ou senha inválidos" ) );
            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Seja bem vindo ", ""+ cliente.getNome() ) );
            SessionContext.getInstance().setAttribute("clienteId", cliente.getId());
            
            //redireciona para ListarProdutos
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/ListarProdutosCliente.xhtml");

        }
    }
    
    public void encerrarSessao() throws IOException{
        SessionContext.getInstance().encerrarSessao();
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
    }

}
