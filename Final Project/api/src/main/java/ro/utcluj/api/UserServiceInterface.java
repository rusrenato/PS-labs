package ro.utcluj.api;


import ro.utcluj.dto.Role;
import ro.utcluj.dto.UserDTO;

import java.util.List;

public interface UserServiceInterface {

    UserDTO getUserDTO (Integer id);

    boolean validateUsername(String username);

    boolean validateUser(String username, String password);

    Role getRoleOfUser(Integer id);

    int getIdByUsername(String username);

    List<UserDTO> findAll();

    void insert(String username, String password,String rol, Integer salary);

    void insertRegister(String name, String username, String password);

    void giveBonus(Integer id, Integer bonus);

    void delete(Integer id);

    void update(Integer id, String username, String password, String rol, Integer salary);

    void updateMoney(Integer id, Integer bonuses);

    void updateNumberOfWashedCars(Integer id, Integer numberOfWashedCars);

    void updateNumberOfBreaks(Integer id, Integer numberOfBreaks);

    void updateRank(String name, Double rating);

}
