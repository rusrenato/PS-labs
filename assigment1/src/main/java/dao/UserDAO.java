package dao;

import java.sql.*;

public class UserDAO {

    private Connection connection;

    public UserDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketing","root", "ronaldica1");
    }

    public void insert(String nume, String prenume, String username, String parola, String rol, int money) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("INSERT INTO userr (nume, prenume, username, parola, rol, money) VALUES (?, ?, ?, ?, ?, ?);");
        statement.setString(1,nume);
        statement.setString(2,prenume);
        statement.setString(3,username);
        statement.setString(4,parola);
        statement.setString(5,rol);
        statement.setInt(6,money);

        statement.executeUpdate();
        statement.close();
    }

    public void delete(int id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("DELETE FROM userr WHERE id = ?");
        statement.setInt(1,id);

        statement.executeUpdate();
        statement.close();
    }

    public ResultSet findByID(int id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM userr WHERE id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();


        return resultSet;
    }

    public ResultSet findByName(String username) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM userr WHERE username = ?");
        preparedStatement.setString(1,username);

        return preparedStatement.executeQuery();
    }

    public ResultSet findAll() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM userr");
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public void modifyMoney(int money, int id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE userr set money = ? where id = ?");
        preparedStatement.setInt(1,money);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }





}
