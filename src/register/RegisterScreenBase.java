package register;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import mynev.Mynav;
import tictactoe.AlertMessage;
import tictactoe.TicTacToe;
import dto.DTOPlayer;
import gameBoard.TackIP;
import network.NetWork;
import tictactoe.login.LoginScreenBase;

public class RegisterScreenBase extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final Label regisster_lbl;
    protected final Label fill_lbl;
    protected final TextField username_tf;
    protected final ImageView user_ic;
    protected final TextField email_tf;
    protected final ImageView email_ic;
    protected final TextField password_tf;
    protected final ImageView pass_ic;
    protected final Button signup_btn;
    protected final Label have_account_lbl;

    NetWork network;

    public RegisterScreenBase() {

        System.out.println("register");

        anchorPane = new AnchorPane();
        regisster_lbl = new Label();
        fill_lbl = new Label();
        username_tf = new TextField();
        user_ic = new ImageView();
        email_tf = new TextField();
        email_ic = new ImageView();
        password_tf = new TextField();
        pass_ic = new ImageView();
        signup_btn = new Button();
        have_account_lbl = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setMaxHeight(400.0);
        anchorPane.setMaxWidth(600.0);
        anchorPane.setMinHeight(321.0);
        anchorPane.setMinWidth(600.0);
        anchorPane.setPrefHeight(400.0);
        anchorPane.setPrefWidth(600.0);

        regisster_lbl.setLayoutX(228.0);
        regisster_lbl.setLayoutY(6.0);
        regisster_lbl.setText("Register");
        regisster_lbl.setFont(new Font(36.0));

        fill_lbl.setLayoutX(173.0);
        fill_lbl.setLayoutY(67.0);
        fill_lbl.setText("Kindly fill in this form to register");
        fill_lbl.setFont(new Font(18.0));

        username_tf.setLayoutX(182.0);
        username_tf.setLayoutY(116.0);
        username_tf.setPrefHeight(35.0);
        username_tf.setPrefWidth(252.0);
        username_tf.setPromptText("Username");
        username_tf.getStylesheets().add("/tictactoe/cssstyle/textFieldStyle.css");
        username_tf.setPadding(new Insets(0.0, 0.0, 0.0, 30.0));

        user_ic.setLayoutX(192.0);
        user_ic.setLayoutY(126.0);
        user_ic.setImage(new Image(getClass().getResource("/tictactoe/media/user.png").toExternalForm()));

        email_tf.setLayoutX(182.0);
        email_tf.setLayoutY(165.0);
        email_tf.setPrefHeight(35.0);
        email_tf.setPrefWidth(252.0);
        email_tf.setPromptText("Email");
        email_tf.getStylesheets().add("/tictactoe/cssstyle/textFieldStyle.css");
        email_tf.setPadding(new Insets(0.0, 0.0, 0.0, 30.0));

        email_ic.setLayoutX(191.0);
        email_ic.setLayoutY(175.0);
        email_ic.setImage(new Image(getClass().getResource("/tictactoe/media/email.png").toExternalForm()));

        password_tf.setLayoutX(182.0);
        password_tf.setLayoutY(215.0);
        password_tf.setPrefHeight(35.0);
        password_tf.setPrefWidth(252.0);
        password_tf.setPromptText("Password");
        password_tf.getStylesheets().add("/tictactoe/cssstyle/textFieldStyle.css");
        password_tf.setPadding(new Insets(0.0, 0.0, 0.0, 30.0));

        pass_ic.setLayoutX(191.0);
        pass_ic.setLayoutY(225.0);
        pass_ic.setImage(new Image(getClass().getResource("/tictactoe/media/key.png").toExternalForm()));

        signup_btn.setLayoutX(183.0);
        signup_btn.setLayoutY(275.0);
        signup_btn.setMnemonicParsing(false);
        signup_btn.setPrefHeight(35.0);
        signup_btn.setPrefWidth(250.0);
        signup_btn.getStylesheets().add("/tictactoe/cssstyle/btnStyle.css");
        signup_btn.setText("Create an account");
        signup_btn.setFont(new Font(18.0));
        signup_btn.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {

            String userName = username_tf.getText();
            String email = email_tf.getText();
            String password = password_tf.getText();
            if (validateData(userName, email, password)) {
                System.out.println((validateData(userName, email, password)));
                Gson gson = new GsonBuilder().create();
                DTOPlayer player = new DTOPlayer(userName, email, password);
                JsonObject jObject = new JsonObject();
                jObject.addProperty("key", "register");
                jObject.addProperty("username", player.getUserName());
                jObject.addProperty("email", player.getEmail());
                jObject.addProperty("password", player.getPassword());
                String jString = gson.toJson(jObject);
                network = new NetWork(TackIP.IPAddress);
                network.sendMessage(jString);
                network.reciveMessage();
            }

        });

        have_account_lbl.setLayoutX(194.0);
        have_account_lbl.setLayoutY(328.0);
        have_account_lbl.setText("You already have an account");
        have_account_lbl.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        have_account_lbl.setTextFill(javafx.scene.paint.Color.valueOf("#0e59ef"));
        have_account_lbl.setUnderline(true);
        have_account_lbl.setFont(new Font(18.0));
        have_account_lbl.setOnMouseClicked((event) -> {
            navigateToLoginScreen(event);
        });
        setCenter(anchorPane);

        anchorPane.getChildren().add(regisster_lbl);
        anchorPane.getChildren().add(fill_lbl);
        anchorPane.getChildren().add(username_tf);
        anchorPane.getChildren().add(user_ic);
        anchorPane.getChildren().add(email_tf);
        anchorPane.getChildren().add(email_ic);
        anchorPane.getChildren().add(password_tf);
        anchorPane.getChildren().add(pass_ic);
        anchorPane.getChildren().add(signup_btn);
        anchorPane.getChildren().add(have_account_lbl);

    }

    public void navigateToLoginScreen(Event event) {
        Parent root = new LoginScreenBase("");
        String image = TicTacToe.class.getResource("app.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-size: 100% 100%;"
                + "-fx-background-position: center center;"
        );
        Mynav.navigateTo(root, event);
    }

    private boolean validateData(String userName, String email, String password) {
        boolean result = true;
        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            AlertMessage.showAlert(Alert.AlertType.ERROR, signup_btn.getScene().getWindow(), "Form Error!",
                    "Please fill up the form properly");
            result = false;
        }
        return result;

    }
}
