/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import gameBoard.ChooseGameUI;
import gameBoard.DisplayRecord;
import gameBoard.GameBoardUI;
import homePage.XOgameUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import recordTable.RecordList;
import register.RegisterScreenBase;
import tictactoe.gamelevels.GameLevelsBase;
import tictactoe.login.LoginScreenBase;


public class TicTacToe extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new XOgameUI();

        String image = TicTacToe.class.getResource("app.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); "+
                 "-fx-background-size: 100% 100%;"+
                 "-fx-background-position: center center;"
                );
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}