package ro.utcluj.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class RegisterController implements Serializable {
    @Autowired
    private ConfigurableApplicationContext context;
    private Parent nextView;

    @FXML
    private TextField nameInput;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Text usernameError;

    @Autowired
    private UserServiceInterface userService;

    private FXMLLoader fxmlLoader;

    public void register(){
        if(userService.validateUsername(usernameInput.getText())){
            usernameError.setText("Username already exists");
            usernameError.setFill(Color.RED);
        } else {
            userService.insertRegister(nameInput.getText(), usernameInput.getText(),passwordInput.getText() );
            usernameInput.clear();
            passwordInput.clear();
            nameInput.clear();
            usernameError.setText("Succes");
            usernameError.setFill(Color.GREEN);
        }
    }

    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginView.fxml"));
        loader.setControllerFactory(context::getBean);
        nextView = loader.load();
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) passwordInput.getScene().getWindow();
        stage.setScene(newScene);
    }

}
