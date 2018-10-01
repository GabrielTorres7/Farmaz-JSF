/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.dominio.Produto;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterDisponibilidadeProxy;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import br.cefetmg.farmaz.proxy.ManterProdutoProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hiago
 */
@ManagedBean(name="FinalizarCompraMB")
@ViewScoped
public class FinalizarCompraMB {

    /**
     * @return the visibilidade
     */


    ManterDisponibilidadeProxy manterDisponibilidade =  new ManterDisponibilidadeProxy();
    private Disponibilidade item = new Disponibilidade();
    List<Disponibilidade> carrinho;
    private String troco ;
    FazerPedidoMB pedido =  new FazerPedidoMB();

    private boolean visibilidade = false;

    public List<Disponibilidade> getCarrinho(){
        carrinho = (List<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
        return carrinho;
    }
    
    public FinalizarCompraMB() throws SocketException, UnknownHostException {
    }
   
    public boolean isVisibilidade() {
        return visibilidade;
    }
    public void show(){
        visibilidade=true;
    }
    
    public void hide(){
        visibilidade=false;
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
    public void enviar() throws IOException, PersistenciaException{
        SessionContext.getInstance().setAttribute("troco", getTroco());
        Double total = 0.0;
                        Produto produto;
                        Farmacia farmacia;
                        String nomeProduto;
                        String nomeFarmacia;
                        ManterProdutoProxy manterProduto = new ManterProdutoProxy();
                        ManterFarmaciaProxy manterFarmacia = new ManterFarmaciaProxy();
                        List<Disponibilidade> carrinho =(List<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
                        for (Disponibilidade item: carrinho) {
                            produto = manterProduto.getProdutoById(item.getProdutoSeq());
                            farmacia = manterFarmacia.getFarmaciaById(item.getFarmaciaCadastro());
                            nomeProduto = produto.getNome();
                            nomeFarmacia = farmacia.getNome();
                            total += item.getPreco();
                            SessionContext.getInstance().setAttribute("Total",total);
                        }
        pedido.Executa();
       
    }
            

    
    
    
}
