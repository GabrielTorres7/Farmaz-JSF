/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Gabriel
 */
@Entity
public class ItemPedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_item_pedido")
    private Long itemPedidoId;
    
    @Column(name = "seq_pedido")
    private Long pedidoId;
    
    @Column(name = "seq_produto")
    private Long produtoId;
    
    private int quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Long pedidoId, Long produtoId, int quantidade) {
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Long getItemPedidoId() {
        return itemPedidoId;
    }

    public void setItemPedidoId(Long itemPedidoId) {
        this.itemPedidoId = itemPedidoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
