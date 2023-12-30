/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Window;

/**
 *
 * @author user
 */
public class AlertMessage {

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void showWinAlert() throws InterruptedException {
        MediaPlayer player = new MediaPlayer(new Media(new Object().getClass().getResource("/tictactoe/media/win.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("You won");
        alert.setHeaderText("");

        VBox content = new VBox(20, mediaView);
        content.setMinSize(700, 400);

        content.setAlignment(Pos.CENTER);
        alert.getDialogPane().setContent(content);

        alert.setOnShowing(e -> player.play());
        alert.showAndWait();
        
       
       
        
    }

}
