package tictactoe.gamelevels;

import gameBoard.GameBoardUI;
import gameBoard.HardLevelWithPc;
import gameBoard.PlayWithPc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import mynev.Mynav;

public  class GameLevelsBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final Button high_btn;
    protected final Label level_lbl;
    protected final Button med_btn;
    protected final Button low_btn;
    
   // public GameLevelsBase(){}

    public GameLevelsBase(String player1_Name) {

        anchorPane = new AnchorPane();
        high_btn = new Button();
        level_lbl = new Label();
        med_btn = new Button();
        low_btn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setMaxHeight(420.0);
        anchorPane.setMaxWidth(600.0);
        anchorPane.setMinHeight(420.0);
        anchorPane.setMinWidth(600.0);
        anchorPane.setPrefHeight(420.0);
        anchorPane.setPrefWidth(600.0);

        high_btn.setLayoutX(14.0);
        high_btn.setLayoutY(88.0);
        high_btn.setMnemonicParsing(false);
        high_btn.setPrefHeight(51.0);
        high_btn.setPrefWidth(270.0);
        high_btn.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        high_btn.setText("High Level");
        high_btn.setTextFill(javafx.scene.paint.Color.WHITE);
        //*****************
          high_btn.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){
                   Mynav.navigateTo(new HardLevelWithPc(player1_Name), event);
                 }
        });
        //*******************
        high_btn.setFont(new Font(24.0));

        level_lbl.setLayoutX(223.0);
        level_lbl.setLayoutY(23.0);
        level_lbl.setPrefHeight(35.0);
        level_lbl.setPrefWidth(183.0);
        level_lbl.setText("Game Levels");
        level_lbl.setFont(new Font("System Bold", 24.0));

        med_btn.setLayoutX(172.0);
        med_btn.setLayoutY(162.0);
        med_btn.setMnemonicParsing(false);
        med_btn.setPrefHeight(51.0);
        med_btn.setPrefWidth(270.0);
        med_btn.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        med_btn.setText("Medium Level");
        med_btn.setTextFill(javafx.scene.paint.Color.WHITE);
         //*****************
          med_btn.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){
                  // GameBoardUI gameBoard = new GameBoardUI(true);
                  // Mynav.navigateTo(new GameBoardUI(true), event);
                 }
        });
        //*******************
        med_btn.setFont(new Font(24.0));

        low_btn.setLayoutX(307.0);
        low_btn.setLayoutY(245.0);
        low_btn.setMnemonicParsing(false);
        low_btn.setPrefHeight(51.0);
        low_btn.setPrefWidth(270.0);
        low_btn.setText("Easy Level");
        low_btn.setTextFill(javafx.scene.paint.Color.WHITE);
        low_btn.setFont(new Font(24.0));
        low_btn.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        //*****************
         low_btn.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){
                   Mynav.navigateTo(new PlayWithPc(player1_Name), event);
                 }
        });
        //*******************
        setCenter(anchorPane);

        anchorPane.getChildren().add(high_btn);
        anchorPane.getChildren().add(level_lbl);
        anchorPane.getChildren().add(med_btn);
        anchorPane.getChildren().add(low_btn);

    }


}
