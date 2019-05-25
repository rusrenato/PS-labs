package ro.utcluj.controller;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.api.WashServiceInterface;
import ro.utcluj.dto.WashDTO;
import ro.utcluj.notification.NotificationService;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class WorkerController implements Initializable  {
    @Autowired
    private ConfigurableApplicationContext context;
    private Parent nextView;
    @FXML
    private ComboBox<String> breakComboBox;

    @FXML
    private Text numberText;
    @FXML
    private Text errorText;
    @FXML
    private TableView<WashDTO> tableView;
    @FXML
    private TableColumn<WashDTO, Integer> idColumn;
    @FXML
    private TableColumn<WashDTO, Integer> priceColumn;
    @FXML
    private TableColumn<WashDTO, String> hourColumn;
    @FXML
    private TableColumn<WashDTO, String> typeColumn;
    @FXML
    private TableColumn<WashDTO, String> carNumberColumn;


    @Autowired
    private WashServiceInterface washService;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private NotificationService notificationService;

    private int washId;
    private List<String> breakList = new ArrayList<>();
    private ObservableList<String> breakObservableList = FXCollections.observableArrayList();
    private Pair<String, List<String>> pair;
    private StringProperty stringProperty = new SimpleStringProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<WashDTO, Integer>("id"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<WashDTO, Integer>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<WashDTO, String>("type"));
        carNumberColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getCar().getNrInmatriculare()));
        hourColumn.setCellValueFactory(new PropertyValueFactory<WashDTO, String>("hour"));
        tableView.setItems(FXCollections.observableArrayList(washService.findByWorker(LoginController.getUserId())));

        stringProperty.set("Init");

        try {
            notificationService.connectToNotificationServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 8; i < 17; i++) {
            if (i < 10) {
                if (!breakObservableList.contains("0" + i + ":00")) {
                    if (!breakList.contains("0" + i + ":00"))
                        breakObservableList.add("0" + i + ":00");
                }
            } else {
                if (!breakObservableList.contains(i + ":00"))
                    if (!breakList.contains(i + ":00")) {
                        breakObservableList.add(i + ":00");
                    }
            }
        }

        for (WashDTO wash : washService.findByWorker(LoginController.getUserId())) {
            breakObservableList.remove(wash.getHour());
        }

        breakComboBox.setItems(breakObservableList);

        updateText();
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                washId = newValue.getId();
            }
        });

        ////

        stringProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("I got here");
                tableView.setItems(FXCollections.observableArrayList(washService.findByWorker(LoginController.getUserId())));
            }
        });

        ////

        pair = new Pair<>(userService.getUserDTO(LoginController.getUserId()).getUsername(), breakList);
    }


    public void washACar() {
        if (washId != 0) {
            washService.delete(washId);
            userService.updateNumberOfWashedCars(LoginController.getUserId(), 1 + userService.getUserDTO(LoginController.getUserId()).getNumberOfWashedCars());
            tableView.setItems(FXCollections.observableArrayList(washService.findByWorker(LoginController.getUserId())));
            washId = 0;
        } else {
            errorText.setText("Select a wash");
            errorText.setFill(Color.RED);
        }
        updateText();
    }

    private void updateText() {
        numberText.setText(userService.getUserDTO(LoginController.getUserId()).getNumberOfWashedCars() + "");
    }

    public void takeABreak() {
        String curentValue = breakComboBox.getValue();
        if (curentValue != null) {
            breakObservableList.remove(curentValue);
            breakList.add(curentValue);
            userService.updateNumberOfBreaks(LoginController.getUserId(),userService.getUserDTO(LoginController.getUserId()).getNumberOfBreaks() + 1);
        }


    }

    Pair<String, List<String>> getBreakList() {
        return pair;
    }


    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginView.fxml"));
        loader.setControllerFactory(context::getBean);
        nextView = loader.load();
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) numberText.getScene().getWindow();
        stage.setScene(newScene);
    }

    public void getText(String text){
        stringProperty.set(text);
        System.out.println(stringProperty);
    }


}
