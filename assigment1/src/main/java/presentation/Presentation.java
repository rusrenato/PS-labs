package presentation;

import domain.Zbor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mapper.UserMapper;
import mapper.ZborMapper;
import services.Services;

import java.sql.SQLException;


public class Presentation extends Application {

   private Scene scene1, scene2;

    public static void main(String[] args)  {
        launch(args);
    }

    public void start(Stage window) throws Exception{

        ZborMapper zborMapper = new ZborMapper();
        UserMapper userMapper = new UserMapper();

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name Input
        TextField nameInput = new TextField("Bucky");
        GridPane.setConstraints(nameInput, 1, 0);

        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);

        //Password Input
        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);

        //Login
        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1, 2);
        loginButton.setOnAction(e -> {
          try {
              if(userMapper.findByName(nameInput.getText()).getUsername() != null){
                  window.setScene(scene2);
              } else {
                    AlertBox.display("Error","Try again");
              }
          }catch (SQLException f){
              f.printStackTrace();
          }


            System.out.println(nameInput.getText());
        } );


        //Add everything to grid
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton);
        scene1 = new Scene(grid, 300, 200);



        Button button2 = new Button("Magic");
        button2.setOnAction(e -> {

            try {
                for (Zbor zbor : zborMapper.findAll()) {
                    System.out.println(zbor.toString());
                }
            } catch (SQLException f) {
                f.printStackTrace();
            }

        });


       //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2,400,500);

        window.setScene(scene1);
        window.setTitle("Login");
        window.show();

    }

}
