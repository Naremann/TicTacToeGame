/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import tictactoe.AlertMessage;

/**
 *
 * @author DELL
 */
// Play with PC "EASY MODE".
 public class PlayWithPc extends GameBoardUI {

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
  }

