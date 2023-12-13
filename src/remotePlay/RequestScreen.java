/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotePlay;

/**
 *
 * @author Orignal Vip
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import mynev.Mynav;


public  class RequestScreen extends AnchorPane {

    protected final FlowPane flowPane;
    protected final Label label;
    protected final TextField namer;
    protected final Button accept;
    protected final Button ignor;

    public RequestScreen() {

        flowPane = new FlowPane();
        label = new Label();
        namer = new TextField();
        accept = new Button();
        ignor = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-radius: 15;");

        flowPane.setLayoutX(90.0);
        flowPane.setLayoutY(14.0);
        flowPane.setPrefHeight(97.0);
        flowPane.setPrefWidth(371.0);

        label.setText("player name");
        label.setFont(new Font("System Bold", 18.0));

        namer.setPrefHeight(38.0);
        namer.setPrefWidth(242.0);
        FlowPane.setMargin(namer, new Insets(0.0));

        accept.setMnemonicParsing(false);
        accept.setPrefHeight(31.0);
        accept.setPrefWidth(150.0);
        accept.setStyle("-fx-background-radius: 15; -fx-background-color: green;");
        accept.setText("accept");
        accept.setTextFill(javafx.scene.paint.Color.valueOf("#f5f0f0"));
        FlowPane.setMargin(accept, new Insets(0.0, 0.0, 0.0, 10.0));
        accept.setFont(new Font(16.0));
        //************
        accept.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){  
                  // Mynav.navigateTo(new Xoivbase(), event);
                 }
        });
                
                
      //******************

        ignor.setMnemonicParsing(false);
        ignor.setPrefHeight(31.0);
        ignor.setPrefWidth(150.0);
        ignor.setStyle("-fx-background-radius: 15; -fx-background-color: red;");
        ignor.setText("ignor");
        ignor.setTextFill(javafx.scene.paint.Color.valueOf("#eeeaea"));
        FlowPane.setMargin(ignor, new Insets(10.0, 0.0, 10.0, 40.0));
        ignor.setFont(new Font(16.0));

        flowPane.getChildren().add(label);
        flowPane.getChildren().add(namer);
        flowPane.getChildren().add(accept);
        flowPane.getChildren().add(ignor);
        getChildren().add(flowPane);

    }
}

