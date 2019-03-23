package dao;

import java.sql.*;

public class BiletDAO {
    private Connection connection;

    public BiletDAO() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketing","root", "ronaldica1");
    }

    public void insert(int id_zbor, int id_user, int price, int seat_nr) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO bilet (id_zbor, id_user, price, seat_nr) VALUES (?, ?, ?, ?);");
       statement.setInt(1,id_zbor);
       statement.setInt(2,id_user);
       statement.setInt(3,price);
       statement.setInt(4,seat_nr);

        statement.executeUpdate();
        statement.close();
    }

    public void delete(int id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("DELETE FROM bilet WHERE id = ?");
        statement.setInt(1,id);

        statement.executeUpdate();
        statement.close();
    }

    public ResultSet findByID(int id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bilet WHERE id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();


        return resultSet;
    }

    public ResultSet findAll() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bilet");
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }


}
