package remotePlay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.DTOPlayer;
import dto.MyPlayer;
import homePage.XOgameUI;
import java.util.List;
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

    String IP;
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
                 }
        });
        
       

        listview.setPrefHeight(450.0);
        listview.setPrefWidth(650.0);

        getChildren().add(listview);
            getChildren().add(btnexit);

    }
    public void receiveOnlinePlayers(List<DTOPlayer> onlinePlayers) { 
        listview.getItems().clear();
        ObservableList<InviteBase> cellList = FXCollections.observableArrayList();
        for (DTOPlayer player : onlinePlayers) {
            InviteBase cell = new InviteBase();
            cell.label1.setText(player.getUserName());
            if(player.getUserName().equals(MyPlayer.userName))
                continue;
            
            listview.getItems().add(cell);
        }
    }
    void requestPlayers()
    {
        Gson gson = new GsonBuilder().create();
        JsonObject setJson = new JsonObject();
        setJson.addProperty("key", "onlinePlayers");
        String jsonString = gson.toJson(setJson);
        NetWork.getInstance(IP).sendMessage(jsonString);
    }

    
    
   
}
