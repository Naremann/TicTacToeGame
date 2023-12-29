package homePage;

import gameBoard.ChooseGameUI;
import gameBoard.GameBoardUI;
import gameBoard.PNameSingleUI;
import gameBoard.PlayerNamesUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import mynev.Mynav;
import recordTable.RecordList;
import remotePlay.ListviewRequestBase;
import remotePlay.RequestScreen;
import tictactoe.gamelevels.GameLevelsBase;

public  class XOgameUI extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final Button btnHistory;
    protected final Button btnwFriend;
    protected final Button btnwComp;
    protected final Label lbPlayWFriend;
    protected final Label lbwComp;

    public XOgameUI() {

        anchorPane = new AnchorPane();
        btnHistory = new Button();
        btnwFriend = new Button();
        btnwComp = new Button();
        lbPlayWFriend = new Label();
        lbwComp = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        
        

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);
        
        String imbtcomp = XOgameUI.class.getResource("pwpc.png").toExternalForm();
       
        btnHistory.setLayoutX(200.0);
        btnHistory.setLayoutY(300.0);
        btnHistory.setMnemonicParsing(false);
        btnHistory.setPrefHeight(50.0);
        btnHistory.setPrefWidth(200.0);
        btnHistory.setStyle( "-fx-text-fill: white;"+
                            "-fx-background-radius: 15;");
        btnHistory.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        btnHistory.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        btnHistory.setText("SHOW RECORDS");
        btnHistory.setFont(new Font("Arial Bold", 20.0));
        btnHistory.addEventHandler(ActionEvent.ACTION, (event) -> {
            Mynav.navigateTo(new RecordList(),event);
        });

        btnwFriend.setLayoutX(47.0);
        btnwFriend.setLayoutY(75.0);
        btnwFriend.setMnemonicParsing(false);
        btnwFriend.setPrefHeight(186.0);
        btnwFriend.setPrefWidth(225.0);
        btnwComp.setStyle("-fx-background-image: url('" + imbtcomp + "'); "+
                 "-fx-background-size: 90% 90%;"+
                "-fx-background-repeat: no-repeat;"+
                 "-fx-background-position: center center;"+
                "-fx-background-radius: 20;");
        //**********
        btnwFriend.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){  
                   Mynav.navigateTo(new ChooseGameUI(), event);
                 }
        });   
        
        
        //************
        btnwComp.setLayoutX(339.0);
        btnwComp.setLayoutY(75.0);
        btnwComp.setMnemonicParsing(false);
        btnwComp.setPrefHeight(185.0);
        btnwComp.setPrefWidth(225.0);
        String imbtFriend = XOgameUI.class.getResource("versus.png").toExternalForm();
        btnwFriend.setStyle("-fx-background-image: url('" + imbtFriend + "'); "+
                 "-fx-background-size: 90% 90%;"+
                "-fx-background-repeat: no-repeat;"+
                 "-fx-background-position: center center;"+
                "-fx-background-radius: 20;");
        //*****************
         btnwComp.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){
                  // GameBoardUI gameBoard = new GameBoardUI(true);
                   Mynav.navigateTo(new PNameSingleUI(), event);
                 }
        });
        //*******************
        

        lbPlayWFriend.setLayoutX(50.0);
        lbPlayWFriend.setLayoutY(50.0);
        lbPlayWFriend.setText("MULTI PLAYER MODE");
        lbPlayWFriend.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        lbPlayWFriend.setStyle("-fx-text-fill: black;");
        
        

        lbwComp.setLayoutX(335.0);
        lbwComp.setLayoutY(50.0);
        lbwComp.setText("SINGLE PLAYER MODE");
        lbwComp.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        lbwComp.setStyle("-fx-text-fill: black;");
        setCenter(anchorPane);

        anchorPane.getChildren().add(btnHistory);
        anchorPane.getChildren().add(btnwFriend);
        anchorPane.getChildren().add(btnwComp);
        anchorPane.getChildren().add(lbPlayWFriend);
        anchorPane.getChildren().add(lbwComp);

    
    }
}
