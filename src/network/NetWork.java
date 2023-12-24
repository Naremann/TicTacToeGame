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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dto.DTOPlayer;
import dto.MyPlayer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
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
import remotePlay.listviewBase;
import tictactoe.AlertMessage;
import tictactoe.login.LoginScreenBase;

public class NetWork {

    private static NetWork singleInstance;
    protected DataInputStream dataInputStream;
    protected Socket socket;
    protected BufferedReader bufferedReader;
    protected PrintStream printStream;
    String message;
    protected String IP;
   public listviewBase listviewBas; 
    List<DTOPlayer> onlinePlayers = new ArrayList<>();
    private NetWork(String ServerIP) {
        try {
            if (socket == null || !socket.isConnected() || socket.isClosed()) {
                socket = new Socket(ServerIP, 4000);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                dataInputStream = new DataInputStream(socket.getInputStream());
                printStream = new PrintStream(socket.getOutputStream());
                reciveMessage();
            }
            IP = ServerIP;
            //IP = socket.getLocalAddress().getHostAddress();

        } catch (IOException ex) {
            showAlert("Invalid Server IP");
        }
    }
    
    public static synchronized NetWork getInstance(String IP) {
        if (singleInstance == null) {
            singleInstance = new NetWork(IP);
        }
        return singleInstance;
    }

    public void sendMessage(String message) {
        new Thread() {
            @Override
            public void run() {
                if(socket != null)
                    printStream.println(message);
                System.out.println(message);

            }
        }.start();
    }

    public void reciveMessage() {
        if(socket != null)
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
                        switch (object.getString("key")) {
                            case "login": {
                                if (object.getString("msg").equals("login successfully")) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            showAlert(object.getString("msg"));
                                            MyPlayer.userName=object.getString("username");
                                            Mynav.navigateTo(new listviewBase(IP));
                                        }
                                    });
                                } else if (object.getString("msg").equals("Invalid Password please Try again")
                                        || object.getString("msg").equals("Invalid username please Sign up")) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            showAlert(object.getString("msg"));
                                        }
                                    });
                                }
                            }
                            break;
                            case "register":
                                handleMessageReceive(object);
                                break;
                                
                            case "invite":
                            {
                                
                            }
                            break;
                                
                            case "onlinePlayers":
                            {
//                                JsonParser jsonParser = new JsonParser();
//                            JsonObject json = jsonParser.parse(message).getAsJsonObject();
//                            
//                            System.out.println("hema mar3y hena :"+newJson);
                            com.google.gson.JsonObject modifiedJson = jsonParser.parse(message).getAsJsonObject();

                            //if (modifiedJson.has("onlinePlayers")) {
                                System.out.println(object.get("onlinePlayersList"));
                                JsonElement playersElement = modifiedJson.get("onlinePlayersList");
                                 System.out.println(playersElement);
                                if (playersElement.isJsonArray()) {
                                    JsonArray playersArray = playersElement.getAsJsonArray();
                                    
                                    
                                   // onlinePlayers.clear();
                                    for (JsonElement playerElement : playersArray) {
                                        DTOPlayer player = new Gson().fromJson(playerElement, DTOPlayer.class);
                                        //  if (player.getFullName().equals("My name") == false) {
                                        
                                        onlinePlayers.add(player);
                                        //}
                                    }
                                }
                                    listviewBas.receiveOnlinePlayers(onlinePlayers);
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

    void showAlert(String message) {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);

        informationAlert.setTitle("");

        informationAlert.setContentText(message);

        informationAlert.showAndWait();
    }

    void handleMessageReceive(JsonObject jsonObject) {
        if (jsonObject.getString("msg").equals("registed successfully")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMessage.showAlert(Alert.AlertType.INFORMATION, null, null, jsonObject.getString("msg"));
                    Mynav.navigateTo(new LoginScreenBase(IP));
                }

            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AlertMessage.showAlert(Alert.AlertType.INFORMATION, null, null, jsonObject.getString("msg"));

                }
            });
        }
    }
}
