/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.CidadeDAO;
import br.cefetmg.farmaz.model.dominio.Cidade;
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
public class CidadeDAOImpl implements CidadeDAO{
    
    private static CidadeDAOImpl cidadeDAO = null;

    private CidadeDAOImpl() {
    }

    public static CidadeDAOImpl getInstance() {
        if (cidadeDAO == null) {
            cidadeDAO = new CidadeDAOImpl();
        }
        return cidadeDAO;
    }
    
    @Override
    public Long insert(Cidade cidade) throws PersistenciaException {
        if (cidade == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long cidadeId = null;

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("cidade");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(cidade);
            manager.getTransaction().commit();

            cidadeId = cidade.getCidadeId();

            manager.close();
            factory.close();

            return cidadeId;

        } catch (Exception ex) {
            Logger.getLogger(EnderecoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public boolean update(Cidade cidade) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("cidade");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(cidade);
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
    public boolean remove(Long cidadeId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("cidade");
            EntityManager manager = factory.createEntityManager();

            Cidade cidade = manager.find(Cidade.class, cidadeId);

            manager.getTransaction().begin();
            manager.remove(cidade);
            manager.getTransaction().commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Cidade getCidadeById(Long cidadeId) throws PersistenciaException {
         try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("cidade");
            EntityManager manager = factory.createEntityManager();

            Cidade cidade = manager.find(Cidade.class, cidadeId);

            return cidade;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public Cidade getCidadeByNome(String nome) throws PersistenciaException {
         try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("cidade");
            EntityManager manager = factory.createEntityManager();

            Query query = manager.createNativeQuery("SELECT * FROM cidade WHERE nome = " + nome);
            
            Cidade cidade = (Cidade) query.getSingleResult();
            manager.close();
            factory.close();
            return cidade;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
    @Override
    public List<Cidade> listAll() throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("cidade");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM cidade");

            List<Cidade> listAll = query.getResultList();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
    
}
