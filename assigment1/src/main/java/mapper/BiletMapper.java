package mapper;

import dao.BiletDAO;
import domain.Bilet;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class BiletMapper {

    private BiletDAO biletDAO;

    public BiletMapper() throws SQLException {
        biletDAO = new BiletDAO();
    }

    public void insert(Bilet bilet) throws SQLException{
        biletDAO.insert(bilet.getId_zbor(),bilet.getId_user(),bilet.getPrice(),bilet.getSeat_nr());
    }

    public void delete(int id) throws SQLException{
        biletDAO.delete(id);
    }

    public List<Bilet> findAll() throws SQLException{
        List<Bilet> biletList = new LinkedList<Bilet>();
        ResultSet resultSet = biletDAO.findAll();
        while (resultSet.next()){
            Bilet bilet = new Bilet();

            bilet.setId(resultSet.getInt("id"));
            bilet.setId_zbor(resultSet.getInt("id_zbor"));
            bilet.setId_user(resultSet.getInt("id_user"));
            bilet.setPrice(resultSet.getInt("price"));
            bilet.setSeat_nr(resultSet.getInt("seat_nr"));
            biletList.add(bilet);
        }
        return biletList;
    }

    public Bilet findById(int id) throws SQLException{
        Bilet bilet = new Bilet();
        ResultSet resultSet = biletDAO.findAll();
        while (resultSet.next()){

            bilet.setId(resultSet.getInt("id"));
            bilet.setId_zbor(resultSet.getInt("id_zbor"));
            bilet.setId_user(resultSet.getInt("id_user"));
            bilet.setPrice(resultSet.getInt("price"));
            bilet.setSeat_nr(resultSet.getInt("seat_nr"));
        }
        return bilet;
    }
}
