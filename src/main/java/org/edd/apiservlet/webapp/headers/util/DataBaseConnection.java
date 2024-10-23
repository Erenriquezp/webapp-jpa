package org.edd.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/ecommerce";
    private static String username = "root";
    private static String password = "patrones123";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
