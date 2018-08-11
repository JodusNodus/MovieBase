package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final String URL = "jdbc:mysql://localhost/moviebase?useSSL=false";
    private static final String UID = "root";
    private static final String PWD = "";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, UID, PWD);
        } catch(SQLException ex) {
            System.out.println("failed");
            throw ex;
        }
    }

}
