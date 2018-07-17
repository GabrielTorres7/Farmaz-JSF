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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class ClienteDAOImpl implements ClienteDAO{
    
    private static ClienteDAOImpl clienteDAO = null;
    
    private ClienteDAOImpl(){}
    
    public static ClienteDAOImpl getInstance() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAOImpl();
        }
        return clienteDAO;
    }
    
    @Override
    public Long insert(Cliente cliente) throws PersistenciaException {
        if (cliente == null){
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }
        
        Long clienteId = null;
        
        try {
            Connection connection = ManterConexao.getInstance().getConnection();
            
            String sql = "INSERT INTO cliente (nom_cliente, nom_email, txt_password, txt_documento, num_telefone) " +
                         "    VALUES (?, ?, md5(?), ?, ?) ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getSenha());
            pstmt.setString(4, cliente.getDocumentoIdentificacao());
            pstmt.setString(5, cliente.getNumeroTelefone());
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM usuario");

            if (rs.next()) {
                clienteId = rs.getLong(1);
                cliente.setId(clienteId);
            }

            rs.close();
            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }
        
        return clienteId;
    }

    @Override
    public boolean update(Cliente cliente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Long clienteId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente getClienteById(Long clienteId) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente getClienteByEmail(String email) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> listAll() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
