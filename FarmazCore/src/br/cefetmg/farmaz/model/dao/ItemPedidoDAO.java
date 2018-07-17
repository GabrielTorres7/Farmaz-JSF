/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.dao;

import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ItemPedidoDAO {
    public Long insert(ItemPedido itemPedido) throws PersistenciaException;
    public boolean update(ItemPedido itemPedido) throws PersistenciaException;
    public boolean remove(Long pedidoId, Long produtoId) throws PersistenciaException;
    public ItemPedido getItemPedidoById(Long pedidoId, Long produtoId) throws PersistenciaException;
    public List<ItemPedido> getItensPedidoByPedidoId(Long pedidoId) throws PersistenciaException;
}
