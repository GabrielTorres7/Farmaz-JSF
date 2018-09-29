/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterProdutoProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Gabriel
 */
@ManagedBean(name="ManterProdutoMB")
@ViewScoped
public class ManterProdutoMB implements Serializable{
    
    ManterProdutoProxy manterProduto;
    private Produto produtoSelecionado;
    
    public ManterProdutoMB() throws SocketException, UnknownHostException {
        this.manterProduto = new ManterProdutoProxy();
    }
    
    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
    
    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }
    
    public List<Produto> getListProduto() throws PersistenciaException {
        return manterProduto.listAll();
    }
    
    public void onRowSelect(SelectEvent event) throws IOException {
        SessionContext.getInstance().setAttribute("produtoSelecionadoId", ((Produto) event.getObject()).getId());
        FacesContext.getCurrentInstance().getExternalContext().redirect("ListarFarmacias.xhtml");
    }
     
}
