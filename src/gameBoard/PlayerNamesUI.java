/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import gameBoard.LocallGame;
import homePage.XOgameUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import mynev.Mynav;
import tictactoe.gamelevels.GameLevelsBase;
/**
 *
 * @author DELL
 */
public  class PlayerNamesUI extends BorderPane {

    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final Button btnBack;
    protected final Button btnNext;
    protected final GridPane gridPane0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final Label lbPlayer1;
    protected final Label lbPlayer2;
    public final TextField txtPlayer1;
    public final TextField txtPlayer2;
    
     private String player1_Name ;
     private String player2_Name;

    public PlayerNamesUI() {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        btnBack = new Button();
        btnNext = new Button();
        gridPane0 = new GridPane();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        lbPlayer1 = new Label();
        lbPlayer2 = new Label();
        txtPlayer1 = new TextField();
        txtPlayer2 = new TextField();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        
        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(30.0);
        btnBack.setPrefWidth(90.0);
        btnBack.setStyle("-fx-background-color: gray;"+
                         "-fx-text-fill: white;"+
                         "-fx-background-radius: 15;");
        btnBack.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        btnBack.setText("BACK");
        GridPane.setMargin(btnBack, new Insets(0.0, 0.0, 30.0, 100.0));
        btnBack.setFont(new Font("Arial Bold", 18.0));

        GridPane.setColumnIndex(btnNext, 1);
        btnNext.setMnemonicParsing(false);
        btnNext.setPrefHeight(33.0);
        btnNext.setPrefWidth(91.0);
        btnNext.setStyle("-fx-background-color: gray;"+
                         "-fx-text-fill: white;"+
                            "-fx-background-radius: 15;");
        btnNext.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        btnNext.setText("NEXT");
        btnNext.setFont(new Font("Arial Bold", 18.0));
        GridPane.setMargin(btnNext, new Insets(0.0, 0.0, 30.0, 95.0));
        BorderPane.setMargin(gridPane, new Insets(0.0, 0.0, 40.0, 0.0));
        setBottom(gridPane);

        BorderPane.setAlignment(gridPane0, javafx.geometry.Pos.CENTER);
        gridPane0.setPrefHeight(313.0);
        gridPane0.setPrefWidth(594.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints0.setMaxHeight(88.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(51.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(174.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(87.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMaxHeight(146.0);
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(47.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMaxHeight(177.0);
        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(177.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        lbPlayer1.setText("Enter First Player Name");
        lbPlayer1.setFont(new Font("Arial Bold", 20.0));
        GridPane.setMargin(lbPlayer1, new Insets(0.0, 0.0, 25.0, 0.0));

        GridPane.setRowIndex(lbPlayer2, 2);
        lbPlayer2.setText("Enter Second Player Name");
        GridPane.setMargin(lbPlayer2, new Insets(50.0, 0.0, 0.0, 0.0));
        lbPlayer2.setFont(new Font("Arial Bold", 20.0));

        GridPane.setRowIndex(txtPlayer1, 1);
        txtPlayer1.setPrefHeight(41.0);
        txtPlayer1.setPrefWidth(475.0);
           
        GridPane.setRowIndex(txtPlayer2, 3);
        txtPlayer2.setPrefHeight(41.0);
        txtPlayer2.setPrefWidth(475.0);
        
       btnNext.setOnAction(event -> {  
           player1_Name = txtPlayer1.getText();
           player2_Name = txtPlayer2.getText(); 
           
        if (checkSymbol(player1_Name) || checkSymbol(player2_Name) ||player1_Name.isEmpty() || player2_Name.isEmpty()) {
               if(checkSymbol(player1_Name) || checkSymbol(player2_Name)){
                Alert unacceptAlert = new Alert(Alert.AlertType.WARNING);
                unacceptAlert.setTitle("Unvalid Input");
                unacceptAlert.setHeaderText(null);
                unacceptAlert.setContentText("Player names cannot contain #, !, or ?");
                unacceptAlert.showAndWait();
                }            
               else if (player1_Name.isEmpty() || player2_Name.isEmpty()){                
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("Empty Field");
                emptyAlert.setHeaderText(null);
                emptyAlert.setContentText("Please Enter The Players Names.");
                emptyAlert.showAndWait();
                }
        }                              
        else {
               
                Mynav.navigateTo(new LocallGame(player1_Name,player2_Name), event);
            }
        });
              
        btnBack.setOnAction(event -> {  
                Mynav.navigateTo(new ChooseGameUI(), event);
        });
        
        
        BorderPane.setMargin(gridPane0, new Insets(80.0, 75.0, 50.0, 75.0));
        gridPane0.setPadding(new Insets(0.0, 0.0, 25.0, 0.0));
        setCenter(gridPane0);

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getChildren().add(btnBack);
        gridPane.getChildren().add(btnNext);
        gridPane0.getColumnConstraints().add(columnConstraints1);
        gridPane0.getRowConstraints().add(rowConstraints0);
        gridPane0.getRowConstraints().add(rowConstraints1);
        gridPane0.getRowConstraints().add(rowConstraints2);
        gridPane0.getRowConstraints().add(rowConstraints3);
        gridPane0.getChildren().add(lbPlayer1);
        gridPane0.getChildren().add(lbPlayer2);
        gridPane0.getChildren().add(txtPlayer1);
        gridPane0.getChildren().add(txtPlayer2);

    }
    
   private boolean checkSymbol(String playerName) {
        return playerName.contains("#") || playerName.contains("!") || playerName.contains("?");
    }    
}
