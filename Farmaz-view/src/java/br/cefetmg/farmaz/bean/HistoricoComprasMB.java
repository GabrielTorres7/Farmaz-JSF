/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.model.dominio.Farmacia;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterFarmaciaProxy;
import br.cefetmg.farmaz.proxy.ManterPedidoProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gabriel
 */
@ManagedBean(name="HistoricoComprasMB")
@ViewScoped
public class HistoricoComprasMB {
    
    private ManterPedidoProxy manterPedido;
    private ManterFarmaciaProxy manterFarmacia;
    private Pedido pedidoSelecionado;
    private Long clienteId;
    
    public HistoricoComprasMB() throws SocketException, UnknownHostException {
        this.manterPedido = new ManterPedidoProxy();
        this.manterFarmacia = new ManterFarmaciaProxy();
    }
    
    public void setPedidoSelecionado(Pedido pedidoSelecionado){
        this.pedidoSelecionado = pedidoSelecionado;
    }
    
    public Pedido getPedidoSelecionado(){
        return pedidoSelecionado;
    }
    
    public List<Pedido> getHistorico() throws PersistenciaException {
        
        //clienteId =  (Long) SessionContext.getInstance().getAttribute("clienteId");
        clienteId = Long.parseLong("8");
        List<Pedido> pedidos = manterPedido.getPedidosByClienteId(clienteId);
        if(pedidos.size() > 1)
            Collections.reverse(pedidos);
        
        return pedidos;
    }
}
