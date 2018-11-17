/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.PedidoDAO;
import br.cefetmg.farmaz.model.dominio.Pedido;
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
public class PedidoDAOImpl implements PedidoDAO {

    private static PedidoDAOImpl pedidoDAO = null;

    private PedidoDAOImpl() {
    }

    public static PedidoDAOImpl getInstance() {
        if (pedidoDAO == null) {
            pedidoDAO = new PedidoDAOImpl();
        }
        return pedidoDAO;
    }

    @Override
    public Long insert(Pedido pedido) throws PersistenciaException {
        if (pedido == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long pedidoId = null;

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(pedido);
            manager.getTransaction().commit();

            pedidoId = pedido.getPedidoId();

            manager.close();
            factory.close();

            return pedidoId;

        } catch (Exception ex) {
            Logger.getLogger(FarmaciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public boolean update(Pedido pedido) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(pedido);
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
    public boolean remove(Long pedidoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Pedido pedido = manager.find(Pedido.class, pedidoId);

            manager.getTransaction().begin();
            manager.remove(pedido);
            manager.getTransaction().commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Pedido getPedidoById(Long pedidoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Pedido pedido = manager.find(Pedido.class, pedidoId);

            return pedido;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Pedido> getPedidosByClienteId(Long clienteId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM pedido WHERE seq_cliente = ? ORDER BY seq_pedido");

            List<Pedido> list = query.getResultList();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Pedido> getPedidosByFarmaciaId(Long farmaciaId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM pedido WHERE cadastro_prefeitura = ? ORDER BY nome");

            List<Pedido> list = query.getResultList();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Pedido> getPedidosByClienteIdAndStatus(Long clienteId, char status) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM pedido WHERE seq_cliente = ? AND status = ? ORDER BY nome");

            List<Pedido> list = query.getResultList();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Pedido> getPedidosByFarmaciaIdAndStatus(Long farmaciaId, char status) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM pedido WHERE cadastro_prefeitura = ? AND status = ? ORDER BY nome");

            List<Pedido> list = query.getResultList();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

}
