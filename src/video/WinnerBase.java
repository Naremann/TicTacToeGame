package video;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public  class WinnerBase extends BorderPane {

    protected MediaView mediavv;

    public WinnerBase() {

        mediavv = new MediaView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(mediavv, javafx.geometry.Pos.CENTER);
        mediavv.setFitHeight(350.0);
        mediavv.setFitWidth(450.0);
        setCenter(mediavv);
       
        
        
        /////////////////////////////
        String resourcePath = "/video/Winner.fxml";
        URL location = getClass().getResource(resourcePath);
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(WinnerBase.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        System.out.println("WinnerController class loaded.");
        String videoPath = "/video/win.mp4";
        Media media = new Media(getClass().getResource(videoPath).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediavv.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        //////////////////////////////////

    }
}
