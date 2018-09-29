/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterClienteProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */

@Named(value = "LoginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private String email;
    private String senha;
    private Cliente cliente;
    private ManterClienteProxy manterCliente;
     
    public LoginBean() throws SocketException, UnknownHostException {
        this.manterCliente = new ManterClienteProxy();
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

    public Cliente getCliente() throws PersistenciaException{
        return manterCliente.getClienteByEmailSenha(email, senha) ;
    }
    
    public void enviar() throws PersistenciaException, IOException{    
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

}
