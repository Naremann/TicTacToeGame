/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import gameBoard.PlayerNamesUI;
import homePage.XOgameUI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;

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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mynev.Mynav;
import tictactoe.AlertMessage;
import video.WinnerBase;


/**
 *
 * @author HimaMarey
 */




public abstract class GameBoardUI extends AnchorPane {

    protected final FlowPane flowPane;
    protected final Label XN;
    protected final TextField XSF;
    protected final Label ON;
    protected final TextField OSF;
    protected final FlowPane flowPane0;
    protected final Button recBtn;
    protected final Button resetBtn;
    protected final Button exitBtn;
    protected final GridPane gride;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final int grideSize;
    protected Button[][] grideButtons;
    protected boolean isX;
    protected int xCount;
    protected int oCount;
    protected String mark;
    protected final List<String> moves;
    protected final List<String> rMoves;
    protected boolean isRecord;
    public GameBoardUI() {
        isRecord =false;
        moves = new ArrayList<>();
        rMoves = new ArrayList<>();
       
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
        XN.setText("");  
        
        XSF.setAlignment(javafx.geometry.Pos.CENTER);
        XSF.setPrefHeight(33.0);
        XSF.setPrefWidth(84.0);
        XSF.setDisable(true);
        XSF.setText(String.valueOf(xCount));

        ON.setAlignment(javafx.geometry.Pos.CENTER);
        ON.setPrefHeight(35.0);
        ON.setPrefWidth(155.0);
        ON.setText("");
        

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
        recBtn.setOnAction(event -> {
            isRecord= true;
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
        exitBtn.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
            @Override
                 public void handle(ActionEvent event){  
                   Mynav.navigateTo(new XOgameUI(), event);
                 }
        });

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
        flowPane0.getChildren().add(resetBtn);
        flowPane0.getChildren().add(exitBtn);
        
        
        getChildren().add(flowPane0);
        getChildren().add(gride);

    }
   

    protected void drawBtn()
    {
        for (int row = 0; row < grideSize; row++) {
            for (int col = 0; col < grideSize; col++) {
                Button btn = new Button();
                btn.setMinWidth(50);
                btn.setMinHeight(50);
                gride.setMargin(btn, new Insets(8.0));
                
                btn.setOnAction(event -> onBtnClicked(btn));
                grideButtons[row][col] = btn;
                gride.add(btn, col, row);
            }
        }
    }

    abstract void onBtnClicked(Button btn);
   
     
    
    protected void winnerAlert(String winner)
    {
    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("END GAME");
        alert.setHeaderText(null);
        alert.setContentText(winner + " WON!");
        alert.showAndWait();
    }
    protected boolean isWinner()
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
    protected boolean gameOver() {
        for (Button[] row : grideButtons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    protected void resetGride() {
        for (Button[] row : grideButtons) {
            for (Button button : row) {
                button.setText("");
            }
        }
        isX = true;
    }
    protected void grideFullAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText(null);
        alert.setContentText("NOBODY WON!");
        alert.showAndWait();
    }
    protected void updateScore(boolean isPlayerX) {
        if (isPlayerX) {
            xCount++;
            XSF.setText(String.valueOf(xCount));

        } else {
            oCount++;
            OSF.setText(String.valueOf(oCount));

        }
    }
    
    protected void showCustomAlert() {
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

    protected void recordMovesToFile() {
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Record History.txt",true));
            Date date = new Date();
            writer.write(XN.getText()+"!"+ON.getText()+"!"+date.toString()+"!"+XSF.getText()+"!"+OSF.getText()+"!?#");
            for (int i = 0 ; i<moves.size();i++) {
                writer.write(moves.get(i));
                writer.write("#");
                if(i==moves.size()-1)
                {  
                    writer.newLine();
                    writer.write("&");
                    writer.newLine();
                }
         
            }
            moves.clear();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     protected void recordMove(Button btn) {
        int row = gride.getRowIndex(btn);
        int col = gride.getColumnIndex(btn);
        String move = String.format("%s,%s,%s", mark, row, col);
        moves.add(move);
    }
     protected void loadMovesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Record History.txt"))) {
            String line;
            StringBuilder record = new StringBuilder();
             rMoves.clear();
            while ((line = reader.readLine()) != null) {
                if (line.equals("&")) {
                   
                    rMoves.add(record.toString());
                    record.setLength(0); // Clear StringBuilder for the next record
                    
                } else {
                    record.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

