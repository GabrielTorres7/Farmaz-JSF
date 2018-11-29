/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.EstadoDAO;
import br.cefetmg.farmaz.model.dominio.Estado;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.util.bd.ManterConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class EstadoDAOImpl implements EstadoDAO{
    
    private static EstadoDAOImpl estadoDAO = null;

    private EstadoDAOImpl() {
    }

    public static EstadoDAOImpl getInstance() {
        if (estadoDAO == null) {
            estadoDAO = new EstadoDAOImpl();
        }
        return estadoDAO;
    }
    
    @Override
    public Long insert(Estado estado) throws PersistenciaException {
        if (estado == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long estadoId = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
        EntityManager manager = factory.createEntityManager();
        
        manager.getTransaction().begin();
        manager.persist(estado);
        manager.getTransaction().commit();
        
        estadoId = estado.getId();
        
        manager.close();
        factory.close();

        return estadoId;
    }

    @Override
    public boolean update(Estado estado) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(estado);
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
    public boolean remove(Long estadoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Estado estado = manager.find(Estado.class, estadoId);

            manager.getTransaction().begin();
            manager.remove(estado);
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
    public Estado getEstadoById(Long estadoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Estado estado = manager.find(Estado.class, estadoId);
            manager.close();
            factory.close();

            return estado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public Estado getEstadoBySigla(String sigla) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Query query = manager.createNativeQuery("SELECT * FROM uf WHERE nome =" + sigla);
            
            Estado estado = (Estado) query.getSingleResult();
            manager.close();
            factory.close();
            return estado;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public List<Estado> listAll() throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM uf");

            List<Estado> listAll = query.getResultList();
            manager.close();
            factory.close();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
}
