/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.service;

import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface ManterPedido extends Remote{
    public Long criarPedido(Pedido pedido) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean atualizarPedido(Pedido pedido) throws PersistenciaException, LogicaNegocioException, RemoteException;
    public boolean deletarPedido(Long pedidoId) throws PersistenciaException, RemoteException;
    public Pedido getPedidoById(Long pedidoId) throws PersistenciaException, RemoteException;
    public List<Pedido> getPedidosByClienteId(Long clienteId) throws PersistenciaException, RemoteException;
    public List<Pedido> getPedidosByFarmaciaId(Long farmaciaId) throws PersistenciaException, RemoteException;
    public List<Pedido> getPedidosByClienteIdAndStatus(Long clienteId, char status) throws PersistenciaException, RemoteException;
    public List<Pedido> getPedidosByFarmaciaIdAndStatus(Long farmaciaId, char status) throws PersistenciaException, RemoteException;
}
