/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

/**
 *
 * @author Arthur
 */


import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

 
@ManagedBean(name = "MapaBean")
@RequestScoped
public class MapaBean {
    
    private String quantidade;
    
    public MapaBean(){
        
    }
    
    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    
    public void finalizar() throws IOException{
        SessionContext.getInstance().setAttribute("quantidadeProduto", quantidade);
        FacesContext.getCurrentInstance().getExternalContext().redirect("FinalizarCompra.xhtml");
    }
    
    public void carrinho() throws SocketException, UnknownHostException, PersistenciaException, IOException{       
        ManterDisponibilidadeProxy manterDisponibilidade = new ManterDisponibilidadeProxy();
        ArrayList<Disponibilidade> carrinho = new ArrayList();
        List<Disponibilidade> aux;
        Disponibilidade item = new Disponibilidade();

        aux = manterDisponibilidade.getDisponibilidadeByProdutoId((Long)SessionContext.getInstance().getAttribute("produtoSelecionadoId"));
        for(Disponibilidade disponibilidade: aux){
            if(disponibilidade.getFarmaciaCadastro().equals((String) SessionContext.getInstance().getAttribute("farmaciaSelecionadaId"))){
                item = disponibilidade;
            }
        }
        item.setEstoque(Integer.parseInt(quantidade));
        item.setPreco(item.getPreco() * item.getEstoque());

        if(SessionContext.getInstance().getAttribute("MeuCarrinho") != null){
            carrinho = (ArrayList<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
        }

        carrinho.add(item);
        SessionContext.getInstance().setAttribute("MeuCarrinho", carrinho);
        FacesContext.getCurrentInstance().getExternalContext().redirect("ListarProdutosCliente.xhtml");
    }
}
