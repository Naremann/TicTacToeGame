package remotePlay;


import dto.DTOPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public  class ListviewRequestBase extends AnchorPane {

    protected final ListView listviewrequest;

    public ListviewRequestBase() {

        listviewrequest = new ListView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        listviewrequest.setPrefHeight(400.0);
        listviewrequest.setPrefWidth(600.0);

        getChildren().add(listviewrequest);

    }
            

    public ListviewRequestBase(ListView listviewrequest, Node... children) {
        super(children);
        this.listviewrequest = listviewrequest;
    }

    public ListviewRequestBase(ListView listviewrequest) {
        this.listviewrequest = listviewrequest;
    
           }
         
    
}