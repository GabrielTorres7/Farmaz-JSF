package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterProdutoProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Arthur
 */

@ManagedBean(name="MeuCarrinhoBean")
@ViewScoped
public class MeuCarrinhoBean {
     
    private List<Disponibilidade> carrinho;
    private Disponibilidade itemSelecionado;
    private ManterProdutoProxy manterProduto;
    private String nomeProduto;
    
    public MeuCarrinhoBean() throws SocketException, UnknownHostException{
        this.manterProduto = new ManterProdutoProxy();
    }

    public String getNomeProduto(Long produtoId) throws PersistenciaException{
        return manterProduto.getProdutoById(produtoId).getNome();
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public void setCarrinho(List<Disponibilidade> carrinho) throws SocketException, UnknownHostException{
        this.carrinho = carrinho;
        this.manterProduto = new ManterProdutoProxy();
    }
    
    public List<Disponibilidade> getCarrinho(){
        carrinho = (List<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
        return carrinho;
    }

    public Disponibilidade getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(Disponibilidade itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }
    
    public void excluiItem(ActionEvent event) throws IOException{
        carrinho.clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("MeuCarrinho.xhtml");
        PrimeFaces.current().ajax().update("carrinho");
    }

    public void existeCarrinho() throws IOException{
        if(SessionContext.getInstance().getAttribute("MeuCarrinho") == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR, "Erro: ", "Você ainda não adicionou nenhum produto no carrinho!" ));
            
            }else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("MeuCarrinho.xhtml");
            }
    }
    
}
