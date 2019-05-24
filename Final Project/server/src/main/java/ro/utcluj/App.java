package ro.utcluj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.utcluj.repository.CarRepository;
import ro.utcluj.repository.UserRepository;

/**
 * Hello world!
 */

@SpringBootApplication


public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
