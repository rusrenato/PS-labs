package ro.utcluj.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.dto.WashDTO;
import ro.utcluj.enitity.Wash;

@Component
public class WashMapper {


    public WashDTO map(Wash wash) {
        CarMapper carMapper = new CarMapper();
        UserMapper userMapper = new UserMapper();
        WashDTO dto = new WashDTO();

        dto.setHour(wash.getHour());
        dto.setId(wash.getId());
        dto.setPrice(wash.getPrice());
        dto.setType(wash.getType());
        dto.setCar(carMapper.map(wash.getCar()));
        dto.setWorker(userMapper.map(wash.getWorker()));

        return dto;
    }
}
