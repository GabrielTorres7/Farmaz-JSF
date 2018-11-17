/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterItemPedido extends Remote{
    public Long inserirItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean atualizarItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean deletarItemPedido(Long itemPedidoId) throws PersistenciaException, RemoteException;
    public ItemPedido getItemPedidoById(Long itemPedidoId) throws PersistenciaException, RemoteException;
    public List<ItemPedido> getItensPedidoByPedidoId(Long pedidoId) throws PersistenciaException, RemoteException;
}
