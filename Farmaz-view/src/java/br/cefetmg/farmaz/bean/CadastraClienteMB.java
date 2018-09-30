/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Cliente;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterClienteProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "CadastraClienteMB")
@SessionScoped
public class CadastraClienteMB implements Serializable {

    private String email;
    private String senha;
    private String nome;
    private String telefone;
    private String documentoIdentificacao;

    private Cliente cliente;
    private ManterClienteProxy manterCliente;

    public CadastraClienteMB() throws SocketException, UnknownHostException {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumentoIdentificacao() {
        return documentoIdentificacao;
    }

    public void setDocumentoIdentificacao(String documentoIdentificacao) {
        this.documentoIdentificacao = documentoIdentificacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void cadastraCliente() throws PersistenciaException, SocketException, UnknownHostException, LogicaNegocioException {
        cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setDocumentoIdentificacao(documentoIdentificacao);
        cliente.setNumeroTelefone(telefone);
        cliente.setSenha(senha);

        manterCliente = new ManterClienteProxy();
        manterCliente.cadastrarCliente(cliente);
    }

    public void enviar() throws PersistenciaException, IOException, SocketException, UnknownHostException, LogicaNegocioException {
        cadastraCliente();
        System.out.println("nome:"+cliente.getNome());
        if (cliente == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente não encontrado.", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seja bem vindo ", "" + cliente.getNome()));
            SessionContext.getInstance().setAttribute("clienteId", cliente.getId());
            System.out.println("nome 1 :"+cliente.getNome());
            //redireciona para ListarProdutos
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/ListarProdutosCliente.xhtml");
            System.out.println("nome 2:"+cliente.getNome());
        }
    }

    public void encerrarSessao() throws IOException {
        SessionContext.getInstance().encerrarSessao();
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
    }

}
