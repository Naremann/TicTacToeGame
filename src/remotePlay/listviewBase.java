package remotePlay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.DTOPlayer;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import network.NetWork;

public  class listviewBase extends AnchorPane {

    protected final ListView listview;

    String IP;
    public listviewBase(String Ip) 
    {
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

        listview.setPrefHeight(450.0);
        listview.setPrefWidth(650.0);

        getChildren().add(listview);

    }
    public void receiveOnlinePlayers(List<DTOPlayer> onlinePlayers) { 
        listview.getItems().clear();
        ObservableList<InviteBase> cellList = FXCollections.observableArrayList();
        for (DTOPlayer player : onlinePlayers) {
            InviteBase cell = new InviteBase();
            cell.label1.setText(player.getUserName());
            
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
