package mapper;

import dao.ZborDAO;
import domain.Zbor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ZborMapper {

    private ZborDAO zborDAO;

   public ZborMapper() throws SQLException{
         zborDAO = new ZborDAO();
   }

    public void insert(Zbor zbor) throws SQLException{

       zborDAO.insert(zbor.getDeparture(),zbor.getArrival(),zbor.getCompany());
    }

    public void delete(int id) throws SQLException{
       zborDAO.delete(id);
    }

    public List<Zbor> findAll() throws SQLException{
       List<Zbor> zborList = new LinkedList<Zbor>();
       ResultSet resultSet = zborDAO.findAll();
       while (resultSet.next()){
           Zbor zbor = new Zbor();

           zbor.setId(resultSet.getInt("id"));
           zbor.setDeparture(resultSet.getString("departure"));
           zbor.setArrival(resultSet.getString("arrival"));
           zbor.setCompany(resultSet.getString("company"));

           zborList.add(zbor);
       }
       return zborList;
    }

    public Zbor findById(int id) throws SQLException{
       Zbor zbor = new Zbor();
       ResultSet resultSet = zborDAO.findByID(id);
       while (resultSet.next()){

           zbor.setId(resultSet.getInt("id"));
           zbor.setDeparture(resultSet.getString("departure"));
           zbor.setArrival(resultSet.getString("arrival"));
           zbor.setCompany(resultSet.getString("company"));
       }
       return zbor;
    }
}
