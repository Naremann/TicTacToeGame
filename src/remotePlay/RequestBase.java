package remotePlay;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public  class RequestBase extends AnchorPane {

    protected final Button btnAccept;
    protected final Button ignor;
    protected final Label labelName;

    public RequestBase() {

        btnAccept = new Button();
        ignor = new Button();
        labelName = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(105.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-radius: 15;");

        btnAccept.setLayoutX(232.0);
        btnAccept.setLayoutY(38.0);
        btnAccept.setMnemonicParsing(false);
        btnAccept.setPrefHeight(31.0);
        btnAccept.setPrefWidth(150.0);
        btnAccept.setStyle("-fx-background-radius: 15; -fx-background-color: green;");
        btnAccept.setText("accept");
        btnAccept.setTextFill(javafx.scene.paint.Color.valueOf("#f5f0f0"));
        btnAccept.setFont(new Font(18.0));

        ignor.setLayoutX(436.0);
        ignor.setLayoutY(38.0);
        ignor.setMnemonicParsing(false);
        ignor.setPrefHeight(31.0);
        ignor.setPrefWidth(150.0);
        ignor.setStyle("-fx-background-radius: 15; -fx-background-color: red;");
        ignor.setText("ignor");
        ignor.setTextFill(javafx.scene.paint.Color.valueOf("#eeeaea"));
        ignor.setFont(new Font(18.0));

        labelName.setLayoutX(33.0);
        labelName.setLayoutY(44.0);
        labelName.setPrefHeight(27.0);
        labelName.setPrefWidth(165.0);
        labelName.setText("    player name");
        labelName.setFont(new Font("System Bold", 18.0));

        getChildren().add(btnAccept);
        getChildren().add(ignor);
        getChildren().add(labelName);

    }
}
