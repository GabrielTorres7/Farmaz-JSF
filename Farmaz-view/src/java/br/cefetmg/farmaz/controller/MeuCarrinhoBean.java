package br.cefetmg.farmaz.controller;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import br.cefetmg.farmaz.proxy.ManterProdutoProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
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

    private List<Disponibilidade> carrinho;
    private Disponibilidade itemSelecionado;
    private ManterProdutoProxy manterProduto;
    private ManterFarmaciaProxy manterFarmacia;
    private String nomeProduto;
    private double total = 0;

    public MeuCarrinhoBean() throws SocketException, UnknownHostException {
        this.manterProduto = new ManterProdutoProxy();
        this.manterFarmacia = new ManterFarmaciaProxy();
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

    public String getNomeProduto(Long produtoId) throws PersistenciaException {
        return manterProduto.getProdutoById(produtoId).getNome();
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setCarrinho(List<Disponibilidade> carrinho) throws SocketException, UnknownHostException {
        this.carrinho = carrinho;
        this.manterProduto = new ManterProdutoProxy();
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

    public String getNomeFarmacia(String farmaciaId) throws PersistenciaException {
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
