/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author HimaMarey
 */


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class NetWork {

    private Socket socket;
    private DataInputStream dataInputStream;
    private PrintStream printStream;
    private String IPAddress;

    public NetWork(String IPAddress) throws IOException {
        this.IPAddress = IPAddress;
        socket = new Socket(IPAddress, 4000);
        dataInputStream = new DataInputStream(socket.getInputStream());
        printStream = new PrintStream(socket.getOutputStream());
    }

    public void setIpAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public void sendMessage(String message) {
        printStream.println(message);
        System.out.println(message);
    }
    public String reciveMessage() {
        String message = null;
        try {
            message = dataInputStream.readLine();
            System.out.println(message);
        } catch (IOException ex) {
            Logger.getLogger(NetWork.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;    
    }

    public void closeConnection() {
        try {
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (printStream != null) {
                printStream.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ex) {
             showAlert("client  Stoooop");
            Logger.getLogger(NetWork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void showAlert(String message){
        Alert informationAlert = new Alert(Alert.AlertType.ERROR);

        informationAlert.setTitle("");

        informationAlert.setContentText(message);

        informationAlert.showAndWait();
    }
}