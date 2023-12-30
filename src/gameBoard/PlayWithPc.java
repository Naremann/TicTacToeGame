/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tictactoe.AlertMessage;
import video.VideoAlert;

/**
 *
 * @author DELL
 */
// Play with PC "EASY MODE".
 public class PlayWithPc extends GameBoardUI {
     
     public PlayWithPc(){}
     public PlayWithPc(String player1_Name) {
        super.XN.setText("" + player1_Name);
        super.ON.setText("Computer" );
        
        super.XN.setFont(Font.font("Arial", FontWeight.BOLD, 18));  
        super.ON.setFont(Font.font("Arial", FontWeight.BOLD, 18)); 
    }
    @Override
    void onBtnClicked(Button btn) {
       
        if (btn.getText().isEmpty()) {
            if (super.isX) {
                btn.setText("X");
               mark = isX ? "X" : "O";
               System.out.println(isRecord);
               if(isRecord)
               recordMove(btn);
                btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));

                if (isWinner(mark)) {
                    updateScore(isX);
                    if(isRecord)
                        recordMovesToFile();
                    isRecord=false;
                    resetGride();
                     try {
                    VideoAlert.showWinAlert(XN.getText());
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocallGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                   /* try {
                        AlertMessage.showWinAlert();
                        winnerAlert("YOU");
                        updateScore(isX);
                        super.resetGride();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameBoardUI.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                } else if (super.gameOver()) {
                    try {
                        VideoAlert.showDrawAlert();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MediumLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(isRecord)
                        recordMovesToFile();
                    isRecord=false;
                    //super.grideFullAlert();
                    resetGride();
                } else {
                    super.isX = !super.isX;
                    makeComputerMove();
                }
            }
        }
    }
    protected void makeComputerMove() {
    int row, col;
    do {
        row = (int) (Math.random() * grideSize);
        col = (int) (Math.random() * grideSize);
    } while (!grideButtons[row][col].getText().isEmpty());

    grideButtons[row][col].setText("O");
    grideButtons[row][col].setTextFill(javafx.scene.paint.Color.valueOf("#000000"));
    if (isRecord) {
            Button btn =grideButtons[row][col];
                    recordMove(btn);
                }
    mark = isX?"X":"O";
    if (isWinner(mark)) {
        try {
            VideoAlert.showPCWinAlert();
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayWithPc.class.getName()).log(Level.SEVERE, null, ex);
        }
        //winnerAlert("COMPUTER"); 
        updateScore(false);
        resetGride();
        if (isRecord) {
                        recordMovesToFile();
                    }
        
    } 
    else if (gameOver()) {
        try {
                        VideoAlert.showDrawAlert();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MediumLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
                    }
        //grideFullAlert();
        resetGride();
        if (isRecord) {
                        recordMovesToFile();
                    }
    } 
    else {
        isX = !isX;
    }
   } 
 }

