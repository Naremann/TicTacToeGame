package remotePlay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.DTOPlayer;
import dto.DTORequest;
import dto.MyPlayer;
import homePage.XOgameUI;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import mynev.Mynav;
import network.NetWork;

public  class listviewBase extends AnchorPane {

    protected final ListView listview;
    Button  btnexit;
    int index;
    String IP;
    NetWork network;
    public listviewBase(String Ip) 
    {
        btnexit = new Button();
        this.IP=Ip;
        NetWork.getInstance(Ip).listviewBas = this;
        this.requestPlayers();
        listview = new ListView();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(450.0);
        setPrefWidth(650.0);
        btnexit.setLayoutX(10.0);
        btnexit.setLayoutY(385.0);
        btnexit.setMnemonicParsing(false);
        btnexit.setPrefHeight(32.0);
        btnexit.setPrefWidth(622.0);
        btnexit.setStyle("-fx-background-color: red;");
        btnexit.setText("Exit");
        btnexit.setTextFill(javafx.scene.paint.Color.WHITE);
        btnexit.setFont(new Font("System Bold", 18.0));
        btnexit.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){  
                   Mynav.navigateTo(new XOgameUI(), event);
                   System.out.println(MyPlayer.userName);
                   exitPlayer(MyPlayer.userName);
                 }
        });
        
       

        listview.setPrefHeight(350.0);
        listview.setPrefWidth(650.0);
        
        

        getChildren().add(listview);
        getChildren().add(btnexit);

    }
    public void receiveOnlinePlayers(List<DTOPlayer> onlinePlayers) {
        Platform.runLater(()->{
            
            
            
        
        listview.getItems().clear();
        ObservableList<InviteBase> cellList = FXCollections.observableArrayList();
        for (int i = 0; i < onlinePlayers.size(); i++) {
           
            InviteBase cell = new InviteBase();
            cell.label1.setText(onlinePlayers.get(i).getUserName());
            cell.label3.setText(String.valueOf(onlinePlayers.get(i).getScore()));
            cell.btninvite.setOnAction(new EventHandler<ActionEvent>() {   
                @Override
                public void handle(ActionEvent event) {
                    // btninvite.setDisable(true);
                    Gson gson = new GsonBuilder().create();
                    
                    String senderUsername = MyPlayer.userName;
                    System.out.println(senderUsername);
                    String receiverUsername = cell.label1.getText();
                    //System.out.println(receiverUsername);
                    
                    DTORequest request = new DTORequest();
                    request.setUserNameReceiver(receiverUsername);
                    request.setUserNameSender(senderUsername);
                    
                    //index = getIndexOfOnlinePlayers(MyPlayer.onlinePlayers, receiverUsername);
                    
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("key", "invite");
                    jsonObject.addProperty("senderUsername", request.getUserNameSender());
                    jsonObject.addProperty("receiverUsername", request.getUserNameReceiver());
                    //jsonObject.addProperty("index", String.valueOf(index));
                    
                    //int index = listviewBase.getIndexOfOnlinePlayers(MyPlayer.onlinePlayers, receiverUsername);
                    
                    //  jsonObject.addProperty("index",index);
                    
                    String jsonRequest = gson.toJson(jsonObject);
         
                    network =NetWork.getInstance(IP);
                    network.sendMessage(jsonRequest);
                    //network.reciveMessage();
                }
            });

            if (onlinePlayers.get(i).getUserName().equals(MyPlayer.userName)||onlinePlayers.get(i).getStatus().equals("onGame")) {
                //MyPlayer.index = i;
                continue;
            }

            listview.getItems().add(cell);
        }
        });
    }
    void requestPlayers()
    {
        Gson gson = new GsonBuilder().create();
        JsonObject setJson = new JsonObject();
        setJson.addProperty("key", "onlinePlayers");
        String jsonString = gson.toJson(setJson);
        NetWork.getInstance(IP).sendMessage(jsonString);
    }

    
     int getIndexOfOnlinePlayers(List<DTOPlayer> onlinePlayers, String username) {
    int index = -1; // Initialize to -1 to indicate that the username was not found
    for (int i = 0; i < onlinePlayers.size(); i++) {
        if (onlinePlayers.get(i).getUserName().equals(username)) {
            index = i; // Update the index when the username is found
            break; // Exit the loop since the username is found
        }
    }

    return index;
}
     void exitPlayer(String userName)
     {
         Gson gson = new GsonBuilder().create();
        JsonObject jObject = new JsonObject();
        jObject.addProperty("key", "exitPlayer");
        jObject.addProperty("userName", userName);
        String jString = gson.toJson(jObject);
        System.out.println(jString);
        NetWork.getInstance(IP).sendMessage(jString);
     }
   
}
