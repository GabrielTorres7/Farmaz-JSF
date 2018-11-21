/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.controller;

/**
 *
 * @author Arthur
 */
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterDisponibilidade;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "MapaBean")
@RequestScoped
public class MapaBean {

    private Registry registry;
    private ManterDisponibilidade manterDisponibilidade;
    private String quantidade;

    public MapaBean() throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry("localhost", 2345);
        this.manterDisponibilidade = (ManterDisponibilidade) registry.lookup("ManterDisponibilidade");
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public void finalizar() throws IOException, SocketException, PersistenciaException {
        adicionaNoCarrinho();
        FacesContext.getCurrentInstance().getExternalContext().redirect("MeuCarrinho.xhtml");
    }

    public void carrinho() throws SocketException, UnknownHostException, PersistenciaException, IOException {
        adicionaNoCarrinho();
        FacesContext.getCurrentInstance().getExternalContext().redirect("ListarProdutosCliente.xhtml");
    }

    public void adicionaNoCarrinho() throws SocketException, PersistenciaException, UnknownHostException, RemoteException {
        ArrayList<Disponibilidade> carrinho = new ArrayList();
        List<Disponibilidade> aux;
        Disponibilidade item = new Disponibilidade();

        aux = manterDisponibilidade.getDisponibilidadeByProdutoId((Long) SessionContext.getInstance().getAttribute("produtoSelecionadoId"));
        for (Disponibilidade disponibilidade : aux) {
            if (disponibilidade.getFarmaciaCadastro().equals((String) SessionContext.getInstance().getAttribute("farmaciaSelecionadaId"))) {
                item = disponibilidade;
            }
        }
        item.setEstoque(Integer.parseInt(quantidade));
        item.setPreco(item.getPreco() * item.getEstoque());

        if (SessionContext.getInstance().getAttribute("MeuCarrinho") != null) {
            carrinho = (ArrayList<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
        }

        carrinho.add(item);
        SessionContext.getInstance().setAttribute("MeuCarrinho", carrinho);
    }
}
