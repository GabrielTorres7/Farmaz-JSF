/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterCidadeProxy;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import br.cefetmg.farmaz.proxy.ManterEnderecoProxy;
import br.cefetmg.farmaz.proxy.ManterEstadoProxy;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
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

    private ManterCidadeProxy manterCidade;
    private ManterDisponibilidadeProxy manterDisponibilidade;
    private ManterEnderecoProxy manterEndereco;
    private ManterEstadoProxy manterEstado;
    private ManterFarmaciaProxy manterFarmacia;
    private Farmacia farmaciaSelecionada;
    private Long clienteId;
    private Long produtoId;
    private String enderecoCliente;
    private List<Disponibilidade> listDisponibilidade;

    public ListarFarmaciaMB() throws SocketException, UnknownHostException {
        this.manterDisponibilidade = new ManterDisponibilidadeProxy();
        this.manterFarmacia = new ManterFarmaciaProxy();
        this.manterEndereco = new ManterEnderecoProxy();
        this.manterCidade = new ManterCidadeProxy();
        this.manterEstado = new ManterEstadoProxy();
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
