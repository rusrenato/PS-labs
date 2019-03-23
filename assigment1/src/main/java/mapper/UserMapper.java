package mapper;


import dao.UserDAO;
import domain.User;
import domain.Zbor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserMapper {

    private UserDAO userDAO;

    public UserMapper() throws SQLException {
        userDAO = new UserDAO();
    }

    public void insert(User user) throws SQLException{
        userDAO.insert(user.getNume(),user.getPrenume(),user.getUsername(),user.getParola(),user.getRol(),user.getMoney());
    }

    public void delete(int id) throws SQLException{
        userDAO.delete(id);
    }

    public List<User> findAll() throws SQLException{
        List<User> userList = new LinkedList<User>();
        ResultSet resultSet = userDAO.findAll();
        while (resultSet.next()){
            User user = new User();

            user.setId(resultSet.getInt("id"));
           user.setNume(resultSet.getString("nume"));
           user.setPrenume(resultSet.getString("prenume"));
           user.setUsername(resultSet.getString("username"));
           user.setParola(resultSet.getString("parola"));
           user.setRol(resultSet.getString("rol"));
           user.setMoney(resultSet.getInt("money"));

            userList.add(user);
        }
        return userList;
    }

    public User findById(int id) throws SQLException{
        User user = new User();
        ResultSet resultSet = userDAO.findByID(id);
        while (resultSet.next()){

            user.setId(resultSet.getInt("id"));
            user.setNume(resultSet.getString("nume"));
            user.setPrenume(resultSet.getString("prenume"));
            user.setUsername(resultSet.getString("username"));
            user.setParola(resultSet.getString("parola"));
            user.setRol(resultSet.getString("rol"));
            user.setMoney(resultSet.getInt("money"));
        }
        return user;
    }

    public User findByName(String username) throws SQLException{
        User user = new User();
        ResultSet resultSet = userDAO.findByName(username);
        while (resultSet.next()){

            user.setId(resultSet.getInt("id"));
            user.setNume(resultSet.getString("nume"));
            user.setPrenume(resultSet.getString("prenume"));
            user.setUsername(resultSet.getString("username"));
            user.setParola(resultSet.getString("parola"));
            user.setRol(resultSet.getString("rol"));
            user.setMoney(resultSet.getInt("money"));
        }
        return user;
    }

    public void modifyMoney(int id, int money) throws SQLException{
        userDAO.modifyMoney(money,id);
    }

}
