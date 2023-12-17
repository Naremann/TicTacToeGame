package recordTable;

import gameBoard.GameBoardUI;
import homePage.XOgameUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import mynev.Mynav;

public  class RecordList extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final Label lbPlayer1;
    protected final Label lbDateTime;
    protected final Label lbPlayer2;
    protected final ListView listView;
    
    protected final Button btnhome;

    public RecordList() {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        lbPlayer1 = new Label();
        lbDateTime = new Label();
        lbPlayer2 = new Label();
        listView = new ListView();
        btnhome = new Button();
        
       ObservableList<String> myListView = FXCollections.observableArrayList("\tSAMY\t\t\t\t\tDEC17  17:30:15\t\t\t\t RAMI","\tSARA\t\t\t\t\tDEC17  17:30:15\t\t\t\t HADEER","\tSALAH\t\t\t\t\tDEC17  17:30:15\t\t\t\t AMGED");
        ListView<String> listView = new ListView<String>(myListView);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setPrefHeight(61.0);
        gridPane.setPrefWidth(601.0);
        gridPane.setStyle("-fx-background-color: gray;");

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        
        
        GridPane.setColumnIndex(btnhome, 0);
        btnhome.setMnemonicParsing(false);
        btnhome.setText("Back");
        btnhome.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
             Mynav.navigateTo(new XOgameUI(), event);
        });

        lbPlayer1.setText("PLAYER 1");
        GridPane.setMargin(lbPlayer1, new Insets(0.0, 0.0, 0.0, 50.0));
        lbPlayer1.setFont(new Font("Arial Bold", 20.0));

        GridPane.setColumnIndex(lbDateTime, 1);
        lbDateTime.setText("DATE AND TIME");
        GridPane.setMargin(lbDateTime, new Insets(0.0, 0.0, 0.0, 25.0));
        lbDateTime.setFont(new Font("Arial Bold", 20.0));

        GridPane.setColumnIndex(lbPlayer2, 2);
        lbPlayer2.setText("PLAYER 2");
        lbPlayer2.setFont(new Font("Arial Bold", 20.0));
        GridPane.setMargin(lbPlayer2, new Insets(0.0, 0.0, 0.0, 50.0));
        gridPane.setOpaqueInsets(new Insets(0.0));
        setTop(gridPane);

        BorderPane.setAlignment(listView, javafx.geometry.Pos.CENTER);
        listView.setPrefHeight(351.0);
        listView.setPrefWidth(600.0);
        setCenter(listView);
        
        

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getChildren().add(lbPlayer1);
        gridPane.getChildren().add(lbDateTime);
        gridPane.getChildren().add(lbPlayer2);
        gridPane.getChildren().add(btnhome);
        

    }
}