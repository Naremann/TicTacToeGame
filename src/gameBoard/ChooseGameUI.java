/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;
import gameBoard.PlayerNamesUI;
import homePage.XOgameUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import mynev.Mynav;
import register.RegisterScreenBase;
import tictactoe.login.LoginScreenBase;

/**
 *
 * @author HimaMarey
 */
public class ChooseGameUI extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final Button btnBack;
    protected final Button btnPlayLocal;
    protected final Button btnPlayOnline;
    protected final Label lbPlayLocal;
    protected final Label lbPlayOnline;
    

    public ChooseGameUI() {

        anchorPane = new AnchorPane();
        btnBack = new Button();
        btnPlayLocal = new Button();
        btnPlayOnline = new Button();
        lbPlayLocal = new Label();
        lbPlayOnline = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        
        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);
        
        btnBack.setLayoutX(200.0);
        btnBack.setLayoutY(300.0);
        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(50.0);
        btnBack.setPrefWidth(200.0);
        btnBack.setStyle( "-fx-text-fill: white;"+
                            "-fx-background-radius: 15;");
        btnBack.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        btnBack.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        btnBack.setText("BACK");
        btnBack.setFont(new Font("Arial Bold", 20.0));
        btnBack.addEventHandler(ActionEvent.ACTION, (event) -> {
            Mynav.navigateTo(new XOgameUI(),event);
        });
        
        lbPlayLocal.setLayoutX(80.0);
        lbPlayLocal.setLayoutY(50.0);
        lbPlayLocal.setText("PLAY LOCALLY");
        lbPlayLocal.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        lbPlayLocal.setStyle("-fx-text-fill: black;");

        
        btnPlayLocal.setLayoutX(47.0);
        btnPlayLocal.setLayoutY(75.0);
        btnPlayLocal.setMnemonicParsing(false);
        btnPlayLocal.setPrefHeight(186.0);
        btnPlayLocal.setPrefWidth(225.0);
       String imbtcomp = ChooseGameUI.class.getResource("plocal.png").toExternalForm();
        btnPlayLocal.setStyle("-fx-background-image: url('" + imbtcomp + "'); "+
                 "-fx-background-size: 90% 90%;"+
                "-fx-background-repeat: no-repeat;"+
                 "-fx-background-position: center center;"+
                "-fx-background-radius: 20;");
        //**********
        btnPlayLocal.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){  
                   Mynav.navigateTo(new PlayerNamesUI(), event);
                 }
        });   
               
        //************
       
        
        lbPlayOnline.setLayoutX(385.0);
        lbPlayOnline.setLayoutY(50.0);
        lbPlayOnline.setText("PLAY ONILNE");
        lbPlayOnline.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        lbPlayOnline.setStyle("-fx-text-fill: black;");
        setCenter(anchorPane);
        
        btnPlayOnline.setLayoutX(339.0);
        btnPlayOnline.setLayoutY(75.0);
        btnPlayOnline.setMnemonicParsing(false);
        btnPlayOnline.setPrefHeight(185.0);
        btnPlayOnline.setPrefWidth(225.0);
        String imbtFriend = ChooseGameUI.class.getResource("online-gaming.png").toExternalForm();
        btnPlayOnline.setStyle("-fx-background-image: url('" + imbtFriend + "'); "+
                 "-fx-background-size: 90% 90%;"+
                "-fx-background-repeat: no-repeat;"+
                 "-fx-background-position: center center;"+
                "-fx-background-radius: 20;");
        //*****************
         btnPlayOnline.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){
                  // GameBoardUI gameBoard = new GameBoardUI(true);
                   Mynav.navigateTo(new TackIP(), event);
                 }
        });
        
        //***************
                   
        anchorPane.getChildren().add(btnBack);
        anchorPane.getChildren().add(btnPlayLocal);
        anchorPane.getChildren().add(btnPlayOnline);
        anchorPane.getChildren().add(lbPlayLocal);
        anchorPane.getChildren().add(lbPlayOnline);

    }
}
