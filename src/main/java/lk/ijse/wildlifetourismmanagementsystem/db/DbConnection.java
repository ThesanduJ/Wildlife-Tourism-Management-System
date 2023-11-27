package lk.ijse.wildlifetourismmanagementsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private  Connection connection;
    private static DbConnection dbConnection;
    private final String URL = "jdbc:mysql://localhost:3306/wildlife_tourism"; //jdbc:mysql://localhost:3306/wildlife_tourism
    private final String username = "root";
    private final String password = "Password@1225";

    private DbConnection() throws SQLException {

        connection = DriverManager.getConnection(URL, username, password);
    }
    public static DbConnection getInstance() throws SQLException {
        if (dbConnection == null) {
            dbConnection=new DbConnection();
            return dbConnection;
        }else {
            return dbConnection;
        }
    }
    public Connection getConnection(){
        return connection;
    }
}
