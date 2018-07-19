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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Long farmaciaId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Farmacia getFarmaciaById(Long farmaciaId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Farmacia getFarmaciaByEmail(String email) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Farmacia getFarmaciaByEmailSenha(String email, String senha) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Farmacia> listAll() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
