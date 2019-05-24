package ro.utcluj.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcluj.enitity.User;
import ro.utcluj.enitity.Wash;

import java.util.List;

public interface WashRepository extends JpaRepository<Wash,Integer> {
    Wash getWashById(int id);
    List<Wash> findAllByCar_Client(User user);
    List<Wash> findAllByWorkerId(int id);
}
