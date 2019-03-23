package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private final Connection connection;

    public Connector() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketing");
    }

    public Connection getConnection(){
        return connection;
    }

}
