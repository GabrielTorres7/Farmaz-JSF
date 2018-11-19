/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.ProdutoDAO;
import br.cefetmg.farmaz.model.dominio.Produto;
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
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Gabriel
 */
public class ProdutoDAOImpl implements ProdutoDAO{
    
    private static ProdutoDAOImpl produtoDAO = null;
    
    private ProdutoDAOImpl(){}
    
    public static ProdutoDAOImpl getInstance() {
        if (produtoDAO == null) {
            produtoDAO = new ProdutoDAOImpl();
        }
        return produtoDAO;
    }
    
    @Override
    public Long insert(Produto produto) throws PersistenciaException {
        if (produto == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long produtoId = null;

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.persist(produto);
            manager.getTransaction().commit();

            produtoId = produto.getId();

            manager.close();
            factory.close();

            return produtoId;

        } catch (Exception ex) {
            Logger.getLogger(EnderecoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
    }

    @Override
    public boolean update(Produto produto) throws PersistenciaException {
         try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(produto);
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
    public boolean remove(Long produtoId) throws PersistenciaException {
         try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Produto endereco = manager.find(Produto.class, produtoId);

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
    public Produto getProdutoById(Long produtoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Produto produto = manager.find(Produto.class, produtoId);

            return produto;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Produto> listAll() throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM Produto");

            List<Produto> listAll = query.getResultList();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Produto getProdutoByNome(String nomeProduto) throws PersistenciaException {
       try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Produto produto = manager.find(Produto.class, nomeProduto);

            return produto;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }  
    }
}
