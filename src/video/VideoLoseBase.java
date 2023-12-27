package video;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public  class VideoLoseBase extends BorderPane {

    protected final MediaView mediavv;

    public VideoLoseBase() {

        mediavv = new MediaView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(mediavv, javafx.geometry.Pos.CENTER);
        mediavv.setFitHeight(400.0);
        mediavv.setFitWidth(600.0);
        setCenter(mediavv);
        /////////////////////////////
        String resourcePath = "/videolose/FXMLDocument.fxml";
        URL location = getClass().getResource(resourcePath);
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(VideoLoseBase.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        System.out.println("FXMLDocumentController class loaded.");
        String videoPath = "/videolose/win.mp4";
        Media media = new Media(getClass().getResource(videoPath).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediavv.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        //////////////////////////////////


    }
     public void showWinnerAlert() {
        // Create a media player
        MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("winner_video.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);

        // Create a stack pane to hold the media view
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(mediaView);

        // Create an alert with a custom dialog pane
        Alert winnerAlert = new Alert(Alert.AlertType.NONE);
        winnerAlert.setTitle("Congratulations!");
        winnerAlert.setHeaderText(null);
        winnerAlert.getDialogPane().setContent(stackPane);

        // Play the video when the alert is shown
        winnerAlert.setOnShown(event -> player.play());

        // Show the alert
        winnerAlert.showAndWait();

        // Stop the video when the alert is closed
        winnerAlert.setOnCloseRequest(event -> player.stop());
    }
}
