package ro.utcluj.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.dto.Role;
import ro.utcluj.dto.UserDTO;


import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

@Component
public class AdminController implements Initializable, Serializable {
    @Autowired
    private ConfigurableApplicationContext context;
    private Parent nextView;

    @FXML
    private TableView<UserDTO> tableView;
    @FXML
    private TableColumn<UserDTO, Integer> idColumn;
    @FXML
    private TableColumn<UserDTO, String> nameColumn;
    @FXML
    private TableColumn<UserDTO, String> usernameColumn;
    @FXML
    private TableColumn<UserDTO, String> passwordColumn;
    @FXML
    private TableColumn<UserDTO, Role> rolColumn;
    @FXML
    private TableColumn<UserDTO, Integer> walletColumn;
    @FXML
    private TableColumn<UserDTO, Integer> salaryColumn;
    @FXML
    private TableColumn<UserDTO, Integer> bonusesColumn;
    @FXML
    private TableColumn<UserDTO, Integer> washedCarsColumn;
    @FXML // ba nu stiu, oare nu ai putea sa iti iei context-ul de pe clientview?
    private Text roleError;
    @FXML
    private Text usernameError;
    @FXML
    private Text passwordError;
    @FXML
    private ComboBox<Role> roleComboBox;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TextField salaryInput;
    @FXML
    private TextField bonusInput;
    @FXML
    private Text salaryError;
    @FXML
    private Text bonusesError;
    @FXML
    private SplitMenuButton splitMenuButton;
    @FXML
    private MenuItem pdf;
    @FXML
    private MenuItem txt;


    @Autowired
    private UserServiceInterface userService;
    private int userId;
    private Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("password"));
        rolColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, Role>("rol"));
        walletColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, Integer>("wallet"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, Integer>("salary"));
        bonusesColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, Integer>("bonuses"));
        washedCarsColumn.setCellValueFactory(new PropertyValueFactory<UserDTO, Integer>("numberOfWashedCars"));
        System.out.println(userService.findAll());
        tableView.setItems(FXCollections.observableArrayList(userService.findAll()));

        ObservableList<Role> observableList = FXCollections.observableArrayList();
        Collections.addAll(observableList, Role.class.getEnumConstants());
        roleComboBox.setItems(observableList);



        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                roleError.setText("");
                usernameError.setText("");
                passwordInput.setText(newValue.getPassword());
                roleComboBox.setValue(newValue.getRol());
                usernameInput.setText(newValue.getUsername());
                userId = newValue.getId();
                if (newValue.getRol().name().equals("Worker")) {
                    salaryInput.setText("0");
                } else {
                    salaryInput.setText("");
                }
            }
        });
    }


    public String getRol() {
        System.out.println(roleComboBox.getValue());
        return roleComboBox.getValue().name();
    }

    private String getRolAsString() {
        return roleComboBox.getValue().name();
    }

    private void clearError() {
        passwordError.setText("");
        usernameError.setText("");
        roleError.setText("");
        salaryError.setText("");
        bonusesError.setText("");
    }

    private void clearInputs() {
        usernameInput.setText("");
        passwordInput.setText("");
        salaryInput.setText("");
        bonusInput.setText("");
    }

    private boolean checkInputs() {
        if (usernameInput.getText().equals("")) {
            usernameError.setText("Please insert a username");
            usernameError.setFill(Color.RED);
            return false;
        }
        if (passwordInput.getText().equals("")) {
            passwordError.setText("Please insert a password");
            passwordError.setFill(Color.RED);
            return false;
        }
        if (passwordInput.getText().length() < 6) {
            passwordError.setText("Password must be longer than 6");
            passwordError.setFill(Color.RED);
            return false;
        }
        if (roleComboBox.getValue() == null) {
            roleError.setText("Please insert a Role");
            roleError.setFill(Color.RED);
            return false;
        }
        if (!salaryInput.getText().equals("") && !roleComboBox.getValue().name().equals("Worker")) {
            salaryError.setText("Can't add salary to " + roleComboBox.getValue().name());
            salaryError.setFill(Color.RED);
            return false;
        }
        if (roleComboBox.getValue().name().equals("Worker") && !salaryInput.getText().matches("[0-9]+")) {
            salaryError.setText("Number please");
            salaryError.setFill(Color.RED);
            return false;
        }
        System.out.println(getRolAsString());
        return true;
    }


    public void insert() {
        clearError();
        if (!userService.validateUsername(usernameInput.getText())) {
            if (checkInputs()) {
                if (!roleComboBox.getValue().name().equals("Worker")) {
                     userService.insert(usernameInput.getText(), passwordInput.getText(), getRol(), 0);
                } else if (roleComboBox.getValue().name().equals("Worker") && salaryInput.getText().equals("")) {
                    salaryError.setText("Worker needs salary");
                    salaryError.setFill(Color.RED);
                } else {
                    userService.insert(usernameInput.getText(), passwordInput.getText(), getRol(), Integer.parseInt(salaryInput.getText()));
                }
                tableView.setItems(FXCollections.observableArrayList(userService.findAll()));
            }
        } else {
            usernameError.setText("Username already exists");
            usernameError.setFill(Color.RED);
        }
        clearInputs();
    }

    private boolean isAWorker() {
        if (userService.getRoleOfUser(userId).equals(Role.valueOf("Worker"))) {
            return true;
        } else {
            roleError.setText("Not a worker");
            roleError.setFill(Color.RED);
            return false;
        }
    }

    public void update() {
        clearError();
        if (checkInputs()) {
            if (isAWorker()) {
                userService.update(userId, usernameInput.getText(), passwordInput.getText(), getRol(), Integer.parseInt(salaryInput.getText()));
            } else {
                userService.update(userId, usernameInput.getText(), passwordInput.getText(), getRol(), Integer.parseInt(salaryInput.getText()));
            }
            tableView.setItems(FXCollections.observableArrayList(userService.findAll()));
            clearInputs();
        }
    }

    public void delete() {
        clearError();
        if (userId != 1) {
            userService.delete(userId);
            tableView.setItems(FXCollections.observableArrayList(userService.findAll()));
        } else {
            usernameError.setText("Can't delete the boss");
            usernameError.setFill(Color.RED);
        }
    }

    public void giveBonus() {
        clearError();
        if (userService.getRoleOfUser(userId).equals(Role.valueOf("Worker"))) {
            if (!usernameInput.getText().equals("") && !bonusInput.getText().equals("") && bonusInput.getText().matches("[0-9]+")) {
                userService.giveBonus(userId, Integer.parseInt(bonusInput.getText()) + userService.getUserDTO(userId).getBonuses());
                tableView.setItems(FXCollections.observableArrayList(userService.findAll()));
            } else {
                roleError.setFill(Color.RED);
                roleError.setText("Select a valid bonus & username");
            }

        } else {
            bonusesError.setText("Not a worker");
            bonusesError.setFill(Color.RED);
        }
        clearInputs();
    }


    public void logout() throws IOException {
        clearError();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginView.fxml"));
        loader.setControllerFactory(context::getBean);
        nextView = loader.load();
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) passwordInput.getScene().getWindow();
        stage.setScene(newScene);
    }



}
