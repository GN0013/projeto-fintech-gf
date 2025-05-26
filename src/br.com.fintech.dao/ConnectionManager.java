package br.com.fintech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "RM560602"; // Substitua pelo seu RM
    private static final String PASSWORD = "240300"; // Substitua pela sua senha FIAP

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(true);
            return conn;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver Oracle não encontrado!", e);
        }
    }
}