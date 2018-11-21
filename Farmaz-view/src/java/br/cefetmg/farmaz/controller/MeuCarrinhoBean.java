package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterFarmacia;
import br.cefetmg.farmaz.model.service.ManterProduto;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Arthur
 */
@ManagedBean(name = "MeuCarrinhoBean")
@ViewScoped
public class MeuCarrinhoBean {

    private Registry registry;
    private List<Disponibilidade> carrinho;
    private Disponibilidade itemSelecionado;
    private ManterProduto manterProduto;
    private ManterFarmacia manterFarmacia;
    private String nomeProduto;
    private double total = 0;

    public MeuCarrinhoBean() throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry("localhost", 2345);
        this.manterProduto = (ManterProduto) registry.lookup("ManterProduto");
        this.manterFarmacia = (ManterFarmacia) registry.lookup("ManterFarmacia");
        carrinho = (List<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
        setTotal();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        for (Disponibilidade disp : carrinho) {
            total += disp.getPreco();
        }
    }

    public String getNomeProduto(Long produtoId) throws PersistenciaException, RemoteException {
        return manterProduto.getProdutoById(produtoId).getNome();
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setCarrinho(List<Disponibilidade> carrinho) throws RemoteException, NotBoundException {
        this.carrinho = carrinho;
        this.manterProduto = (ManterProduto) registry.lookup("ManterProduto");
    }

    public List<Disponibilidade> getCarrinho() {
        carrinho = (List<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
        return carrinho;
    }

    public Disponibilidade getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(Disponibilidade itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public String getNomeFarmacia(String farmaciaId) throws PersistenciaException, RemoteException {
        return manterFarmacia.getFarmaciaById(farmaciaId).getNome();
    }

    public void excluiItem() throws IOException {
        for (int i = 0; i < carrinho.size(); i++) {
            if (carrinho.get(i).getId() == itemSelecionado.getId()) {
                carrinho.remove(carrinho.get(i));
            }
        }
        if (carrinho.size() > 0) {
            getTotal();
            FacesContext.getCurrentInstance().getExternalContext().redirect("MeuCarrinho.xhtml");
        } else {
            SessionContext.getInstance().deleteAttribute("MeuCarrinho");
            FacesContext.getCurrentInstance().getExternalContext().redirect("ListarProdutosCliente.xhtml");
        }
    }

    public void existeCarrinho() throws IOException {
        if (SessionContext.getInstance().getAttribute("MeuCarrinho") == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", "Você ainda não adicionou nenhum produto no carrinho!"));

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("MeuCarrinho.xhtml");
        }
    }

    public void finalizar() throws IOException {
        SessionContext.getInstance().setAttribute("Total", total);
        FacesContext.getCurrentInstance().getExternalContext().redirect("FinalizarCompra.xhtml");
    }
}
