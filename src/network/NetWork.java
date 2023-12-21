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



import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import mynev.Mynav;
import register.RegisterScreenBase;
import remotePlay.Invite;


public class NetWork {

    protected DataInputStream dataInputStream;
    protected Socket socket;
    protected BufferedReader bufferedReader;
    protected PrintStream printStream;
    String message;
    protected String IP;

    public NetWork(String ServerIP) {
        try {
            if (socket == null || !socket.isConnected() || socket.isClosed()) {
                socket = new Socket(ServerIP, 4000);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                dataInputStream = new DataInputStream(socket.getInputStream());
                printStream = new PrintStream(socket.getOutputStream());
                reciveMessage();
            }

            IP = socket.getLocalAddress().getHostAddress();

        } catch (IOException ex) {
            showAlert("Invalid Server IP");
        }
    }

    public void sendMessage(String message) {
        new Thread() {
            @Override
            public void run() {
                printStream.println(message);       
                System.out.println(message);

            }
        }.start();
    }
    public void reciveMessage() {
        new Thread() {
            @Override
            public void run() {

                try {
                    while (socket.isConnected() && !socket.isClosed()) {

                        message = bufferedReader.readLine();

                        if (message == null) {
                            socket.close();
                            break;
                        }
                        JsonReader jsonReader = (JsonReader) Json.createReader(new StringReader(message));
                        JsonObject object = jsonReader.readObject();
                        JsonParser jsonParser = new JsonParser();
                        switch (object.getString("key"))
                        {
                            case "login":
                            {
                                if (object.getString("msg").equals("login successfully"))
                                {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                           showAlert(object.getString("msg"));
                                           Mynav.navigateTo(new Invite());
                                        }
                                    });
                                } else if (object.getString("msg").equals("Invalid Password please Try again")
                                        ||object.getString("msg").equals("Invalid username please Sign up")
                                        ) 
                                {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            showAlert(object.getString("msg"));
                                        }
                                    });
                                }
                            }
                            break;
                        }
                    }
                } catch (SocketException ex) {
                        showAlert("Server is Down");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();    
    }
    public String getIp() {
        return IP;
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
            Logger.getLogger(NetWork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void showAlert(String message){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);

        informationAlert.setTitle("");

        informationAlert.setContentText(message);

        informationAlert.showAndWait();
    }
}