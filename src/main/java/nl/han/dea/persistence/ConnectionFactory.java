package nl.han.dea.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String CONNECTION_URL
            = "jdbc:mysql://localhost:3306/spotitube?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ConnectionFactory() {

    }

    public Connection getConnecion() {
        try {
            return DriverManager.getConnection(CONNECTION_URL,
                    DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}