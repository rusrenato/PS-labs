package ro.utcluj.notification;

import org.springframework.beans.factory.annotation.Autowired;
import ro.utcluj.controller.ClientController;
import ro.utcluj.controller.WorkerController;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class ServerSocketListener implements Runnable {

    private final Socket serverSocket;

    ClientController clientController;
    WorkerController workerController;


    ServerSocketListener(Socket serverSocket, ClientController clientController, WorkerController workerController) {
        this.serverSocket = serverSocket;
        this.clientController = clientController;
        this.workerController = workerController;
    }



    @Override
    public void run() {
        try {
            String message;
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            while (true) {
                message = in.readLine();
                if(message.contains("W")) {
                    System.out.println("Here ServerSocket : " + message);
                    clientController.getText(message.substring(1));
                } else if (message.contains("insert")){
                    workerController.getText(message);
                    System.out.println("Insertul " + message);
                }

            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("The server is no longer available");
        }
    }


}
