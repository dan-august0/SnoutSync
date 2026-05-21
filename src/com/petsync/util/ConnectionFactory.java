package com.petsync.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/petsync"
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=America/Sao_Paulo";

    private static final String USER = System.getProperty("petsync.db.user", "root");
    private static final String PASSWORD = System.getProperty("petsync.db.password", "");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver MySQL nao encontrado.", ex);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
