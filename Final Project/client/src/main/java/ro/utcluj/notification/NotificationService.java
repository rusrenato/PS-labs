package ro.utcluj.notification;

import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.utcluj.controller.ClientController;
import ro.utcluj.controller.LoginController;
import ro.utcluj.controller.WorkerController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Component
public class NotificationService {

    private ClientController clientController;
    private WorkerController workerController;


    public NotificationService(ClientController clientController, WorkerController workerController) {
        this.clientController = clientController;
        this.workerController = workerController;
    }


    //@PostConstruct
    public void connectToNotificationServer() throws IOException {
        Socket socket = new Socket("localhost", 8081);

        Thread listenerThread = new Thread(new ServerSocketListener(socket, clientController,workerController));
        listenerThread.start();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());

        out.println("c" + LoginController.getUserId());
        System.out.println("Am trimis id-ul : " + LoginController.getUserId());

    }

}
