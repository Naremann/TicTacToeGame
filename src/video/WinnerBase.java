package video;

import gameBoard.ChooseGameUI;
import gameBoard.GameBoardUI;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import mynev.Mynav;

public class WinnerBase extends BorderPane {

    protected final MediaView mediavv;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final Button btnhome;

    public WinnerBase() {

        mediavv = new MediaView();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        btnhome = new Button();

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

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setPrefHeight(42.0);
        gridPane.setPrefWidth(600.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(549.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(531.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(292.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(69.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setColumnIndex(btnhome, 1);
        btnhome.setMnemonicParsing(false);
        btnhome.setText("Back to Game");
        setBottom(gridPane);
        

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getChildren().add(btnhome);
        
        

        try {
            String resourcePath = "/video/Winner.fxml";
           URL location = getClass().getResource(resourcePath);
           FXMLLoader fxmlLoader = new FXMLLoader(location);
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
        
        btnhome.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){  
                   Mynav.navigateTo(new GameBoardUI(), event);
                    mediaPlayer.stop();
                 }
        });
    }
}
