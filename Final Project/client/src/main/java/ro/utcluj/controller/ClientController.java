package ro.utcluj.controller;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcluj.api.CarServiceInterface;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.api.WashServiceInterface;
import ro.utcluj.dto.CarDTO;
import ro.utcluj.dto.UserDTO;
import ro.utcluj.dto.WashDTO;
import ro.utcluj.notification.NotificationService;
import ro.utcluj.notification.ServerSocketListener;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

@Component
public class ClientController implements Initializable {

    @Autowired
    private ConfigurableApplicationContext context;
    private Parent nextView;

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
    @FXML
    private TableColumn<WashDTO, String> workerColumn;
    @FXML
    private ComboBox<String> carNumberComboBox;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ComboBox<String> hourComboBox;
    @FXML
    private ComboBox<String> workerComboBox;
    @FXML
    private Text typeError;
    @FXML
    private Text carComboNumberError;
    @FXML
    private Text workerError;
    @FXML
    private Text hourError;


    //Table 2

    @FXML
    private TableView<CarDTO> tableViewCar;
    @FXML
    private TableColumn<CarDTO, Integer> idColumn2;
    @FXML
    private TableColumn<CarDTO, String> carNumberColumn2;
    @FXML
    private TableColumn<CarDTO, String> colorColumn;
    @FXML
    private TextField colorInput;
    @FXML
    private TextField carNumberInput;
    @FXML
    private Text carNumberError;
    @FXML
    private Text colorError;
    @FXML
    private Text moneyText;
    @FXML
    private TextField moneyInput;
    @FXML
    private Text moneyError;
    @FXML
    private Text washDoneMessage;

    @FXML
    private Button button;



    private static String mesaj;
    int j = 0;

    @Autowired
    private WashServiceInterface washService;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private CarServiceInterface carService;
    @Autowired
    private WorkerController workerController;
    @Autowired
    NotificationService notificationService;


    private HashMap<String, Integer> washTypesMap = new HashMap<>();
    private HashMap<String, ObservableList<String>> workerHourMap = new HashMap<>();

    private int washId;
    private int carId;
    private ObservableList<String> carObservableList = FXCollections.observableArrayList();
    private ObservableList<String> hourObservableList = FXCollections.observableArrayList();
    private ObservableList<String> workerObservableList = FXCollections.observableArrayList();

    private StringProperty stringProperty = new SimpleStringProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        stringProperty.set("Init");

        try {
            notificationService.connectToNotificationServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Table 1

        idColumn.setCellValueFactory(new PropertyValueFactory<WashDTO, Integer>("id"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<WashDTO, Integer>("price"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<WashDTO, String>("type"));
        carNumberColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getCar().getNrInmatriculare()));
        hourColumn.setCellValueFactory(new PropertyValueFactory<WashDTO, String>("hour"));
        workerColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<String>(cellData.getValue().getWorker().getName()));
        tableView.setItems(FXCollections.observableArrayList(washService.findByCar(LoginController.getUserId())));

        //Table 2

        idColumn2.setCellValueFactory(new PropertyValueFactory<CarDTO, Integer>("id"));
        carNumberColumn2.setCellValueFactory(new PropertyValueFactory<CarDTO, String>("nrInmatriculare"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<CarDTO, String>("color"));
        tableViewCar.setItems(FXCollections.observableArrayList(carService.findAllCarsOfAClinet(LoginController.getUserId())));

        ObservableList<String> typeObservableList = FXCollections.observableArrayList();

        moneyText.setText(userService.getUserDTO(LoginController.getUserId()).getWallet() + "");


        for (CarDTO car : carService.findAllCarsOfAClinet(LoginController.getUserId())) {
                carObservableList.add(car.getNrInmatriculare());
        }
        carNumberComboBox.setItems(carObservableList);

        washTypesMap.put("Exterior", 10);
        washTypesMap.put("Interior", 30);
        washTypesMap.put("Interior-Exterior", 50);

        for (String s : washTypesMap.keySet()) {
            typeObservableList.add(s);
        }
        typeComboBox.setItems(typeObservableList);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                washId = newValue.getId();
                carNumberComboBox.setValue(newValue.getCar().getNrInmatriculare());
                typeComboBox.setValue(newValue.getType());
                hourComboBox.setValue(newValue.getHour());
            }
        });

        ////

        stringProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue.substring(6));
                washDoneMessage.setText("Wash done for hour " + newValue.substring(0,5));
                tableView.setItems(FXCollections.observableArrayList(washService.findByCar(LoginController.getUserId())));
                workerHourMap.get(newValue.substring(6)).add(newValue.substring(0,5));


            }
        });

        ////


        tableViewCar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                carId = newValue.getId();
            }
        });

        for (int i = 8; i < 17; i++) {
            if (i < 10) {
                hourObservableList.add("0" + i + ":00");
            } else {
                hourObservableList.add(i + ":00");
            }
        }

        for (UserDTO user : userService.findAll()) {
            if (user.getRol().name().equals("Worker")) {
                ObservableList<String> hourObservableList2 = FXCollections.observableArrayList();
                hourObservableList2.addAll(hourObservableList);
                workerHourMap.put(user.getName(), hourObservableList2);
            }
        }

        for (WashDTO wash : washService.findByCar((LoginController.getUserId()))) {
            System.out.println();
            workerHourMap.get(wash.getWorker().getUsername()).remove(wash.getHour());
        }


        if (workerController.getBreakList() != null)
            workerHourMap.get(workerController.getBreakList().getKey()).removeAll(workerController.getBreakList().getValue());

        for (String worker : workerHourMap.keySet()) {
            if (!workerObservableList.contains(worker))
                workerObservableList.add(worker);
        }

        workerComboBox.setItems(workerObservableList);


        workerComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    if(newValue != null)
                    hourComboBox.setItems(workerHourMap.get(newValue).sorted());

                }
        );
    }

    private void clearError() {
        carNumberError.setText("");
        colorError.setText("");
    }

    private void clearInput() {
        carNumberInput.setText("");
        colorInput.setText("");
    }

    public void insertWash() {

        if (userService.getUserDTO(LoginController.getUserId()).getWallet() > washTypesMap.get(typeComboBox.getValue())) {
            washService.insert(carNumberComboBox.getValue(), typeComboBox.getValue(), hourComboBox.getValue(), washTypesMap.get(typeComboBox.getValue()), workerComboBox.getValue());
            tableView.setItems(FXCollections.observableArrayList(washService.findByCar(LoginController.getUserId())));
            userService.updateMoney(LoginController.getUserId(), -washTypesMap.get(typeComboBox.getValue()));
            workerHourMap.get(workerComboBox.getValue()).remove(hourComboBox.getValue());
        } else {
            typeError.setText("Not enough money");
            typeError.setFill(Color.RED);

        }
    }

    public void deleteWash() {
        int price = washService.getWashDTO(washId).getPrice();
        String worker = washService.getWashDTO(washId).getWorker().getUsername();
        String hour = washService.getWashDTO(washId).getHour();
        washService.delete(washId);
        tableView.setItems(FXCollections.observableArrayList(washService.findByCar(LoginController.getUserId())));
        userService.updateMoney(LoginController.getUserId(), price);
        if (!workerHourMap.get(worker).contains(hour))
            workerHourMap.get(worker).add(hour);
    }


    //Table 2

    public void insertCar() {
        if (carNumberInput.getText().matches("[A-Z][A-Z]-[0-9][1-9]-[A-Z][A-Z][A-Z]")) {
            if (colorInput.getText().matches("[a-zA-Z]+")) {
                if (carService.getCarByNrInmatriculare(carNumberInput.getText()) == null) {
                    carService.insert(carNumberInput.getText(), colorInput.getText(), (LoginController.getUserId()));
                    tableViewCar.setItems(FXCollections.observableArrayList(carService.findAllCarsOfAClinet(LoginController.getUserId())));
                    carObservableList.add(carNumberInput.getText());
                    clearError();
                } else {
                    carNumberError.setText("Car already exists");
                    carNumberError.setFill(Color.RED);
                }
            } else {
                colorError.setText("Wrong input");
                colorError.setFill(Color.RED);
            }
        } else {
            carNumberError.setText("Wrong format");
            carNumberError.setFill(Color.RED);
        }
    }

    public void deleteCar() {
        if (carId != 0) {
            String carNumber = carService.getCarById(carId).getNrInmatriculare();
            carService.delete(carId);
            tableViewCar.setItems(FXCollections.observableArrayList(carService.findAllCarsOfAClinet(LoginController.getUserId())));
            carObservableList.remove(carNumber);
            clearError();
            clearInput();
        } else {
            carNumberError.setText("Please pick a car");
            carNumberError.setFill(Color.RED);
        }
    }

    public void setUserMoney() {
        moneyText.setText(userService.getUserDTO(LoginController.getUserId()).getWallet() + "");
    }

    public void addMoney() {
        if (moneyInput.getText().matches("[0-9]+")) {
            userService.updateMoney(LoginController.getUserId(), Integer.parseInt(moneyInput.getText()));
            moneyError.setText("");
            setUserMoney();
        } else {
            moneyError.setText("Wrong input");
            moneyError.setFill(Color.RED);
        }
    }


    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginView.fxml"));
        loader.setControllerFactory(context::getBean);
        nextView = loader.load();
        Scene newScene = new Scene(nextView);
        Stage stage = (Stage) carNumberInput.getScene().getWindow();
        stage.setScene(newScene);
        carObservableList.clear();
        hourObservableList.clear();
    }

   public void getText(String text){
       stringProperty.set(text);
       System.out.println(stringProperty);
   }

   public void doButton(){
        stringProperty.set("Yass " + ++j);
   }


}
