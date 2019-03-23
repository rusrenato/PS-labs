package dao;


import java.sql.*;


public class ZborDAO {

    private Connection connection;

    public ZborDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketing","root", "ronaldica1");
    }

    public void insert(String departure, String arrival, String company) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO zbor (departure, arrival, company) VALUES (?, ?, ?);");
        statement.setString(1,departure);
        statement.setString(2,arrival);
        statement.setString(3,company);

        statement.executeUpdate();
        statement.close();
    }

    public void delete(int id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("DELETE FROM zbor WHERE id = ?");
        statement.setInt(1,id);

        statement.executeUpdate();
        statement.close();
    }

    public ResultSet findByID(int id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM zbor WHERE id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();


       return resultSet;
    }

    public ResultSet findAll() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM zbor");
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }



}

