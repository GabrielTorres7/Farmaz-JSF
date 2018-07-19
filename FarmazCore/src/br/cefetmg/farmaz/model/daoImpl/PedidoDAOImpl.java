/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.model.daoImpl;

import br.cefetmg.farmaz.model.dao.PedidoDAO;
import br.cefetmg.farmaz.model.dominio.Pedido;
import br.cefetmg.farmaz.model.exception.PersistenciaException;
import br.cefetmg.farmaz.util.bd.ManterConexao;
import java.sql.Connection;
import java.sql.Date;
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
public class PedidoDAOImpl implements PedidoDAO {

    private static PedidoDAOImpl pedidoDAO = null;

    private PedidoDAOImpl() {
    }

    public static PedidoDAOImpl getInstance() {
        if (pedidoDAO == null) {
            pedidoDAO = new PedidoDAOImpl();
        }
        return pedidoDAO;
    }

    @Override
    public Long insert(Pedido pedido) throws PersistenciaException {
        if (pedido == null) {
            throw new PersistenciaException("Domínio não pode ser nulo.");
        }

        Long pedidoId = null;

        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "INSERT INTO pedido (cliente_serial, cadastro_prefeitura, data, status, token_pagseguro) "
                    + "    VALUES (?, ?, ?, ?, ?) ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, pedido.getClienteId());
            pstmt.setLong(2, pedido.getFarmaciaId());
            pstmt.setDate(3, (Date) pedido.getDataHora());
            pstmt.setString(4, String.valueOf(pedido.getIdtStatus()));
            pstmt.setDouble(5, pedido.getPagamento());

            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM pedido");

            if (rs.next()) {
                pedidoId = rs.getLong(1);
                pedido.setPedidoId(pedidoId);
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException(ex);
        }

        return pedidoId;
    }

    @Override
    public boolean update(Pedido pedido) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "UPDATE pedido "
                    + " SET cliente_serial = ?, "
                    + "     cadastro_prefeitura = ? "
                    + "     data = ? "
                    + "     status = ? "
                    + "     token_pagseguro = ? "
                    + " WHERE pedido_serial = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, pedido.getClienteId());
            pstmt.setLong(2, pedido.getFarmaciaId());
            pstmt.setDate(3, (Date) pedido.getDataHora());
            pstmt.setString(4, String.valueOf(pedido.getIdtStatus()));
            pstmt.setDouble(5, pedido.getPagamento());
            pstmt.setLong(6, pedido.getPedidoId());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }

        return true;
    }

    @Override
    public boolean remove(Long pedidoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "DELETE FROM pedido WHERE pedido_serial = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, pedidoId);
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
    public Pedido getPedidoById(Long pedidoId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM pedido WHERE pedido_serial = ? ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, pedidoId);
            ResultSet rs = pstmt.executeQuery();

            Pedido pedido = null;
            if (rs.next()) {
                pedido = new Pedido();
                pedido.setPedidoId(pedidoId);
                pedido.setClienteId(rs.getLong("cliente_serial"));
                pedido.setFarmaciaId(rs.getLong("cadastro_prefeitura"));
                pedido.setDataHora(rs.getDate("data"));
                pedido.setIdtStatus(rs.getString("status").charAt(0));
                pedido.setPagamento(rs.getDouble("token_pagseguro"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return pedido;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Pedido> getPedidosByClienteId(Long clienteId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM pedido WHERE cliente_serial = ? ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, clienteId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Pedido> listPedidos = null;
            Pedido pedido = null;

            if (rs.next()) {
                listPedidos = new ArrayList<>();
                do {
                    pedido = new Pedido();
                    pedido.setPedidoId(rs.getLong("pedido_serial"));
                    pedido.setClienteId(rs.getLong("cliente_serial"));
                    pedido.setFarmaciaId(rs.getLong("cadastro_prefeitura"));
                    pedido.setDataHora(rs.getDate("data"));
                    pedido.setIdtStatus(rs.getString("status").charAt(0));
                    pedido.setPagamento(rs.getDouble("token_pagseguro"));
                    listPedidos.add(pedido);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listPedidos;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Pedido> getPedidosByFarmaciaId(Long farmaciaId) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM pedido WHERE cadastro_prefeitura = ? ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, farmaciaId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Pedido> listPedidos = null;
            Pedido pedido = null;

            if (rs.next()) {
                listPedidos = new ArrayList<>();
                do {
                    pedido = new Pedido();
                    pedido.setPedidoId(rs.getLong("pedido_serial"));
                    pedido.setClienteId(rs.getLong("cliente_serial"));
                    pedido.setFarmaciaId(rs.getLong("cadastro_prefeitura"));
                    pedido.setDataHora(rs.getDate("data"));
                    pedido.setIdtStatus(rs.getString("status").charAt(0));
                    pedido.setPagamento(rs.getDouble("token_pagseguro"));
                    listPedidos.add(pedido);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listPedidos;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Pedido> getPedidosByClienteIdAndStatus(Long clienteId, char status) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM pedido WHERE cliente_serial = ? AND status = ? ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, clienteId);
            pstmt.setLong(2, status);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Pedido> listPedidos = null;
            Pedido pedido = null;

            if (rs.next()) {
                listPedidos = new ArrayList<>();
                do {
                    pedido = new Pedido();
                    pedido.setPedidoId(rs.getLong("pedido_serial"));
                    pedido.setClienteId(rs.getLong("cliente_serial"));
                    pedido.setFarmaciaId(rs.getLong("cadastro_prefeitura"));
                    pedido.setDataHora(rs.getDate("data"));
                    pedido.setIdtStatus(rs.getString("status").charAt(0));
                    pedido.setPagamento(rs.getDouble("token_pagseguro"));
                    listPedidos.add(pedido);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listPedidos;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    @Override
    public List<Pedido> getPedidosByFarmaciaIdAndStatus(Long farmaciaId, char status) throws PersistenciaException {
        try {
            Connection connection = ManterConexao.getInstance().getConnection();

            String sql = "SELECT * FROM pedido WHERE cadastro_prefeitura = ? AND status = ? ORDER BY nome";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, farmaciaId);
            pstmt.setLong(2, status);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Pedido> listPedidos = null;
            Pedido pedido = null;

            if (rs.next()) {
                listPedidos = new ArrayList<>();
                do {
                    pedido = new Pedido();
                    pedido.setPedidoId(rs.getLong("pedido_serial"));
                    pedido.setClienteId(rs.getLong("cliente_serial"));
                    pedido.setFarmaciaId(rs.getLong("cadastro_prefeitura"));
                    pedido.setDataHora(rs.getDate("data"));
                    pedido.setIdtStatus(rs.getString("status").charAt(0));
                    pedido.setPagamento(rs.getDouble("token_pagseguro"));
                    listPedidos.add(pedido);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listPedidos;

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

}
