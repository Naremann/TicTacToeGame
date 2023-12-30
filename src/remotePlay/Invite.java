/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotePlay;

/**
 *
 * @author Orignal Vip
 */
/*public class Invite {
    
}*/
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DTORequest;
import dto.MyPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import mynev.Mynav;
import network.NetWork;
import com.google.gson.JsonObject;
import dto.DTOPlayer;
import java.util.List;
import dto.MyPlayer;
import static java.util.Collections.list;
//import xo.Xorequest;

public  class Invite extends AnchorPane {

    protected final FlowPane flowPane;
    protected final Label label;
    protected final TextField textField;
    protected final Button invite;
    NetWork network;

    public Invite(String IP) {

        flowPane = new FlowPane();
        label = new Label();
        textField = new TextField();
        invite = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        flowPane.setLayoutX(63.0);
        flowPane.setLayoutY(14.0);
        flowPane.setPrefHeight(51.0);
        flowPane.setPrefWidth(474.0);

        label.setText("player name");
        label.setFont(new Font("System Bold", 16.0));

        textField.setPrefHeight(37.0);
        textField.setPrefWidth(219.0);
        FlowPane.setMargin(textField, new Insets(0.0, 0.0, 0.0, 3.0));

        invite.setMnemonicParsing(false);
        invite.setPrefHeight(25.0);
        invite.setPrefWidth(80.0);
        invite.setStyle("-fx-background-radius: 20; -fx-background-color: green;");
        invite.setText("invite");
        invite.setTextFill(javafx.scene.paint.Color.WHITE);
        FlowPane.setMargin(invite, new Insets(0.0, 0.0, 0.0, 20.0));
        invite.setFont(new Font(16.0));
        //************
        invite.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                public void handle(ActionEvent event){ 
                    
                    invite.setDisable(true);
                    Gson gson = new GsonBuilder().create();
                    
                    String senderUsername = MyPlayer.userName;
                    System.out.println(senderUsername);
                    String receiverUsername = textField.getText();
                    System.out.println(receiverUsername);
                    
                    DTORequest request = new DTORequest();
                    request.setUserNameReceiver(receiverUsername);
                    request.setUserNameSender(senderUsername);
                    //int index = listviewBase.getIndexOfOnlinePlayers(MyPlayer.onlinePlayers, receiverUsername);
                    
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("key", "invite");
                    jsonObject.addProperty("senderUsername", request.getUserNameSender());
                    jsonObject.addProperty("receiverUsername", request.getUserNameReceiver());
                   // jsonObject.addProperty("index", index);
                    for (int i = 0; i < MyPlayer.onlinePlayers.size(); i++){
                        System.out.println(MyPlayer.onlinePlayers.get(i));
                    }
                   // int index = listviewBase.getIndexOfOnlinePlayers(MyPlayer.onlinePlayers, receiverUsername);
                    
                  //  jsonObject.addProperty("index",index);
                    
                    String jsonRequest = gson.toJson(jsonObject);
         
                    network =NetWork.getInstance(IP);
                    network.sendMessage(jsonRequest); 
                    network.reciveMessage();
                }
        });
        
                
                
      //******************
        flowPane.getChildren().add(label);
        flowPane.getChildren().add(textField);
        flowPane.getChildren().add(invite);
        getChildren().add(flowPane);

    }
    
    

}

