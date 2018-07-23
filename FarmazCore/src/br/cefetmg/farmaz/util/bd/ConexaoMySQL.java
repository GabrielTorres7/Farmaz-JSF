/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.farmaz.util.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class ConexaoMySQL implements ConnectionFactory {

    private final static String dbDriver = "com.mysql.jdbc.Driver";
    private final static String dbURL = "jdbc:mysql://localhost:3306/farmaz";
    private final static String usuario = "root";
    private final static String senha = "";

    public ConexaoMySQL() {
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(dbDriver);
            return DriverManager.getConnection(dbURL, usuario, senha);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

}
