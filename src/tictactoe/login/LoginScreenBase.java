package tictactoe.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dto.DTOPlayer;
import dto.MyPlayer;
import gameBoard.ChooseGameUI;
import gameBoard.GameBoardUI;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import tictactoe.db.DataAccessLayer;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import static javax.management.Query.value;
import mynev.Mynav;
import network.NetWork;
import register.RegisterScreenBase;
import remotePlay.Invite;
//import static sun.audio.AudioPlayer.player;
import tictactoe.AlertMessage;
import tictactoe.TicTacToe;

public class LoginScreenBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final Label login_lbl;
    protected final Label label;
    protected final PasswordField pass_tf;
    protected final Button signin_btn;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final Label have_account_lbl;
    protected final TextField username_tf;
    protected final ImageView imageView1;
    NetWork network;
    public LoginScreenBase(String IP) {
        anchorPane = new AnchorPane();
        login_lbl = new Label();
        label = new Label();
        pass_tf = new PasswordField();
        signin_btn = new Button();
        imageView = new ImageView();
        imageView0 = new ImageView();
        have_account_lbl = new Label();
        username_tf = new TextField();
        imageView1 = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setMaxHeight(321.0);
        anchorPane.setMaxWidth(600.0);
        anchorPane.setMinHeight(321.0);
        anchorPane.setMinWidth(600.0);
        anchorPane.setPrefHeight(321.0);
        anchorPane.setPrefWidth(600.0);
        anchorPane.setStyle("-fx-background-color: transparent;");

        login_lbl.setLayoutX(253.0);
        login_lbl.setLayoutY(22.0);
        login_lbl.setText("Login");
        login_lbl.setFont(new Font("System Bold", 36.0));

        label.setLayoutX(210.0);
        label.setLayoutY(75.0);
        label.setText("Sign in  to your account");
        label.setFont(new Font(18.0));

        AnchorPane.setBottomAnchor(pass_tf, 111.0);
        AnchorPane.setLeftAnchor(pass_tf, 171.0);
        AnchorPane.setRightAnchor(pass_tf, 177.0);
        AnchorPane.setTopAnchor(pass_tf, 175.0);
        pass_tf.setLayoutX(171.0);
        pass_tf.setLayoutY(175.0);
        pass_tf.setPrefHeight(35.0);
        pass_tf.setPrefWidth(252.0);
        pass_tf.setPromptText("Password");
        pass_tf.getStylesheets().add("/tictactoe/cssstyle/textFieldStyle.css");
        pass_tf.setPadding(new Insets(0.0, 0.0, 0.0, 30.0));

        signin_btn.setLayoutX(172.0);
        signin_btn.setLayoutY(224.0);
        signin_btn.setMnemonicParsing(false);
        signin_btn.setPrefHeight(35.0);
        signin_btn.setPrefWidth(250.0);
        signin_btn.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        signin_btn.setText("Sign in");
        signin_btn.setFont(new Font(18.0));
        signin_btn.setOnAction((ActionEvent event) -> {
            Gson gson = new GsonBuilder().create();
            String userName = username_tf.getText();
            String password = pass_tf.getText();
            validateData(userName, password);
            DTOPlayer player = new DTOPlayer(userName,password);
            JsonObject jObject = new JsonObject();
            jObject.addProperty("key", "login");
            jObject.addProperty("username", player.getUserName());
            jObject.addProperty("password", player.getPassword());
            String jString = gson.toJson(jObject);
           network =NetWork.getInstance(IP);
           network.sendMessage(jString);
           network.reciveMessage();
        });

        imageView.setFitHeight(21.0);
        imageView.setFitWidth(17.0);
        imageView.setLayoutX(178.0);
        imageView.setLayoutY(182.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        imageView0.setLayoutX(178.0);
        imageView0.setLayoutY(185.0);
        imageView0.setImage(new Image(getClass().getResource("/tictactoe/media/key.png").toExternalForm()));

        have_account_lbl.setLayoutX(208.0);
        have_account_lbl.setLayoutY(271.0);
        have_account_lbl.setText("Don't have an account?");
        have_account_lbl.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        have_account_lbl.setTextFill(javafx.scene.paint.Color.valueOf("#0e59ef"));
        have_account_lbl.setUnderline(true);
        have_account_lbl.setFont(new Font(18.0));
        have_account_lbl.setOnMouseClicked((event) -> {
            Parent root = new RegisterScreenBase();
            String image = TicTacToe.class.getResource("app.jpg").toExternalForm();
            root.setStyle("-fx-background-image: url('" + image + "'); "
                    + "-fx-background-size: 100% 100%;"
                    + "-fx-background-position: center center;"
            );
            Mynav.navigateTo(root, event);
        });

        username_tf.setLayoutX(170.0);
        username_tf.setLayoutY(130.0);
        username_tf.setPrefHeight(35.0);
        username_tf.setPrefWidth(252.0);
        username_tf.setPromptText("Username");
        username_tf.getStylesheets().add("/tictactoe/cssstyle/textFieldStyle.css");
        username_tf.setPadding(new Insets(0.0, 0.0, 0.0, 30.0));
        
        imageView1.setLayoutX(178.0);
        imageView1.setLayoutY(140.0);
        imageView1.setImage(new Image(getClass().getResource("/tictactoe/media/user.png").toExternalForm()));
        setCenter(anchorPane);

        anchorPane.getChildren().add(login_lbl);
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(pass_tf);
        anchorPane.getChildren().add(signin_btn);
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(have_account_lbl);
        anchorPane.getChildren().add(username_tf);
        anchorPane.getChildren().add(imageView1);

    }

    private void validateData(String userName, String password) {
        if (userName.isEmpty() || password.isEmpty()) {

            AlertMessage.showAlert(Alert.AlertType.ERROR, signin_btn.getScene().getWindow(), "Form Error!",
                    "Please fill up the form properly");
            return;
        }

    }
    private void alert(String data)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText(data);
                alert.showAndWait();
    }
}
