package ro.utcluj.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.dto.CarDTO;
import ro.utcluj.enitity.Car;

@Component
public class CarMapper {
    public CarMapper() {
    }

    public CarDTO map(Car car){
        UserMapper userMapper = new UserMapper();

        CarDTO dto = new CarDTO();

        dto.setColor(car.getColor());
        dto.setId(car.getId());
        dto.setNrInmatriculare(car.getNrInmatriculare());
        dto.setClient(userMapper.map(car.getClient()));

        return dto;
    }


}
