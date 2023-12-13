/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homePage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gameBoard.GameBoardUI;
import gameBoard.ChooseGameUI;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


/**
 *
 * @author DELL
 */
public class XoGame extends Application {
    private boolean playWithComputer;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new GameBoardUI();
        String image = XOgameUI.class.getResource("bkdnd.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); "+
                 "-fx-background-size: 100% 100%;"+
                 "-fx-background-position: center center;");
        
        
         Scene gameScene;
        if (playWithComputer) {
            GameBoardUI gameBoard = new GameBoardUI(true); // true indicates playing with the computer
            gameScene = new Scene(gameBoard, 600, 400);
        } 
        else {
            GameBoardUI gameBoard = new GameBoardUI(); // Example, adjust based on your actual class
            gameScene = new Scene(gameBoard, 600, 400);
        }
        Scene scene = new Scene(root);
        stage.setScene(gameScene);
        stage.setTitle("Game Board");
        stage.show();

        // Close the player choice window
       // Stage primaryStage = (Stage) playWithComputer.getScene().getWindow();
       // primaryStage.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
