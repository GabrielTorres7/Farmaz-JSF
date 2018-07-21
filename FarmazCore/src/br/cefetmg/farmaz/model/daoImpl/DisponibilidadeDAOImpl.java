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

/**
 *
 * @author PEDRO HENRIQUE FODÃO
 */

public class DisponibilidadeDAOImpl implements DisponibilidadeDAO{
    
    private static DisponibilidadeDAOImpl disponibilidadeDAO = null;
    
    private DisponibilidadeDAOImpl(){}
    
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
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "INSERT INTO Disponibilidade (Produto-serial, Cadastro-prefeitura, Estoque, Preço, avaliacao) "
                    + "    VALUES (?, ?, ?, ?, ?) ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, disponibilidade.getProdutoSeq());
            pstmt.setLong(2, disponibilidade.getFarmaciaCadastro());
            pstmt.setString(3, disponibilidade.getEstoque());
            pstmt.setDouble(4, disponibilidade.getPreco());
            pstmt.setString(5, disponibilidade.getAvaliacao());
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM disponibilidade");

            if (rs.next()) {
                DisponibilidadeId = rs.getLong(1);
                disponibilidade.setId(DisponibilidadeId);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DisponibilidadeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

        return DisponibilidadeId;              
    }

    @Override
    public boolean update(Disponibilidade disponibilidade) throws PersistenciaException {
        try {

            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "UPDATE disponibilidade "
                    + " SET Produto-serial = ?, "
                    + "     Cadastro-prefeitura = ? "
                    + "     Estoque = ? "
                    + "     Preço = ? "
                    + "     avaliacao = ? "
                    + " WHERE disponibilidade_serial = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, disponibilidade.getProdutoSeq());
            pstmt.setLong(2, disponibilidade.getFarmaciaCadastro());
            pstmt.setString(3, disponibilidade.getEstoque());
            pstmt.setDouble(4, disponibilidade.getPreco());
            pstmt.setString(5, disponibilidade.getAvaliacao());
            pstmt.setLong(6, disponibilidade.getId());
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
    public boolean remove(Long DisponibilidadeId) throws PersistenciaException {
         try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM disponibilidade WHERE disponibilidade_serial = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, DisponibilidadeId);
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
    public Disponibilidade getDisponibilidadeById(Long DisponibilidadeId) throws PersistenciaException {
         try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM disponibilidade WHERE disponibilidade_serial = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, DisponibilidadeId);
            ResultSet rs = pstmt.executeQuery();

            Disponibilidade disponibilidade = null;
            if (rs.next()) {
                disponibilidade = new Disponibilidade();
                disponibilidade.setId(DisponibilidadeId);
                disponibilidade.setProdutoSeq(rs.getLong("Produto-serial"));
                disponibilidade.setFarmaciaCadastro(rs.getLong("Cadastro-prefeitura"));
                disponibilidade.setEstoque(rs.getString("Estoque"));
                disponibilidade.setPreco(rs.getDouble("Preço"));
                disponibilidade.setAvaliacao(rs.getString("avaliacao"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return disponibilidade;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Disponibilidade> getDisponibilidadeByProdutoId(Long produtoId) throws PersistenciaException {
         try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM disponibilidade WHERE Produto-serial = ?";            

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, produtoId);
            ResultSet rs = pstmt.executeQuery();            
            
            ArrayList<Disponibilidade> listAll = null;
            Disponibilidade disponibilidade = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    disponibilidade = new Disponibilidade();
                    disponibilidade.setId(rs.getLong("disponibilidade_serial"));
                    disponibilidade.setProdutoSeq(rs.getLong("Produto-serial"));
                    disponibilidade.setFarmaciaCadastro(rs.getLong("Cadastro-prefeitura"));
                    disponibilidade.setEstoque(rs.getString("Estoque"));
                    disponibilidade.setPreco(rs.getDouble("Preço"));
                    disponibilidade.setAvaliacao(rs.getString("avaliacao"));
                    listAll.add(disponibilidade);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Disponibilidade> getDisponibilidadeByFarmaciaId(Long farmaciaId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM disponibilidade WHERE Cadastro-prefeitura = ?";            

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, farmaciaId);
            ResultSet rs = pstmt.executeQuery();            
            
            ArrayList<Disponibilidade> listAll = null;
            Disponibilidade disponibilidade = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    disponibilidade = new Disponibilidade();
                    disponibilidade.setId(rs.getLong("disponibilidade_serial"));
                    disponibilidade.setProdutoSeq(rs.getLong("Produto-serial"));
                    disponibilidade.setFarmaciaCadastro(rs.getLong("Cadastro-prefeitura"));
                    disponibilidade.setEstoque(rs.getString("Estoque"));
                    disponibilidade.setPreco(rs.getDouble("Preço"));
                    disponibilidade.setAvaliacao(rs.getString("avaliacao"));
                    listAll.add(disponibilidade);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
}
  
