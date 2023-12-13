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
/*public class Invite {
    
}*/
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
//import xo.Xorequest;

public  class Invite extends AnchorPane {

    protected final FlowPane flowPane;
    protected final Label label;
    protected final TextField textField;
    protected final Button invite;

    public Invite() {

        flowPane = new FlowPane();
        label = new Label();
        textField = new TextField();
        invite = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        flowPane.setLayoutX(63.0);
        flowPane.setLayoutY(14.0);
        flowPane.setPrefHeight(51.0);
        flowPane.setPrefWidth(474.0);

        label.setText("player name");
        label.setFont(new Font("System Bold", 16.0));

        textField.setPrefHeight(37.0);
        textField.setPrefWidth(219.0);
        FlowPane.setMargin(textField, new Insets(0.0, 0.0, 0.0, 3.0));

        invite.setMnemonicParsing(false);
        invite.setPrefHeight(25.0);
        invite.setPrefWidth(80.0);
        invite.setStyle("-fx-background-radius: 20; -fx-background-color: green;");
        invite.setText("invite");
        invite.setTextFill(javafx.scene.paint.Color.WHITE);
        FlowPane.setMargin(invite, new Insets(0.0, 0.0, 0.0, 20.0));
        invite.setFont(new Font(16.0));
        //************
        invite.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){  
                 //  Mynav.navigateTo(new Xorequest(), event);
                 }
        });
                
                
      //******************
        flowPane.getChildren().add(label);
        flowPane.getChildren().add(textField);
        flowPane.getChildren().add(invite);
        getChildren().add(flowPane);

    }
}

