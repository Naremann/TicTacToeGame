/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author HimaMarey
 */




public class GameBoardUI extends AnchorPane {

    protected final FlowPane flowPane;
    protected final Label XN;
    protected final TextField XSF;
    protected final Label ON;
    protected final TextField OSF;
    protected final FlowPane flowPane0;
    protected final Button recBtn;
    protected final Button againBtn;
    protected final Button resetBtn;
    protected final Button exitBtn;
    protected final GridPane gride;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    
    private boolean playWithComputer ;
    
    private final int grideSize;
    private Button[][] grideButtons;
    private boolean isX;
    int xCount;
    int oCount;
    public GameBoardUI() {
        xCount=0;
        oCount=0;
        grideSize = 3;
        isX = true;
        grideButtons = new Button[grideSize][grideSize];
        flowPane = new FlowPane();
        XN = new Label();
        XSF = new TextField();
        ON = new Label();
        OSF = new TextField();
        flowPane0 = new FlowPane();
        recBtn = new Button();
        againBtn = new Button();
        resetBtn = new Button();
        exitBtn = new Button();
        gride = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        drawBtn();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        flowPane.setLayoutX(330.0);
        flowPane.setLayoutY(47.0);
        flowPane.setPrefHeight(109.0);
        flowPane.setPrefWidth(256.0);

        XN.setAlignment(javafx.geometry.Pos.CENTER);
        XN.setPrefHeight(40.0);
        XN.setPrefWidth(155.0);
        XN.setText("Label");

        XSF.setAlignment(javafx.geometry.Pos.CENTER);
        XSF.setPrefHeight(33.0);
        XSF.setPrefWidth(84.0);
        XSF.setDisable(true);
        XSF.setText(String.valueOf(xCount));

        ON.setAlignment(javafx.geometry.Pos.CENTER);
        ON.setPrefHeight(35.0);
        ON.setPrefWidth(155.0);
        ON.setText("Label");

        OSF.setAlignment(javafx.geometry.Pos.CENTER);
        OSF.setPrefHeight(33.0);
        OSF.setPrefWidth(84.0);
        OSF.setDisable(true);
        OSF.setText(String.valueOf(oCount));


        flowPane0.setLayoutX(330.0);
        flowPane0.setLayoutY(245.0);
        flowPane0.setPrefHeight(141.0);
        flowPane0.setPrefWidth(256.0);

        recBtn.setMnemonicParsing(false);
        recBtn.setPrefHeight(35.0);
        recBtn.setPrefWidth(235.0);
        recBtn.setText("Record Game");
        FlowPane.setMargin(recBtn, new Insets(4.0, 8.0, 4.0, 8.0));
        recBtn.setFont(new Font(18.0));

        againBtn.setMnemonicParsing(false);
        againBtn.setPrefHeight(35.0);
        againBtn.setPrefWidth(235.0);
        againBtn.setText("Play Again");
        FlowPane.setMargin(againBtn, new Insets(4.0, 8.0, 4.0, 8.0));
        againBtn.setFont(new Font(18.0));
        againBtn.setOnAction(event -> {
            resetGride();
        });

        resetBtn.setMnemonicParsing(false);
        resetBtn.setPrefHeight(35.0);
        resetBtn.setPrefWidth(112.0);
        resetBtn.setText("Reset");
        FlowPane.setMargin(resetBtn, new Insets(4.0, 4.0, 4.0, 8.0));
        resetBtn.setFont(new Font(18.0));
        resetBtn.setOnAction(event -> {
            xCount=0;
            XSF.setText(String.valueOf(xCount));
            oCount=0;
            OSF.setText(String.valueOf(oCount));
            resetGride();
        });

        exitBtn.setMnemonicParsing(false);
        exitBtn.setPrefHeight(35.0);
        exitBtn.setPrefWidth(112.0);
        exitBtn.setStyle("-fx-background-color: red;");
        exitBtn.setText("Exit");
        exitBtn.setTextFill(javafx.scene.paint.Color.valueOf("#fdfcfc"));
        FlowPane.setMargin(exitBtn, new Insets(4.0));
        exitBtn.setFont(new Font(18.0));

        gride.setLayoutX(14.0);
        gride.setLayoutY(47.0);
        gride.setPrefHeight(198.0);
        gride.setPrefWidth(320.0);

        flowPane.getChildren().add(XN);
        flowPane.getChildren().add(XSF);
        flowPane.getChildren().add(ON);
        flowPane.getChildren().add(OSF);
        getChildren().add(flowPane);
        flowPane0.getChildren().add(recBtn);
        flowPane0.getChildren().add(againBtn);
        flowPane0.getChildren().add(resetBtn);
        flowPane0.getChildren().add(exitBtn);
        getChildren().add(flowPane0);
        getChildren().add(gride);

    }
    
     public GameBoardUI(boolean playWithComputer) {
         
        this.playWithComputer = playWithComputer;
        xCount=0;
        oCount=0;
        grideSize = 3;
        isX = true;
        grideButtons = new Button[grideSize][grideSize];
        flowPane = new FlowPane();
        XN = new Label();
        XSF = new TextField();
        ON = new Label();
        OSF = new TextField();
        flowPane0 = new FlowPane();
        recBtn = new Button();
        againBtn = new Button();
        resetBtn = new Button();
        exitBtn = new Button();
        gride = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        drawBtn();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        flowPane.setLayoutX(330.0);
        flowPane.setLayoutY(47.0);
        flowPane.setPrefHeight(109.0);
        flowPane.setPrefWidth(256.0);

        XN.setAlignment(javafx.geometry.Pos.CENTER);
        XN.setPrefHeight(40.0);
        XN.setPrefWidth(155.0);
        XN.setText("Label");

        XSF.setAlignment(javafx.geometry.Pos.CENTER);
        XSF.setPrefHeight(33.0);
        XSF.setPrefWidth(84.0);
        XSF.setDisable(true);
        XSF.setText(String.valueOf(xCount));

        ON.setAlignment(javafx.geometry.Pos.CENTER);
        ON.setPrefHeight(35.0);
        ON.setPrefWidth(155.0);
        ON.setText("Label");

        OSF.setAlignment(javafx.geometry.Pos.CENTER);
        OSF.setPrefHeight(33.0);
        OSF.setPrefWidth(84.0);
        OSF.setDisable(true);
        OSF.setText(String.valueOf(oCount));


        flowPane0.setLayoutX(330.0);
        flowPane0.setLayoutY(245.0);
        flowPane0.setPrefHeight(141.0);
        flowPane0.setPrefWidth(256.0);

        recBtn.setMnemonicParsing(false);
        recBtn.setPrefHeight(35.0);
        recBtn.setPrefWidth(235.0);
        recBtn.setText("RECORD GAME");
        FlowPane.setMargin(recBtn, new Insets(4.0, 8.0, 4.0, 8.0));
        recBtn.setFont(new Font(18.0));

        againBtn.setMnemonicParsing(false);
        againBtn.setPrefHeight(35.0);
        againBtn.setPrefWidth(235.0);
        againBtn.setText("PLAY AGAIN");
        FlowPane.setMargin(againBtn, new Insets(4.0, 8.0, 4.0, 8.0));
        againBtn.setFont(new Font(18.0));
        againBtn.setOnAction(event -> {
            resetGride();
        });

        resetBtn.setMnemonicParsing(false);
        resetBtn.setPrefHeight(35.0);
        resetBtn.setPrefWidth(112.0);
        resetBtn.setText("RESET");
        FlowPane.setMargin(resetBtn, new Insets(4.0, 4.0, 4.0, 8.0));
        resetBtn.setFont(new Font(18.0));
        resetBtn.setOnAction(event -> {
            xCount=0;
            XSF.setText(String.valueOf(xCount));
            oCount=0;
            OSF.setText(String.valueOf(oCount));
            resetGride();
        });

        exitBtn.setMnemonicParsing(false);
        exitBtn.setPrefHeight(35.0);
        exitBtn.setPrefWidth(112.0);
        exitBtn.setStyle("-fx-background-color: red;");
        exitBtn.setText("EXIT");
        exitBtn.setTextFill(javafx.scene.paint.Color.valueOf("#fdfcfc"));
        FlowPane.setMargin(exitBtn, new Insets(4.0));
        exitBtn.setFont(new Font(18.0));

        gride.setLayoutX(14.0);
        gride.setLayoutY(47.0);
        gride.setPrefHeight(198.0);
        gride.setPrefWidth(320.0);

        flowPane.getChildren().add(XN);
        flowPane.getChildren().add(XSF);
        flowPane.getChildren().add(ON);
        flowPane.getChildren().add(OSF);
        getChildren().add(flowPane);
        flowPane0.getChildren().add(recBtn);
        flowPane0.getChildren().add(againBtn);
        flowPane0.getChildren().add(resetBtn);
        flowPane0.getChildren().add(exitBtn);
        getChildren().add(flowPane0);
        getChildren().add(gride); 
    }
    void drawBtn()
    {
        for (int row = 0; row < grideSize; row++) {
            for (int col = 0; col < grideSize; col++) {
                Button btn = new Button();
                btn.setMinWidth(50);
                btn.setMinHeight(50);
                gride.setMargin(btn, new Insets(8.0));
                
                if (playWithComputer) {
                btn.setOnAction(event -> onBtnClickedAI(btn));
                } 
                else {
                btn.setOnAction(event -> onBtnClicked(btn));
                }
                grideButtons[row][col] = btn;
                gride.add(btn, col, row);
            }
        }
    }
    void onBtnClicked(Button btn)
    {
        if (btn.getText().isEmpty()) {
            btn.setText(isX ? "X" : "O");
            btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));
            if (isWinner()) {
                winnerAlert(isX ? "Player X" : "Player O");
                updateScore(isX);
                resetGride();
            } else if (gameOver()) {
                grideFullAlert();
               // resetGride();
            } else {
                isX = !isX;
            }
        }
    }
    //pLay with pc easy mode.
     void onBtnClickedAI(Button btn)
    {
        if (btn.getText().isEmpty()) {
            if (isX){
                btn.setText("X");
                btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));
                
                 if (isWinner()) {
           
                winnerAlert("YOU");
                updateScore(isX);
                resetGride();
            } else if (gameOver()) {
                grideFullAlert();
              
            } else {
                isX = !isX;
                makeComputerMove();
                
            }
            }
           
        }
    }
     
     private void makeComputerMove() {
    int row, col;
    do {
        row = (int) (Math.random() * grideSize);
        col = (int) (Math.random() * grideSize);
    } while (!grideButtons[row][col].getText().isEmpty());

    grideButtons[row][col].setText("O");
    grideButtons[row][col].setTextFill(javafx.scene.paint.Color.valueOf("#000000"));

    if (isWinner()) {
        winnerAlert("COMPUTER"); 
        updateScore(false);
        resetGride();
    } 
    else if (gameOver()) {
        grideFullAlert();
        resetGride();
    } 
    else {
        isX = !isX;
    }
}
     
     
     
    void winnerAlert(String winner)
    {
     //   showCustomAlert();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("END GAME");
        alert.setHeaderText(null);
        alert.setContentText(winner + " WON!");
        alert.showAndWait();
    }
    boolean isWinner()
    {
        String symbol = isX ? "X" : "O";
        for (int i = 0; i < grideSize; i++) {
            if (grideButtons[i][0].getText().equals(symbol)
                    && grideButtons[i][1].getText().equals(symbol)
                    && grideButtons[i][2].getText().equals(symbol)) {
                return true; // Row win
            }
            if (grideButtons[0][i].getText().equals(symbol)
                    && grideButtons[1][i].getText().equals(symbol)
                    && grideButtons[2][i].getText().equals(symbol)) {
                return true; // Column win
            }
        }
        if (grideButtons[0][0].getText().equals(symbol)
                && grideButtons[1][1].getText().equals(symbol)
                && grideButtons[2][2].getText().equals(symbol)) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (grideButtons[0][2].getText().equals(symbol)
                && grideButtons[1][1].getText().equals(symbol)
                && grideButtons[2][0].getText().equals(symbol)) {
            return true; // Diagonal win (top-right to bottom-left)
        }
        return false;
    }
    private boolean gameOver() {
        for (Button[] row : grideButtons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    private void resetGride() {
        for (Button[] row : grideButtons) {
            for (Button button : row) {
                button.setText("");
                button.setDisable(false);
            }
        }
        isX = true;
    }
    private void grideFullAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText(null);
        alert.setContentText("NOBODY WON!");
        alert.showAndWait();
    }
    private void updateScore(boolean isPlayerX) {
        if (isPlayerX) {
            xCount++;
            XSF.setText(String.valueOf(xCount));

        } else {
            oCount++;
            OSF.setText(String.valueOf(oCount));

        }
    }
    
    private void showCustomAlert() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("CUSTOM ALERT");
        dialog.setHeaderText(null);

        Label contentLabel = new Label("This is a custom alert.");
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> dialog.close());

        VBox contentBox = new VBox(10, contentLabel, okButton);
        contentBox.setAlignment(Pos.CENTER);

        dialog.getDialogPane().setContent(contentBox);
        dialog.showAndWait();
    }
}
