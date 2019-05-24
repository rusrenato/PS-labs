package ro.utcluj.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.dto.Role;

import java.io.IOException;
import java.io.Serializable;

@Component
public class LoginController implements Serializable {

    @Autowired
    private ConfigurableApplicationContext context;
    private Parent nextView;

    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Text usernameText;
    @FXML
    private Text passwordText;

    @Autowired
    private UserServiceInterface userService;

   // @Autowired
  //  private WorkerService workerService;

    private static int userId;


    private FXMLLoader fxmlLoader;

    public void login() throws IOException{



        String username = usernameInput.getText();
        String password = passwordInput.getText();
        if(!userService.validateUsername(username)){
            usernameText.setText("This username dosen't exists");
            usernameText.setFill(Color.RED);
        } else if (userService.validateUser(username,password)){
            userId = userService.getIdByUsername(username);
            Role rol = userService.getRoleOfUser(userId);
            if(rol == Role.Client){
                fxmlLoader = new FXMLLoader(getClass().getResource("/clientView.fxml"));
            } else if (rol == Role.Admin){
                fxmlLoader = new FXMLLoader(getClass().getResource("/adminView.fxml"));
            } else if (rol == Role.Worker){
                fxmlLoader = new FXMLLoader(getClass().getResource("/workerView.fxml"));
            }
            fxmlLoader.setControllerFactory(context::getBean);
            nextView = fxmlLoader.load();
            Scene newScene = new Scene(nextView);
            Stage stage = (Stage)usernameInput.getScene().getWindow();
            stage.setTitle(usernameInput.getText());
            stage.setScene(newScene);
        } else {
            passwordText.setText("Wrong password");
            passwordText.setFill(Color.RED);
        }
    }

   public static int getUserId() {
        return userId;
    }

    public void register() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/registrationView.fxml"));
        loader.setControllerFactory(context::getBean);
        nextView = loader.load();
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) passwordInput.getScene().getWindow();
        stage.setScene(newScene);
    }


}
