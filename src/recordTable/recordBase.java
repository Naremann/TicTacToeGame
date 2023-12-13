package recordTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public  class recordBase extends VBox {

    protected final ListView lsciewInner;
    protected final ListView listView;

    public recordBase() {

        lsciewInner = new ListView();
        listView = new ListView();
        
        
        ListView<String> list = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList ();
        list.setItems(items);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        lsciewInner.setPrefHeight(660.0);
        lsciewInner.setPrefWidth(600.0);

        listView.setPrefHeight(403.0);
        listView.setPrefWidth(600.0);

        getChildren().add(lsciewInner);
        getChildren().add(listView);

    }
}
