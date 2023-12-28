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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import dto.DTOPlayer;
import dto.DTORequest;
import dto.MyPlayer;
import gameBoard.OnlineGame;
//import gameBoard.OnlineGame;
import gameBoard.TackIP;
import homePage.XOgameUI;
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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import mynev.Mynav;
import register.RegisterScreenBase;
import remotePlay.Invite;
import remotePlay.listviewBase;
import tictactoe.AlertMessage;
import tictactoe.login.LoginScreenBase;
//import com.google.gson.JsonObject;

public class NetWork {
    String recieverResponse = null;

    private static NetWork singleInstance;
    protected DataInputStream dataInputStream;
    protected Socket socket;
    protected BufferedReader bufferedReader;
    protected PrintStream printStream;
    String message;
    protected String IP;
    public listviewBase listviewBas;
    private boolean waitingForResponse = false;
    List<DTOPlayer> onlinePlayers = new ArrayList<>();
    public OnlineGame onlineGame;

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
            Mynav.navigateTo(new TackIP());
        }
    }
//    public NetWork(OnlineGame g) {
//        
//        try {
//            if (socket == null || !socket.isConnected() || socket.isClosed()) {
//                socket = new Socket(MyPlayer.serverIP, 4000);
//                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                dataInputStream = new DataInputStream(socket.getInputStream());
//                printStream = new PrintStream(socket.getOutputStream());
//                reciveMessage();
//            }
//            NetWork.onlineGame=g;
//            IP = socket.getLocalAddress().getHostAddress();
//
//        } catch (IOException ex) {
//            showAlert("Invalid Server IP");
//            Mynav.navigateTo(new TackIP());
//        }
//    }

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
                if (socket != null) {
                    printStream.println(message);
                }
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
                        switch (object.getString("key")) {
                            case "login": {
                                if (object.getString("msg").equals("login successfully")) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            showAlert(object.getString("msg"));
                                            MyPlayer.userName = object.getString("username");
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
                                handleRequestReceive(object);
                                break;

                            case "onlinePlayers": {
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
                                MyPlayer.onlinePlayers = onlinePlayers;
                                listviewBas.receiveOnlinePlayers(onlinePlayers);
                            }
                            break;
                            case "saveMove":
                                handleSaveMoveResponse(object);
                            break;
                            case "IGNORE":
                            {
                                Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            showAlert(object.getString("reciverName")+" Refuse your Invatation");
                                        }
                                    });
                            }
                            break;
                            case "ACCEPT":
                            {
                                Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            System.out.println(object.getString("reciverName"));
                                            MyPlayer.opponentName=object.getString("reciverName");
                                            onlineGame=new OnlineGame(object.getString("senderName"),object.getString("reciverName"),true);
                                             Mynav.navigateTo(onlineGame);
                                        }
                                    });
                            }
                            break;
                            case "exitPlayer":
                                handleExitPlayer(message,object);
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

    void handleRequestReceive(JsonObject jsonObject) {
        if (jsonObject.getString("msg").equals("Invite Sent Successfully")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    MyPlayer.opponentName=jsonObject.getString("senderUsername");
                    System.out.println(MyPlayer.opponentName);
                    showRequestAlert("Invitation", MyPlayer.opponentName     + " Wants To Play With You.");
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Alert ");
                    showRequestAlert("Error", jsonObject.getString("msg"));
                }

            });
        }
        System.out.println("alert is called");

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

    void showRequestAlert(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(null);

        ButtonType buttonNo = new ButtonType("IGNORE", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonYes = new ButtonType("ACCEPT");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonYes) {
            onlineGame=new OnlineGame(MyPlayer.opponentName,MyPlayer.userName,false);
            Mynav.navigateTo(onlineGame);
            recieverResponse = "ACCEPT";
            Gson gson = new GsonBuilder().create();
            com.google.gson.JsonObject jObject = new com.google.gson.JsonObject();
            jObject.addProperty("key", "ACCEPT");
            jObject.addProperty("senderName", MyPlayer.opponentName);
            jObject.addProperty("reciverName", MyPlayer.userName);
            String jString = gson.toJson(jObject);
              System.out.println(jString);
                    sendMessage(jString);
                    
            // Platform.runLater(() -> Mynav.navigateTo()); 
        } else {
            recieverResponse = "IGNORE";
            Gson gson = new GsonBuilder().create();
            com.google.gson.JsonObject jObject = new com.google.gson.JsonObject();
            jObject.addProperty("key", "IGNORE");
            jObject.addProperty("senderName", MyPlayer.opponentName);
            jObject.addProperty("reciverName", MyPlayer.userName);
            String jString = gson.toJson(jObject);
              System.out.println(jString);
                    sendMessage(jString);
          
        }
        System.out.println(recieverResponse);
         System.out.println(recieverResponse+"****************");
        handleRecieverAlertMessage(recieverResponse);
    }
    
    void handleRecieverAlertMessage(String response){
          Gson gson = new GsonBuilder().create();
          
            com.google.gson.JsonObject jObject = new com.google.gson.JsonObject();
            jObject.addProperty("key", "recieverResponse");
            jObject.addProperty("response", recieverResponse);
            jObject.addProperty("opponentNmae", MyPlayer.opponentName);
            String jString = gson.toJson(jObject);
                    sendMessage(jString);
    }
    
    void showAlertToSender(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    void handleSaveMoveResponse(JsonObject jsonObject) {
        String opponent = jsonObject.getString("userName");
        String row = jsonObject.getString("row");
        String col = jsonObject.getString("col");
        onlineGame.getMove(jsonObject.getString("mark"),Integer.parseInt(row),Integer.parseInt(col));
    }
    
    void handleExitPlayer(String msg,JsonObject object)
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run() {
                showAlertToSender("Game is End", object.getString("userName")+" Exit From The Game and You Are The Winner");
                Mynav.navigateTo(new XOgameUI());
            }
        });
        
    }
}