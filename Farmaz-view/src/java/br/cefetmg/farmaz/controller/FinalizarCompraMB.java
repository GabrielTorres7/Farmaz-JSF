/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import br.cefetmg.farmaz.proxy.ManterItemPedidoProxy;
import br.cefetmg.farmaz.proxy.ManterPedidoProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hiago
 */
@ManagedBean(name = "FinalizarCompraMB")
@ViewScoped
public class FinalizarCompraMB {

    /**
     * @return the visibilidade
     */
    ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
    private Disponibilidade item = new Disponibilidade();
    List<Disponibilidade> carrinho;
    private String troco;

    private boolean visibilidade = false;

    public List<Disponibilidade> getCarrinho() {
        carrinho = (List<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
        return carrinho;
    }

    public FinalizarCompraMB() throws SocketException, UnknownHostException {
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void show() {
        visibilidade = true;
    }

    public void hide() {
        visibilidade = false;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public String getTroco() {
        return troco;
    }

    public void enviar() throws IOException, PersistenciaException, LogicaNegocioException {

        if (Double.parseDouble(troco) < (double) SessionContext.getInstance().getAttribute("Total")) {
            String erro = "Troco não pode ser menor que valor do pedido!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", erro));
        } else {
            Pedido pedido = new Pedido();
            ItemPedido itemPedido = new ItemPedido();
            ManterItemPedidoProxy manterItemPedido = new ManterItemPedidoProxy();
            ManterPedidoProxy manterPedido = new ManterPedidoProxy();
            List<Disponibilidade> carrinho = (List<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
            Long pedidoId;

            pedido.setClienteId((Long) SessionContext.getInstance().getAttribute("clienteId"));
            pedido.setFarmaciaId(carrinho.get(0).getFarmaciaCadastro());
            pedido.setDataHora(new Date());
            pedido.setIdtStatus('F');
            pedido.setTroco(Integer.parseInt(troco));
            pedido.setValor((double) SessionContext.getInstance().getAttribute("Total"));

            pedidoId = manterPedido.criarPedido(pedido);

            for (Disponibilidade disp : carrinho) {
                itemPedido.setPedidoId(pedidoId);
                itemPedido.setProdutoId(disp.getProdutoSeq());
                itemPedido.setQuantidade(disp.getEstoque());
                manterItemPedido.inserirItemPedido(itemPedido);
            }

            SessionContext.getInstance().deleteAttribute("Total");
            SessionContext.getInstance().deleteAttribute("MeuCarrinho");

            FacesContext.getCurrentInstance().getExternalContext().redirect("ListarProdutosCliente.xhtml");

        }

    }

}
