/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.DisponibilidadeDAO;
import br.cefetmg.farmaz.model.dominio.Disponibilidade;
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

public class DisponibilidadeDAOImpl implements DisponibilidadeDAO {

    private static DisponibilidadeDAOImpl disponibilidadeDAO = null;

    private DisponibilidadeDAOImpl() {
    }

    public static DisponibilidadeDAOImpl getInstance() {
        if (disponibilidadeDAO == null) {
            disponibilidadeDAO = new DisponibilidadeDAOImpl();
        }
        return disponibilidadeDAO;
    }

    @Override
    public Long insert(Disponibilidade disponibilidade) throws PersistenciaException {
        if (disponibilidade == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long DisponibilidadeId = null;

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            manager.getTransaction().begin();
            manager.persist(disponibilidade);
            manager.getTransaction().commit();
            DisponibilidadeId = disponibilidade.getId();
            manager.close();
            factory.close();
            return DisponibilidadeId;

        } catch (Exception ex) {
            Logger.getLogger(DisponibilidadeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

    }

    @Override
    public boolean update(Disponibilidade disponibilidade) throws PersistenciaException {
        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            manager.getTransaction().begin();
            manager.refresh(disponibilidade);
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
    public boolean remove(Long disponibilidadeId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM disponibilidade WHERE seq_disponibilidade = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, disponibilidadeId);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Disponibilidade getDisponibilidadeById(Long disponibilidadeId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();

            Disponibilidade disponibilidade = manager.find(Disponibilidade.class, disponibilidadeId);

            return disponibilidade;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Disponibilidade> getDisponibilidadeByProdutoId(Long produtoId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM disponibilidade WHERE seq_produto = ? ORDER BY seq_produto");

            List<Disponibilidade> list = query.getResultList();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Disponibilidade> getDisponibilidadeByFarmaciaId(String farmaciaId) throws PersistenciaException {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("FarmazPU");
            EntityManager manager = factory.createEntityManager();
            Query query = manager.createNativeQuery("SELECT * FROM disponibilidade WHERE cadastro_prefeitura = ? ORDER BY cadastro_prefeitura");

            List<Disponibilidade> list = query.getResultList();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
}
