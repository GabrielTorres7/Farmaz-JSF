/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterCidade;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.service.ManterEndereco;
import br.cefetmg.farmaz.model.service.ManterEstado;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Gabriel
 */
@ManagedBean(name = "ListarFarmaciaMB")
@ViewScoped
public class ListarFarmaciaMB {

    private Registry registry;
    private ManterCidade manterCidade;
    private ManterDisponibilidade manterDisponibilidade;
    private ManterEndereco manterEndereco;
    private ManterEstado manterEstado;
    private ManterFarmacia manterFarmacia;
    private Farmacia farmaciaSelecionada;
    private Long clienteId;
    private Long produtoId;
    private String enderecoCliente;
    private List<Disponibilidade> listDisponibilidade;

    public ListarFarmaciaMB() throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry("localhost", 2345);
        this.manterDisponibilidade = (ManterDisponibilidade) registry.lookup("ManterDisponibilidade");
        this.manterFarmacia = (ManterFarmacia) registry.lookup("ManterFarmacia");
        this.manterEndereco = (ManterEndereco) registry.lookup("ManterEndereco");
        this.manterCidade = (ManterCidade) registry.lookup("ManterCidade");
        this.manterEstado = (ManterEstado) registry.lookup("ManterEstado");
    }

    public void setFarmaciaSelecionada(Farmacia farmaciaSelecionada) {
        this.farmaciaSelecionada = farmaciaSelecionada;
    }

    public Farmacia getFarmaciaSelecionada() {
        return farmaciaSelecionada;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public double getPreco(Long farmaciaId) {
        for (Disponibilidade disponibilidade : listDisponibilidade) {
            if (Long.parseLong(disponibilidade.getFarmaciaCadastro()) == farmaciaId) {
                return disponibilidade.getPreco();
            }
        }
        return 0;
    }

    public List<Farmacia> getFarmacias() throws PersistenciaException {
        ArrayList<Farmacia> listFarmacia = new ArrayList();

        clienteId = (Long) SessionContext.getInstance().getAttribute("clienteId");
        produtoId = (Long) SessionContext.getInstance().getAttribute("produtoSelecionadoId");

        listDisponibilidade = manterDisponibilidade.getDisponibilidadeByProdutoId(produtoId);
        for (Disponibilidade disponibilidade : listDisponibilidade) {
            if (disponibilidade.getEstoque() == 0) {
                listDisponibilidade.remove(disponibilidade);
            } else {
                listFarmacia.add(manterFarmacia.getFarmaciaById(disponibilidade.getFarmaciaCadastro()));
            }
        }

        return listFarmacia;
    }

    public void onRowSelect(SelectEvent event) throws IOException {
        SessionContext.getInstance().setAttribute("farmaciaSelecionadaId", ((Farmacia) event.getObject()).getCadastroPrefeitura());
        FacesContext.getCurrentInstance().getExternalContext().redirect("Mapa.xhtml");
    }
}
