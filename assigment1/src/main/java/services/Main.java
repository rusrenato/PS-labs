package services;

import domain.Bilet;
import domain.User;
import domain.Zbor;
import mapper.BiletMapper;
import mapper.UserMapper;
import mapper.ZborMapper;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Zbor zbor1 = new Zbor(1,"Cluj","Bucuresti","Tarom");
        ZborMapper zborMapper = new ZborMapper();
        Services services = new Services();
        //zborMapper.insert(zbor1);
        //zborMapper.delete(2);
//        for(Zbor zbor : zborMapper.findAll()){
//            System.out.println(zbor.toString());
//        }
        //System.out.println(zborMapper.findById(1).toString());
        User user1 = new User("Rus","Renato","YubelYuki","parola","Admin",9999);
        UserMapper userMapper = new UserMapper();
       // userMapper.insert(user1);
       //userMapper.delete(1);
        for(User user : userMapper.findAll()){
            System.out.println(user.toString());
        }
       // userMapper.modifyMoney(2,300);
        //System.out.println(userMapper.findById(3).toString());
        Bilet bilet1 = new Bilet(3,3,50,2);
        BiletMapper biletMapper = new BiletMapper();
       // biletMapper.insert(bilet1);
//        for(Bilet bilet : biletMapper.findAll()){
//            System.out.println(bilet.toString());
//        }
        services.buyTicket(user1,bilet1);
        services.getList();

    }
}
