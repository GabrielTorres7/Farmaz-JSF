/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import br.cefetmg.farmaz.model.service.ManterItemPedido;
import br.cefetmg.farmaz.model.service.ManterPedido;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import br.cefetmg.farmaz.proxy.ManterItemPedidoProxy;
import br.cefetmg.farmaz.proxy.ManterPedidoProxy;
import br.cefetmg.farmaz.proxy.ManterProdutoProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gabriel
 */
@ManagedBean(name = "HistoricoComprasMB")
@ViewScoped
public class HistoricoComprasMB {

    private Registry registry;
    private ManterPedido manterPedido;
    private ManterDisponibilidade manterDisponibilidade;
    private ManterFarmacia manterFarmacia;
    private ManterItemPedido manterItemPedido;
    private ManterProduto manterProduto;
    private Pedido pedidoSelecionado;
    private Long clienteId;
    private String nomeProduto;

    public HistoricoComprasMB() throws SocketException, UnknownHostException, RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry("localhost", 2345);
        this.manterPedido = (ManterPedido) registry.lookup("ManterPedido");
        this.manterDisponibilidade = (ManterDisponibilidade) registry.lookup("ManterDisponibilidade");
        this.manterItemPedido = (ManterItemPedido) registry.lookup("ManterItemPedido");
        this.manterProduto = (ManterProduto) registry.lookup("ManterProduto");
        this.manterFarmacia = (ManterFarmacia) registry.lookup("ManterFarmacia");
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setNomeProduto(String nomeProduto) throws PersistenciaException {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeProduto(Long produtoId) throws PersistenciaException, RemoteException {
        return manterProduto.getProdutoById(produtoId).getNome();
    }

    public String getNomeFarmacia(Long farmaciaId) throws PersistenciaException, RemoteException {
        return manterFarmacia.getFarmaciaById(Long.toString(farmaciaId)).getNome();
    }

    public String getStatus(char status) {
        if (status == 'F') {
            return "Realizado";
        } else if (status == 'P') {
            return "Preparando envio";
        } else if (status == 'A') {
            return "A caminho";
        } else if (status == 'C') {
            return "Concluído";
        }
        return "";
    }

    public List<Pedido> getHistorico() throws PersistenciaException, IOException {
        clienteId = (Long) SessionContext.getInstance().getAttribute("clienteId");
        List<Pedido> pedidos = null;

        if (manterPedido.getPedidosByClienteId(clienteId) != null) {
            pedidos = manterPedido.getPedidosByClienteId(clienteId);
            if (pedidos.size() > 1) {
                Collections.reverse(pedidos);
            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ListarProdutosCliente.xhtml");
        }
        return pedidos;
    }

    public List<ItemPedido> getItens() throws PersistenciaException, RemoteException {
        List<ItemPedido> itens = manterItemPedido.getItensPedidoByPedidoId(pedidoSelecionado.getPedidoId());

        return itens;
    }

}
