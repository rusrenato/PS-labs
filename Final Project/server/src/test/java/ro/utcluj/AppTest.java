package ro.utcluj;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ro.utcluj.enitity.User;
import ro.utcluj.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = App.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private UserService userService;

    @Test
    public void rank() {
        User user = userService.getUser(4);
        System.out.println(user.getRank());
       /* System.out.println(user.getNumberOfWashedCars());
        userService.updateRank(user.getName(),2.00);
        System.out.println(user.getRank());
        System.out.println(user.getNumberOfWashedCars());*/
    }

    @Test
    public void Strings() {
        System.out.println(49 % 10);
        System.out.println(159 % 10);
        System.out.println(8 % 10);
        System.out.println(9 % 10);
    }
}
