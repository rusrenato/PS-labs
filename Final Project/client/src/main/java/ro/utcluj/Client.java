package ro.utcluj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.utcluj.api.CarServiceInterface;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.api.WashServiceInterface;

@SpringBootApplication
@ComponentScan({
        "ro.utcluj.config",
        "ro.utcluj.controller",
        "ro.utcluj.notification",
})
public class Client extends Application {

    private ConfigurableApplicationContext context;
    private Parent root;

    public static void main(String[] args) {
       // ApplicationContext context = SpringApplication.run(Client.class);
       // SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("user", "userPass"));

     /*   UserServiceInterface userService = context.getBean(UserServiceInterface.class);
        CarServiceInterfacec carService = context.getBean(CarServiceInterfacec.class);
        WashServiceInterface washService = context.getBean(WashServiceInterface.class);

        System.out.println(userService.getUserDTO(1).toString());
        System.out.println(carService.getCarDTO(1).toString());
        System.out.println(washService.getWashDTO(10).toString());*/




        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(Client.class);

        WashServiceInterface washService = context.getBean(WashServiceInterface.class);
        CarServiceInterface carService = context.getBean(CarServiceInterface.class);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginView.fxml"));
        loader.setControllerFactory(context::getBean);
        root = loader.load(); // ude stie fxml de controller-ul de clien
    }

    @Override
    public void stop() throws Exception {
        context.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
