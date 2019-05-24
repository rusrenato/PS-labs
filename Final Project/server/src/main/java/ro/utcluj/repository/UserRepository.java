package ro.utcluj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcluj.enitity.User;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User getUserByUsernameAndPassword(String username, String password);
    User getUserByUsername(String username);
    User getUserById(int id);
    List<User> findAll();

}
