package ro.utcluj.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.dto.Role;
import ro.utcluj.dto.UserDTO;
import ro.utcluj.enitity.User;

@Component
public class UserMapper {

    public UserMapper() {
    }

    public UserDTO map(User user){

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setNumberOfWashedCars(user.getNumberOfWashedCars());
        dto.setPassword(user.getPassword());
        dto.setRol(Role.valueOf(user.getRol().name()));
        dto.setBonuses(user.getBonuses());
        dto.setSalary(user.getSalary());
        dto.setNumberOfWashedCars(user.getNumberOfWashedCars());
        dto.setWallet(user.getWallet());

        return dto;
    }
}
