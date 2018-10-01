/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.bean;

import br.cefetmg.farmaz.controller.ListarProdutosCliente;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.proxy.ManterItemPedidoProxy;
import br.cefetmg.farmaz.proxy.ManterPedidoProxy;
import br.cefetmg.farmaz.util.session.SessionContext;
import java.io.IOException;
import java.util.Date;
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
@ManagedBean(name = "FazerPedidoMB")
@ViewScoped
public class FazerPedidoMB {

    /**
     * Creates a new instance of FazerPedidoMB
     */
    public FazerPedidoMB() {
    }

    public void Executa() throws IOException, PersistenciaException, LogicaNegocioException {

        String troco = (String) SessionContext.getInstance().getAttribute("Troco");

        if (Double.parseDouble(troco) < (double) SessionContext.getInstance().getAttribute("Total")) {
            String erro = "Troco não pode ser menor que valor do pedido!";
            SessionContext.getInstance().setAttribute("erro", erro);
            FacesContext.getCurrentInstance ().getExternalContext().redirect("erro.jsp");

        } else {
            Pedido pedido = new Pedido();
            ItemPedido itemPedido = new ItemPedido();
            ManterItemPedidoProxy manterItemPedido = new ManterItemPedidoProxy();
            ManterPedidoProxy manterPedido = new ManterPedidoProxy();
            List<Disponibilidade> carrinho = (List<Disponibilidade>) SessionContext.getInstance().getAttribute("MeuCarrinho");
            Long pedidoId;

            pedido.setClienteId((Long) SessionContext.getInstance().getAttribute("cod_cliente"));
            pedido.setFarmaciaId(carrinho.get(0).getFarmaciaCadastro());
            pedido.setDataHora(new Date());
            pedido.setIdtStatus('F');
            pedido.setTroco(Integer.parseInt((String) SessionContext.getInstance().getAttribute("Troco")));
            pedido.setValor((double) SessionContext.getInstance().getAttribute("Total"));

            pedidoId = manterPedido.criarPedido(pedido);
            for (Disponibilidade disp : carrinho) {
                itemPedido.setPedidoId(pedidoId);
                itemPedido.setProdutoId(disp.getProdutoSeq());
                itemPedido.setQuantidade(disp.getEstoque());
                manterItemPedido.inserirItemPedido(itemPedido);
            }

            FacesContext.getCurrentInstance().getExternalContext()
            .getSessionMap().remove("Total");
            FacesContext.getCurrentInstance().getExternalContext()
            .getSessionMap().remove("MeuCarrinho");
            FacesContext.getCurrentInstance().getExternalContext()
            .getSessionMap().remove("disponibilidadeId");
          
            FacesContext.getCurrentInstance ().getExternalContext().redirect("ListarProdutosCliente.xhtml");

        }

    }

    
    

    }
    
