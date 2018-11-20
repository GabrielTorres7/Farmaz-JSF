/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterEstado;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "CadastraFarmaciaMB")
@SessionScoped
public class CadastraFarmaciaMB implements Serializable {
    private Registry registry;
    private ManterFarmacia manterFarmacia;
    private ManterEstado manterEstado;
    private String cnpj;
    private String cadastroPrefeitura;
    private String nome;
    private String cep;
    private String bairro;
    private int numero;
    private String rua;
    private String cidade;
    private String estado;
    private String email;
    private String senha;
    
    private Farmacia farmacia;

    public CadastraFarmaciaMB() throws SocketException, UnknownHostException, RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry("localhost", 2345);
        this.manterFarmacia = (ManterFarmacia) registry.lookup("ManterFarmacia");
        this.manterEstado = (ManterEstado) registry.lookup("ManterEstado");
    }

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public ManterFarmacia getManterFarmacia() {
        return manterFarmacia;
    }

    public void setManterFarmacia(ManterFarmacia manterFarmacia) {
        this.manterFarmacia = manterFarmacia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCadastroPrefeitura() {
        return cadastroPrefeitura;
    }

    public void setCadastroPrefeitura(String cadastroPrefeitura) {
        this.cadastroPrefeitura = cadastroPrefeitura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public void cadastraFarmacia() throws PersistenciaException, SocketException, UnknownHostException, LogicaNegocioException, RemoteException {   
        farmacia = new Farmacia();
        farmacia.setNome(nome);
        farmacia.setEmail(email);
        farmacia.setCadastroPrefeitura(cadastroPrefeitura);
        farmacia.setBairro(bairro);
        farmacia.setSenha(senha);
        farmacia.setCep(cep);
        farmacia.setCnpj(cnpj);
        farmacia.setCodCidade(10L);
        farmacia.setNumero(numero);
        farmacia.setRua(rua);
                
        manterFarmacia.cadastrarFarmacia(farmacia);
    }

    public void enviar() throws PersistenciaException, IOException, SocketException, UnknownHostException, LogicaNegocioException {
        cadastraFarmacia();

        if (farmacia == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente não encontrado.", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seja bem vindo ", "" + farmacia.getNome()));
            SessionContext.getInstance().setAttribute("farmaciaId", farmacia.getCadastroPrefeitura());

            //redireciona para ListarProdutos
            FacesContext.getCurrentInstance().getExternalContext().redirect("ListarProdutosFarmacia.xhtml");
        }
    }

    public void encerrarSessao() throws IOException {
        SessionContext.getInstance().encerrarSessao();
        FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
    }

}