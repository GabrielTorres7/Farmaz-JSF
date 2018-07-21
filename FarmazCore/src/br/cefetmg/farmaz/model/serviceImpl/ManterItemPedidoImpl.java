/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.serviceImpl;

import br.cefetmg.farmaz.model.daoImpl.ItemPedidoDAOImpl;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.exception.LogicaNegocioException;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.model.service.ManterItemPedido;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ManterItemPedidoImpl implements ManterItemPedido{
    
    private final ItemPedidoDAOImpl itemPedidoDAO;

    public ManterItemPedidoImpl(ItemPedidoDAOImpl itemPedidoDAO) {
        this.itemPedidoDAO = itemPedidoDAO;
    }
    
    @Override
    public Long inserirItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizarItemPedido(ItemPedido itemPedido) throws PersistenciaException, LogicaNegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletarItemPedido(Long itemPedidoId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemPedido getItemPedidoById(Long itemPedidoId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemPedido> getItensPedidoByPedidoId(Long pedidoId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
