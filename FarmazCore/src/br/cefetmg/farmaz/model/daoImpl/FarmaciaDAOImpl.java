/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.FarmaciaDAO;
import br.cefetmg.farmaz.model.dominio.Farmacia;
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
 * @author Gabriel
 */
public class FarmaciaDAOImpl implements FarmaciaDAO{
    
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
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "INSERT INTO farmacia (cadastro_prefeitura, codigo_cidade, codigo_uf, "
                    + " cnpj, nome, cep, bairro, rua, numero, email, senha) "
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, md5(?)) ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, farmacia.getCadastroPrefeitura());
            pstmt.setLong(2, farmacia.getCodCidade());
            pstmt.setLong(3, farmacia.getCodUf());
            pstmt.setString(4, farmacia.getCnpj());
            pstmt.setString(5, farmacia.getNome());
            pstmt.setInt(6, farmacia.getCep());
            pstmt.setString(7, farmacia.getBairro());
            pstmt.setString(8, farmacia.getRua());
            pstmt.setInt(9, farmacia.getNumero());
            pstmt.setString(10, farmacia.getEmail());
            pstmt.setString(11, farmacia.getSenha());
            
            ResultSet rs = pstmt.executeQuery();
            
            farmaciaId = farmacia.getCadastroPrefeitura();
            
            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FarmaciaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

        return farmaciaId;
    }

    @Override
    public boolean update(Farmacia farmacia) throws PersistenciaException {
        try {

            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "UPDATE farmacia "
                    + " SET codigo_cidade = ? "
                    + "     codigo_uf = ? "
                    + "     cnpj = ? "
                    + "     nome = ? "
                    + "     cep = ? "
                    + "     bairro = ? "
                    + "     rua = ? "
                    + "     numero = ? "
                    + "     email = ? "
                    + "     senha = ? "
                    + " WHERE cadastro_prefeitura = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, farmacia.getCodCidade());
            pstmt.setLong(2, farmacia.getCodUf());
            pstmt.setString(3, farmacia.getCnpj());
            pstmt.setString(4, farmacia.getNome());
            pstmt.setInt(5, farmacia.getCep());
            pstmt.setString(6, farmacia.getBairro());
            pstmt.setString(7, farmacia.getRua());
            pstmt.setInt(8, farmacia.getNumero());
            pstmt.setString(9, farmacia.getEmail());
            pstmt.setString(10, farmacia.getSenha());
            pstmt.setLong(11, farmacia.getCadastroPrefeitura());

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
    public boolean remove(Long farmaciaId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM farmacia WHERE cadastro_prefeitura = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, farmaciaId);
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
    public Farmacia getFarmaciaById(Long farmaciaId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM farmacia WHERE cadastro_prefeitura = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, farmaciaId);
            ResultSet rs = pstmt.executeQuery();

            Farmacia farmacia = null;
            if (rs.next()) {
                farmacia = new Farmacia();
                farmacia.setCadastroPrefeitura(farmaciaId);
                farmacia.setCodCidade(rs.getLong("codigo_cidade"));
                farmacia.setCodUf(rs.getLong("codigo_uf"));
                farmacia.setCnpj(rs.getString("cnpj"));
                farmacia.setNome(rs.getString("nome"));
                farmacia.setCep(rs.getInt("cep"));
                farmacia.setBairro(rs.getString("bairro"));
                farmacia.setRua(rs.getString("rua"));
                farmacia.setNumero(rs.getInt("numero"));
                farmacia.setEmail(rs.getString("email"));
                farmacia.setSenha(rs.getString("senha"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return farmacia;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Farmacia getFarmaciaByEmail(String email) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM farmacia WHERE email = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            Farmacia farmacia = null;
            if (rs.next()) {
                farmacia = new Farmacia();
                farmacia.setCadastroPrefeitura(rs.getLong("cadastro_prefeitura"));
                farmacia.setCodCidade(rs.getLong("codigo_cidade"));
                farmacia.setCodUf(rs.getLong("codigo_uf"));
                farmacia.setCnpj(rs.getString("cnpj"));
                farmacia.setNome(rs.getString("nome"));
                farmacia.setCep(rs.getInt("cep"));
                farmacia.setBairro(rs.getString("bairro"));
                farmacia.setRua(rs.getString("rua"));
                farmacia.setNumero(rs.getInt("numero"));
                farmacia.setEmail(rs.getString("email"));
                farmacia.setSenha(rs.getString("senha"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return farmacia;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public Farmacia getFarmaciaByEmailSenha(String email, String senha) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM farmacia WHERE email = ? AND senha = md5(?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            Farmacia farmacia = null;
            if (rs.next()) {
                farmacia = new Farmacia();
                farmacia.setCadastroPrefeitura(rs.getLong("cadastro_prefeitura"));
                farmacia.setCodCidade(rs.getLong("codigo_cidade"));
                farmacia.setCodUf(rs.getLong("codigo_uf"));
                farmacia.setCnpj(rs.getString("cnpj"));
                farmacia.setNome(rs.getString("nome"));
                farmacia.setCep(rs.getInt("cep"));
                farmacia.setBairro(rs.getString("bairro"));
                farmacia.setRua(rs.getString("rua"));
                farmacia.setNumero(rs.getInt("numero"));
                farmacia.setEmail(rs.getString("email"));
                farmacia.setSenha(rs.getString("senha"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return farmacia;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Farmacia> listAll() throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM farmacia ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Farmacia> listAll = null;
            Farmacia farmacia = null;

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                farmacia = new Farmacia();
                farmacia.setCadastroPrefeitura(rs.getLong("cadastro_prefeitura"));
                farmacia.setCodCidade(rs.getLong("codigo_cidade"));
                farmacia.setCodUf(rs.getLong("codigo_uf"));
                farmacia.setCnpj(rs.getString("cnpj"));
                farmacia.setNome(rs.getString("nome"));
                farmacia.setCep(rs.getInt("cep"));
                farmacia.setBairro(rs.getString("bairro"));
                farmacia.setRua(rs.getString("rua"));
                farmacia.setNumero(rs.getInt("numero"));
                farmacia.setEmail(rs.getString("email"));
                farmacia.setSenha(rs.getString("senha"));
                listAll.add(farmacia);
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
