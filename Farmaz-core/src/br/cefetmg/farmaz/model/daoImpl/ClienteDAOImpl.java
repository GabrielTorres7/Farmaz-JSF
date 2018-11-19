/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.ClienteDAO;
import br.cefetmg.farmaz.model.dominio.Cliente;
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
public class ClienteDAOImpl implements ClienteDAO {

    private static ClienteDAOImpl clienteDAO = null;

    private ClienteDAOImpl() {
    }

    public static ClienteDAOImpl getInstance() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAOImpl();
        }
        return clienteDAO;
    }

    @Override
    public Long insert(Cliente cliente) throws PersistenciaException {
        if (cliente == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long clienteId = null;

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(cliente);
            manager.getTransaction().commit();

            clienteId = cliente.getId();

            manager.close();
            factory.close();

            return clienteId;

        } catch (Exception ex) {
            Logger.getLogger(FarmaciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public boolean update(Cliente cliente) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(cliente);
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
    public boolean remove(Long clienteId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Cliente cliente = manager.find(Cliente.class, clienteId);

            manager.getTransaction().begin();
            manager.remove(cliente);
            manager.getTransaction().commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Cliente getClienteById(Long clienteId) throws PersistenciaException {
         try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Cliente cliente = manager.find(Cliente.class, clienteId);

            return cliente;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Cliente getClienteByEmail(String email) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Cliente cliente = manager.find(Cliente.class, email);

            return cliente;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public Cliente getClienteByEmailSenha(String email, String senha) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM Cliente WHERE email = '"+ email +"' AND senha = '"+ senha);

            Cliente cliente = new Cliente();
            cliente = (Cliente) query.getSingleResult();
            
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Cliente> listAll() throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM Cliente");

            List<Cliente> listAll = query.getResultList();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

}
