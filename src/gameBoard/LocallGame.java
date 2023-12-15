/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import tictactoe.AlertMessage;

/**
 *
 * @author HimaMarey
 */
public class LocallGame extends GameBoardUI
{
    LocallGame(){}
     void onBtnClicked(Button btn)
    {
        if (btn.getText().isEmpty()) {
            btn.setText(super.isX ? "X" : "O");

            mark = super.isX ? "X" : "O";
            
           recordMove(btn);

            btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));
            if (isWinner()) {
                super.recordMovesToFile();
                try {
                    writer.flush();
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                    isRecord=false;
                try {
                    AlertMessage.showWinAlert();
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocallGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                    // Mynav.navigateTo(new WinnerBase());
                winnerAlert(super.isX ? "Player X" : "Player O");
                updateScore(isX);
                super.resetGride();
                
            } else if (super.gameOver()) {
                super.recordMovesToFile();
                try {
                    writer.flush();
                } catch (IOException ex) {
                    Logger.getLogger(GameBoardUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                    isRecord=false;

                super.grideFullAlert();
                
               // resetGride();
            } else {
                super.isX = !super.isX;
            }
        }
    }
}
