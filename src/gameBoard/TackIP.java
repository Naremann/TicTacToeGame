
package gameBoard;

import dto.MyPlayer;
import homePage.XOgameUI;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import mynev.Mynav;
import tictactoe.login.LoginScreenBase;

public class TackIP extends BorderPane{
    protected GridPane gridPane;
    protected ColumnConstraints columnConstraints;
    protected ColumnConstraints columnConstraints0;
    protected RowConstraints rowConstraints;
    protected Button btnBack;
    protected Button btnNext;
    protected GridPane gridPane0;
    protected ColumnConstraints columnConstraints1;
    protected RowConstraints rowConstraints0;
    protected RowConstraints rowConstraints1;
    protected RowConstraints rowConstraints2;
    protected RowConstraints rowConstraints3;
    protected Label lbPlayer1;
    public TextField IPField;
    public static String IPAddress ;
 

    public TackIP() {

        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        btnBack = new Button();
        btnNext = new Button();
        gridPane0 = new GridPane();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        lbPlayer1 = new Label();
       
        IPField = new TextField();
        

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(30.0);
        btnBack.setPrefWidth(90.0);
        btnBack.setStyle("-fx-background-color: gray;");
        btnBack.setText("BACK");
        GridPane.setMargin(btnBack, new Insets(0.0, 0.0, 30.0, 100.0));
        btnBack.setFont(new Font("Arial Bold", 18.0));
        btnBack.setOnAction(e->{
            Mynav.navigateTo(new ChooseGameUI(), e);
        });
        GridPane.setColumnIndex(btnNext, 1);
        btnNext.setMnemonicParsing(false);
        btnNext.setPrefHeight(33.0);
        btnNext.setPrefWidth(91.0);
        btnNext.setStyle("-fx-background-color: gray;");
        btnNext.setText("NEXT");
        btnNext.setFont(new Font("Arial Bold", 18.0));
        GridPane.setMargin(btnNext, new Insets(0.0, 0.0, 30.0, 95.0));
        BorderPane.setMargin(gridPane, new Insets(0.0, 0.0, 40.0, 0.0));
        setBottom(gridPane);

        BorderPane.setAlignment(gridPane0, javafx.geometry.Pos.CENTER);
        gridPane0.setPrefHeight(313.0);
        gridPane0.setPrefWidth(594.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints0.setMaxHeight(88.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(51.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(174.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(87.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMaxHeight(146.0);
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(47.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMaxHeight(177.0);
        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(177.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        lbPlayer1.setText("Pealse Enter Server IP");
        lbPlayer1.setFont(new Font("Arial Bold", 33.0));
        GridPane.setHalignment(lbPlayer1, javafx.geometry.HPos.CENTER);
        GridPane.setMargin(lbPlayer1, new Insets(100.0, 0.0, 25.0, 0.0));

        GridPane.setRowIndex(IPField, 1);
        IPField.setPrefHeight(40.0);
        IPField.setMaxHeight(70);  
        IPField.setPrefWidth(475.0);
        IPField.setStyle("-fx-font-weight: bold;");
        GridPane.setHalignment(IPField, javafx.geometry.HPos.CENTER);
        GridPane.setMargin(IPField, new Insets(150.0, 0.0, 25.0, 0.0)); 
        
       btnNext.setOnAction(event -> {  
           IPAddress = IPField.getText();
            
        if (IPAddress.isEmpty()) {
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Your Server IP Address.");
                alert.showAndWait();
            } else {
                MyPlayer.serverIP=IPField.getText();
                Mynav.navigateTo(new LoginScreenBase(IPAddress), event);
            }
        });
        
        BorderPane.setMargin(gridPane0, new Insets(80.0, 75.0, 50.0, 75.0));
        gridPane0.setPadding(new Insets(0.0, 0.0, 25.0, 0.0));
        setCenter(gridPane0);

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getChildren().add(btnBack);
        gridPane.getChildren().add(btnNext);
        gridPane0.getColumnConstraints().add(columnConstraints1);
        gridPane0.getRowConstraints().add(rowConstraints0);
        gridPane0.getRowConstraints().add(rowConstraints1);
        gridPane0.getRowConstraints().add(rowConstraints2);
        gridPane0.getRowConstraints().add(rowConstraints3);
        gridPane0.getChildren().add(lbPlayer1);
        gridPane0.getChildren().add(IPField);
      

    }
}
