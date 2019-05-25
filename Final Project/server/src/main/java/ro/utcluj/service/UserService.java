package ro.utcluj.service;


import com.fasterxml.jackson.databind.deser.Deserializers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.dto.UserDTO;
import ro.utcluj.enitity.Role;
import ro.utcluj.enitity.User;
import ro.utcluj.mapper.UserMapper;
import ro.utcluj.repository.UserRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component
@Transactional
public class UserService implements UserServiceInterface, Serializable {

    private UserRepository userRepository;
    private UserMapper mapper;
    private String message;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public boolean validateUsername(String username) {
        return (userRepository.getUserByUsername(username) != null);
    }

    public boolean validateUser(String username, String password) {
        return (userRepository.getUserByUsernameAndPassword(username, password) != null);
    }

    public ro.utcluj.dto.Role getRoleOfUser(Integer id) {
        return (ro.utcluj.dto.Role.valueOf(userRepository.getUserById(id).getRol().name()));
    }

    public int getIdByUsername(String username) {
        System.out.println(username);
        return (userRepository.getUserByUsername(username).getId());
    }

    public User getUserbyUsername(String username) {
        return (userRepository.getUserByUsername(username));
    }

    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> dtoList = new ArrayList<>();
        userList.forEach(user -> dtoList.add(mapper.map(user)));
        return dtoList;
    }

    @Override
    public void insert(String username, String password, String rol, Integer salary) {
        System.out.println(username + " " + password);
        User user = new User(username, username, password, Role.valueOf(rol), 0, salary, 0, 0, 0, 0, null);
        userRepository.save(user);
    }

    public void insertRegister(String name, String username, String password) {
        User user = new User(name, username, password, Role.valueOf("Client"), 0, 0, 0, 0, 0, 0, null);
        userRepository.save(user);
    }

    public void updateRank(String name, Double rating) {
        User user = userRepository.getUserByUsername(name);
        if (user != null) {
            if (user.getNumberOfWashedCars() == 0) {
                user.setRank(rating);
            } else if (user.getNumberOfWashedCars() == 1) {
                user.setRank((user.getRank() + rating) / user.getNumberOfWashedCars());
            } else {
                user.setRank((user.getRank() * (user.getNumberOfWashedCars() - 1) + rating) / (double) user.getNumberOfWashedCars());

            }
            userRepository.save(user);
        }
    }


    public User getUser(Integer id) {
        return userRepository.getUserById(id);
    }

    public User getUserFromDataBase(Integer id) {
        return userRepository.getUserById(id);
    }

    public void giveBonus(Integer id, Integer bonus) {
        User user = userRepository.getUserById(id);
        if (user != null) {
            user.setBonuses(bonus);
            userRepository.save(user);
        }
    }

    public void delete(Integer id) {
        User user = userRepository.getUserById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public void update(Integer id, String username, String password, String rol, Integer salary) {
        User user = userRepository.getUserById(id);
        if (user != null) {
            user.setPassword(password);
            user.setUsername(username);
            user.setRol(Role.valueOf(rol));
            user.setSalary(salary);
            userRepository.save(user);
        }
    }

    public void updateMoney(Integer id, Integer money) {
        User user = userRepository.getUserById(id);
        if (user != null) {
            user.setWallet(user.getWallet() + money);
            userRepository.save(user);
        }
    }

    public void updateNumberOfWashedCars(Integer id, Integer numberOfWashedCars) {
        User user = userRepository.getUserById(id);
        if (user != null) {
            user.setNumberOfWashedCars(numberOfWashedCars);
            userRepository.save(user);
        }
    }

    public UserDTO getUserDTO(Integer id){
        User user = userRepository.getUserById(id);
        return mapper.map(user);
    }

    public void updateNumberOfBreaks(Integer id, Integer numberOfBreaks) {
        User user = userRepository.getUserById(id);
        if (user != null) {
            user.setNumberOfBreaks(numberOfBreaks);
            userRepository.save(user);
        }
    }


}
