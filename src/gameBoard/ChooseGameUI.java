/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.Font;
/**
 *
 * @author HimaMarey
 */
public class ChooseGameUI extends AnchorPane {

    protected final FlowPane flowPane;
    protected final Label label;
    protected final Button locallyBtn;
    protected final FlowPane flowPane0;
    protected final Label label0;
    protected final Button onlineBtn;

    public ChooseGameUI() {

        flowPane = new FlowPane();
        label = new Label();
        locallyBtn = new Button();
        flowPane0 = new FlowPane();
        label0 = new Label();
        onlineBtn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        flowPane.setLayoutX(42.0);
        flowPane.setLayoutY(75.0);
        flowPane.setPrefHeight(250.0);
        flowPane.setPrefWidth(250.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setPrefHeight(52.0);
        label.setPrefWidth(251.0);
        label.setText("Play Locally");
        label.setFont(new Font("System Bold", 18.0));

        locallyBtn.setMnemonicParsing(false);
        locallyBtn.setPrefHeight(174.0);
        locallyBtn.setPrefWidth(223.0);
        FlowPane.setMargin(locallyBtn, new Insets(12.0));
        String image = ChooseGameUI.class.getResource("locally.png").toExternalForm();
        locallyBtn.setStyle("-fx-background-image: url('" + image + "'); "+
                 "-fx-background-size: 100% 100%;"+
                 "-fx-background-position: center center;"
                );

        flowPane0.setLayoutX(309.0);
        flowPane0.setLayoutY(75.0);
        flowPane0.setPrefHeight(250.0);
        flowPane0.setPrefWidth(250.0);

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setPrefHeight(54.0);
        label0.setPrefWidth(253.0);
        label0.setText("Online Game");
        label0.setFont(new Font("System Bold", 18.0));

        onlineBtn.setMnemonicParsing(false);
        onlineBtn.setPrefHeight(174.0);
        onlineBtn.setPrefWidth(223.0);
        FlowPane.setMargin(onlineBtn, new Insets(12.0));

        String img = ChooseGameUI.class.getResource("online.png").toExternalForm();
        onlineBtn.setStyle("-fx-background-image: url('" + img + "'); "+
                 "-fx-background-size: 100% 100%;"+
                 "-fx-background-position: center center;"
                );
        
        flowPane.getChildren().add(label);
        flowPane.getChildren().add(locallyBtn);
        getChildren().add(flowPane);
        flowPane0.getChildren().add(label0);
        flowPane0.getChildren().add(onlineBtn);
        getChildren().add(flowPane0);

    }
}

