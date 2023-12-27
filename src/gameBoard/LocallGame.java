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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tictactoe.AlertMessage;
import video.VideoAlert;

/**
 *
 * @author HimaMarey
 */
public class LocallGame extends GameBoardUI
{
   
    public LocallGame(String player1_Name, String player2_Name) {
        //throw new UnsupportedOperationException("Not supported yet."); 
        XN.setText("" + player1_Name);
        ON.setText("" + player2_Name);
        
        XN.setFont(Font.font("Arial", FontWeight.BOLD, 18));  
        ON.setFont(Font.font("Arial", FontWeight.BOLD, 18)); 
    }
     void onBtnClicked(Button btn)
    {
        if (btn.getText().isEmpty()) {
            btn.setText(isX ? "X" : "O");

            mark = isX ? "X" : "O";
            System.out.println(isRecord);
            if(isRecord)
            recordMove(btn);
           btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));
           if (isWinner()) 
           {
                if(isRecord)
                    recordMovesToFile();
                isRecord=false;
                try {
                    VideoAlert.showWinAlert();
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocallGame.class.getName()).log(Level.SEVERE, null, ex);
                }
               /* try {
                   AlertMessage.showWinAlert();
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocallGame.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                    // Mynav.navigateTo(new WinnerBase());
                winnerAlert(isX ? "Player X" : "Player O");
                updateScore(isX);
                super.resetGride();
                
            } else if (gameOver()) 
            {
                if(isRecord)
                    recordMovesToFile();
                isRecord=false;
                super.grideFullAlert();
                resetGride();
            } else {
                isX = !isX;
            }
        }
    }
}