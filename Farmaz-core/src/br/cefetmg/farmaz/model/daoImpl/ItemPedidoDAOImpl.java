/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.ItemPedidoDAO;
import br.cefetmg.farmaz.model.dominio.ItemPedido;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Gabriel
 */
public class ItemPedidoDAOImpl implements ItemPedidoDAO {

    private static ItemPedidoDAOImpl itemPedidoDAO = null;

    private ItemPedidoDAOImpl() {
    }

    public static ItemPedidoDAOImpl getInstance() {
        if (itemPedidoDAO == null) {
            itemPedidoDAO = new ItemPedidoDAOImpl();
        }
        return itemPedidoDAO;
    }

    @Override
    public Long insert(ItemPedido itemPedido) throws PersistenciaException {
        if (itemPedido == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long itemPedidoId = null;

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(itemPedido);
            manager.getTransaction().commit();

            itemPedidoId = itemPedido.getPedidoId();

            manager.close();
            factory.close();

            return itemPedidoId;

        } catch (Exception ex) {
            Logger.getLogger(FarmaciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public boolean update(ItemPedido itemPedido) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(itemPedido);
            manager.getTransaction().commit();

            manager.close();
            factory.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public boolean remove(Long itemPedidoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            ItemPedido itemPedido = manager.find(ItemPedido.class, itemPedidoId);

            manager.getTransaction().begin();
            manager.remove(itemPedido);
            manager.getTransaction().commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public ItemPedido getItemPedidoById(Long itemPedidoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            ItemPedido itemPedido = manager.find(ItemPedido.class, itemPedidoId);

            return itemPedido;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<ItemPedido> getItensPedidoByPedidoId(Long pedidoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM item_pedido WHERE seq_pedido = ? ORDER BY seq_produto");

            List<ItemPedido> list = query.getResultList();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

}
