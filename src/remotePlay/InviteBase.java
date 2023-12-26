package remotePlay;

import gameBoard.TackIP;
import homePage.XOgameUI;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import mynev.Mynav;

public  class InviteBase extends AnchorPane {

    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Button btninvite;

    public InviteBase() {

        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        btninvite = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(68.0);
        setPrefWidth(600.0);

        label1.setLayoutX(37.0);
        label1.setLayoutY(21.0);
        label1.setPrefHeight(17.0);
        label1.setPrefWidth(86.0);
        label1.setText("Label");
        label1.setFont(new Font(18.0));

        label2.setLayoutX(198.0);
        label2.setLayoutY(21.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(86.0);
        label2.setText("Label");
        label2.setFont(new Font(18.0));

        label3.setLayoutX(384.0);
        label3.setLayoutY(21.0);
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(76.0);
        label3.setText("Label");
        label3.setFont(new Font(18.0));

        btninvite.setLayoutX(494.0);
        btninvite.setLayoutY(15.0);
        btninvite.setMnemonicParsing(false);
        btninvite.setStyle("-fx-background-color: orange;");
        btninvite.setText("invite");
        btninvite.setTextFill(javafx.scene.paint.Color.valueOf("#fffdfd"));
        btninvite.setFont(new Font(18.0));
        
        
        
        
        

        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(btninvite);

    }
}
