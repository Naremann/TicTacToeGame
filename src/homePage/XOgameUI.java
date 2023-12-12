package homePage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public  class XOgameUI extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final TextField txtName;
    protected final TextField txtScore;
    protected final Button btnRequest;
    protected final Button btnHistory;
    protected final Button btnwFriend;
    protected final Button btnwComp;
    protected final Label lbPlayWFriend;
    protected final Label lbwComp;

    public XOgameUI() {

        anchorPane = new AnchorPane();
        txtName = new TextField();
        txtScore = new TextField();
        btnRequest = new Button();
        btnHistory = new Button();
        btnwFriend = new Button();
        btnwComp = new Button();
        lbPlayWFriend = new Label();
        lbwComp = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);
        
        String imbtcomp = XOgameUI.class.getResource("wComp.png").toExternalForm();
        btnwComp.setStyle("-fx-background-image: url('" + imbtcomp + "'); "+
                 "-fx-background-size: 90% 90%;"+
                "-fx-background-repeat: no-repeat;"+
                 "-fx-background-position: center center;");
        
        String imbtFriend = XOgameUI.class.getResource("wFriend.png").toExternalForm();
        btnwFriend.setStyle("-fx-background-image: url('" + imbtFriend + "'); "+
                 "-fx-background-size: 90% 90%;"+
                "-fx-background-repeat: no-repeat;"+
                 "-fx-background-position: center center;");

        txtName.setEditable(false);
        txtName.setLayoutX(31.0);
        txtName.setLayoutY(14.0);
        txtName.setFont(new Font("Arial Bold", 18.0));

        txtScore.setEditable(false);
        txtScore.setLayoutX(31.0);
        txtScore.setLayoutY(56.0);
        txtScore.setPrefHeight(33.0);
        txtScore.setPrefWidth(224.0);
        txtScore.setText("SCORE:");
        txtScore.setFont(new Font("Arial Bold", 18.0));

        btnRequest.setLayoutX(386.0);
        btnRequest.setLayoutY(14.0);
        btnRequest.setMnemonicParsing(false);
        btnRequest.setPrefHeight(33.0);
        btnRequest.setPrefWidth(179.0);
        btnRequest.setStyle("-fx-background-color: gray;");
        btnRequest.setText("REQUESTS");
        btnRequest.setFont(new Font("Arial Bold", 18.0));

        btnHistory.setLayoutX(386.0);
        btnHistory.setLayoutY(56.0);
        btnHistory.setMnemonicParsing(false);
        btnHistory.setPrefHeight(33.0);
        btnHistory.setPrefWidth(179.0);
        btnHistory.setStyle("-fx-background-color: gray;");
        btnHistory.setText("SHOW RECORD");
        btnHistory.setFont(new Font("Arial Bold", 18.0));

        btnwFriend.setLayoutX(47.0);
        btnwFriend.setLayoutY(128.0);
        btnwFriend.setMnemonicParsing(false);
        btnwFriend.setPrefHeight(186.0);
        btnwFriend.setPrefWidth(217.0);

        btnwComp.setLayoutX(339.0);
        btnwComp.setLayoutY(129.0);
        btnwComp.setMnemonicParsing(false);
        btnwComp.setPrefHeight(185.0);
        btnwComp.setPrefWidth(216.0);

        lbPlayWFriend.setLayoutX(70.0);
        lbPlayWFriend.setLayoutY(325.0);
        lbPlayWFriend.setText("MULTI PLAYER MODE");
        lbPlayWFriend.setFont(new Font("Arial Bold", 18.0));

        lbwComp.setLayoutX(381.0);
        lbwComp.setLayoutY(325.0);
        lbwComp.setText("SINGLE MODE");
        lbwComp.setFont(new Font("Arial Bold", 18.0));
        setCenter(anchorPane);

        anchorPane.getChildren().add(txtName);
        anchorPane.getChildren().add(txtScore);
        anchorPane.getChildren().add(btnRequest);
        anchorPane.getChildren().add(btnHistory);
        anchorPane.getChildren().add(btnwFriend);
        anchorPane.getChildren().add(btnwComp);
        anchorPane.getChildren().add(lbPlayWFriend);
        anchorPane.getChildren().add(lbwComp);

    }
}
