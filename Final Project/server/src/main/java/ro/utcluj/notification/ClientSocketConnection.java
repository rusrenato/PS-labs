package ro.utcluj.notification;

import java.io.*;
import java.net.Socket;


/**
 * Runnable listening for incoming messages from the client
 */
class ClientSocketConnection {

    private final NotificationService notificationService;
    private BufferedReader in;
    private PrintWriter out;
    private ObjectInputStream objIn;
    private String id;

    ClientSocketConnection(Socket clientSocket, NotificationService notificationService) {
        this.notificationService = notificationService;
        createReaderAndWriter(clientSocket);
        new Thread(this::listenAndRespond).start(); // starting a new thread which listens to client messages
    }

    private void createReaderAndWriter(Socket clientSocket) {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            objIn = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception exception) {
            System.out.println("Client disconnected");
        }
    }

    /**
     * Listening for messages from the client, printing them to the console and responding to them
     */
    private void listenAndRespond() {
        try {
            while (true) {
                String recived = in.readLine();
                if(recived != null) {
                    if(recived.startsWith("c")){
                        id = (recived.substring(1));
                        System.out.println(recived);
                    } else {
                        System.out.println("Recived  : " + recived);
                        out.println(recived);
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println("Client disconnected");
            notificationService.removeClient(this);
        }
    }

    void sendMessage(String message) {
        out.println(message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
