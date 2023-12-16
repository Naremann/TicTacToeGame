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
                btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));

                if (isWinner()) {
                    try {
                        AlertMessage.showWinAlert();
                        // Mynav.navigateTo(new WinnerBase());
                        winnerAlert("YOU");
                        updateScore(isX);
                        super.resetGride();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameBoardUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (super.gameOver()) {
                    super.grideFullAlert();
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
 }
