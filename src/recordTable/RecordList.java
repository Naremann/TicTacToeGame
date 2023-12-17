package recordTable;

import gameBoard.DisplayRecord;
import homePage.XOgameUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mynev.Mynav;
 import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;

public class RecordList extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final Label lbPlayer1;
    protected final Label lbDateTime;
    protected final Label lbPlayer2;
    protected final ListView<String> listView;
    protected final List<String> rMoves;
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
        listView = new ListView<>();
        btnhome = new Button();
        rMoves = new ArrayList<>();

      

        ObservableList<String> myListView = FXCollections.observableArrayList();
         loadMovesFromFile(myListView);
         System.out.println("Contents of myListView: " + myListView);
         //System.out.println();
        listView.setItems(myListView);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setPrefHeight(61.0);
        gridPane.setPrefWidth(601.0);
        gridPane.setStyle("-fx-background-color: #E4E0D7;");

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

        GridPane.setColumnIndex(btnhome, 2);
        GridPane.setMargin(btnhome, new Insets(50.0, 50.0, 0.0, 0.0));
        btnhome.setText("BACK");
        btnhome.setStyle("-fx-background-color: #E4E0D7; -fx-text-fill: black;");
        btnhome.setFont(Font.getDefault());
        btnhome.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            Mynav.navigateTo(new XOgameUI(), event);
        });

        BorderPane.setAlignment(btnhome, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(btnhome, new Insets(10, 0, 10, 0));

        btnhome.setPrefWidth(100);
        btnhome.setPrefHeight(40);

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
        listView.setPrefHeight(300.0);
        listView.setPrefWidth(600.0);
        BorderPane.setMargin(listView, new Insets(0, 0, 20, 0));
        setCenter(listView);

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getChildren().add(lbPlayer1);
        gridPane.getChildren().add(lbDateTime);
        gridPane.getChildren().add(lbPlayer2);
        setBottom(btnhome);
        BorderPane.setAlignment(btnhome, Pos.CENTER);
        
        
         listView.setOnMouseClicked((MouseEvent event) -> {
            String selectedRecord = listView.getSelectionModel().getSelectedItem();
            
            if (selectedRecord != null) {
                String[] recordParts = selectedRecord.split("!");
                if (recordParts.length > 4) {
                    StringBuilder remainingPart = new StringBuilder();
                    for (int i = 3; i < recordParts.length; i++) {
                        remainingPart.append(recordParts[i]);
                        if (i < recordParts.length - 1) {
                            remainingPart.append("!");
                        }
                     String rest = remainingPart.toString();
                     Mynav.navigateTo(new DisplayRecord(rest), event);
                   System.out.println("what i am sending: " + selectedRecord);
 
                }
            }
        }
        });
         
        // listView.setCellFactory(param -> {
            
            //return cell;
       // });

        listView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setText(item);
                    setGraphic(null);
                    setPrefHeight(50);
                    setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    int index = getIndex();
                    if (index % 2 == 0) {
                        setStyle("-fx-background-color: darkgray;");
                    } else {
                        setStyle("-fx-background-color: lightgray;");
                    }
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
           
            
        });
        
    }

   

// ...

protected void loadMovesFromFile(ObservableList<String> movesList) {
    try (BufferedReader reader = new BufferedReader(new FileReader("Record History.txt"))) {
        String line;
        StringBuilder record = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.equals("&")) {
                rMoves.add(record.toString());
                String[] recordParts = record.toString().split("!");
                if (recordParts.length == 6) {
                    String formattedRecord = String.format("%-30s%-30s%-30s","\t"+ recordParts[0], recordParts[2], "\t\t"+recordParts[1]);
                    
                    
// Use Platform.runLater to update UI components on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        movesList.add(formattedRecord);
                        System.out.println("Added to movesList: " + formattedRecord);
                    });
                }
                record.setLength(0);
            } else {
                record.append(line).append("\t\t\t");
            }
        }               
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
