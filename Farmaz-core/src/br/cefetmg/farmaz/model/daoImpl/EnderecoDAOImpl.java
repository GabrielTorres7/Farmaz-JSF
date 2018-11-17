/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.EnderecoDAO;
import br.cefetmg.farmaz.model.dominio.Endereco;
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
public class EnderecoDAOImpl implements EnderecoDAO {

    private static EnderecoDAOImpl enderecoDAO = null;

    private EnderecoDAOImpl() {
    }

    public static EnderecoDAOImpl getInstance() {
        if (enderecoDAO == null) {
            enderecoDAO = new EnderecoDAOImpl();
        }
        return enderecoDAO;
    }

    @Override
    public Long insert(Endereco endereco) throws PersistenciaException {
        if (endereco == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long enderecoId = null;

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(endereco);
            manager.getTransaction().commit();

            enderecoId = endereco.getEnderecoId();

            manager.close();
            factory.close();

            return enderecoId;

        } catch (Exception ex) {
            Logger.getLogger(EnderecoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

    }

    @Override
    public boolean update(Endereco endereco) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(endereco);
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
    public boolean remove(Long enderecoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Endereco endereco = manager.find(Endereco.class, enderecoId);

            manager.getTransaction().begin();
            manager.remove(endereco);
            manager.getTransaction().commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Endereco getEnderecoById(Long enderecoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Endereco endereco = manager.find(Endereco.class, enderecoId);

            return endereco;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Endereco> getEnderecosByClienteId(Long clienteId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM Endereco WHERE seq_cliente = '" + clienteId + "' ");

            List<Endereco> listAll = query.getResultList();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
}