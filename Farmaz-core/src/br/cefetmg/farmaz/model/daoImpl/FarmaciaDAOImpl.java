/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.FarmaciaDAO;
import br.cefetmg.farmaz.model.dominio.Farmacia;
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
public class FarmaciaDAOImpl implements FarmaciaDAO {

    private static FarmaciaDAOImpl farmaciaDAO = null;

    private FarmaciaDAOImpl() {
    }

    public static FarmaciaDAOImpl getInstance() {
        if (farmaciaDAO == null) {
            farmaciaDAO = new FarmaciaDAOImpl();
        }
        return farmaciaDAO;

    }

    @Override
    public Long insert(Farmacia farmacia) throws PersistenciaException {

        if (farmacia == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long farmaciaId = null;

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmaz-corePU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(farmacia);
            manager.getTransaction().commit();

            farmaciaId = Long.parseLong((farmacia.getCadastroPrefeitura()));

            manager.close();
            factory.close();

            return farmaciaId;

        } catch (Exception ex) {
            Logger.getLogger(FarmaciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

    }

    @Override
    public boolean update(Farmacia farmacia) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmaz-corePU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(farmacia);
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
    public boolean remove(String farmaciaId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmaz-corePU");
            EntityManager manager = factory.createEntityManager();

            Farmacia farmacia = manager.find(Farmacia.class, farmaciaId);

            manager.getTransaction().begin();
            manager.remove(farmacia);
            manager.getTransaction().commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Farmacia getFarmaciaById(String farmaciaId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmaz-corePU");
            EntityManager manager = factory.createEntityManager();

            Farmacia farmacia = manager.find(Farmacia.class, farmaciaId);

            return farmacia;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Farmacia getFarmaciaByEmail(String email) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmaz-corePU");
            EntityManager manager = factory.createEntityManager();

            Farmacia farmacia = manager.find(Farmacia.class, email);

            return farmacia;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Farmacia getFarmaciaByEmailSenha(String email, String senha) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmaz-corePU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM Farmacia WHERE email = '"+ email +"' AND senha = '"+ senha);

            Farmacia farmacia = new Farmacia();
            farmacia = (Farmacia) query.getSingleResult();
            
            return farmacia;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Farmacia> listAll() throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("Farmaz-corePU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM Farmacia");

            List<Farmacia> listAll = query.getResultList();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

}
