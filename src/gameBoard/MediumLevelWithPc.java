/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import model.Moves;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//import static sun.audio.AudioPlayer.player;
import tictactoe.AlertMessage;
import video.VideoAlert;

/**
 *
 * @author user
 */
public class MediumLevelWithPc extends GameBoardUI {

    public MediumLevelWithPc() {
    }

    public MediumLevelWithPc(String player1_Name) {
        super.XN.setText("" + player1_Name);
        super.ON.setText("Computer");

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
                if (isRecord) {
                    recordMove(btn);
                }
                btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));

                if (isWinner(mark)) {
                    if (isRecord) {
                        recordMovesToFile();
                    }
                    isRecord = false;
                     try {
                    VideoAlert.showWinAlert();
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocallGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                    /*try {
                        AlertMessage.showWinAlert();
                        winnerAlert("YOU");
                        updateScore(isX);
                        super.resetGride();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MediumLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                } else if (super.gameOver()) {
                    if (isRecord) {
                        recordMovesToFile();
                    }
                    isRecord = false;
                    super.grideFullAlert();
                } else {
                    super.isX = !super.isX;
                    makeComputerMove();
                }
            }
        }
    }

    protected void makeComputerMove() {
        int bestMove;
        int row;
        int col;

        System.out.println("can win: " + canWin("X"));
        System.out.println(
                "item: " + grideButtons[0][0].getText());
        if (canWin("X") != -1) {
            bestMove = canWin("X");
            System.out.println("best: " + bestMove);
            row = getGameBoardIndicesByBestMove(bestMove).getRow();
            col = getGameBoardIndicesByBestMove(bestMove).getcolumn();
        } else if (canWin("O") != -1) {
            bestMove = canWin("O");
            row = getGameBoardIndicesByBestMove(bestMove).getRow();
            col = getGameBoardIndicesByBestMove(bestMove).getcolumn();
        } else {

            do {
                row = (int) (Math.random() * grideSize);
                col = (int) (Math.random() * grideSize);
            } while (!grideButtons[row][col].getText().isEmpty());

        }

        grideButtons[row][col].setText("O");
        mark = isX?"X":"O";
        if (isWinner(mark)) {
            winnerAlert("COMPUTER");
            updateScore(false);
            resetGride();
        } else if (gameOver()) {
            grideFullAlert();
            resetGride();
        } else {
            isX = !isX;
        }

    }

    private int canWin(String mark) {
        int cell;

        // check rows
        for (int i = 0; i < 3; i++) {
            cell = checkForThirdEmptyCell(mark, grideButtons[i][0].getText(), grideButtons[i][1].getText(), grideButtons[i][2].getText());
            switch (cell) {
                case 1:
                    return i * 3;
                case 2:
                    return (i * 3) + 1;
                case 3:
                    return (i * 3) + 2;
            }
        }

        // check cols
        for (int i = 0; i < 3; i++) {
            cell = checkForThirdEmptyCell(mark, grideButtons[0][i].getText(), grideButtons[1][i].getText(), grideButtons[2][i].getText());
            switch (cell) {
                case 1:
                    return (i * 2);
                case 2:
                    return (i * 1) + 3;
                case 3:
                    return (i * 1) + 6;
            }
        }

        // check 1st diagonal
        cell = checkForThirdEmptyCell(mark, grideButtons[0][0].getText(), grideButtons[1][1].getText(), grideButtons[2][2].getText());
        switch (cell) {
            case 1:
                return 0;
            case 2:
                return 4;
            case 3:
                return 8;
        }

        // check 2nd diagonal
        cell = checkForThirdEmptyCell(mark, grideButtons[0][2].getText(), grideButtons[1][1].getText(), grideButtons[2][0].getText());
        switch (cell) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 6;
        }
        System.out.println("cell" + cell);

        return -1;

    }

    private int checkForThirdEmptyCell(String mark, String btnText1, String btnText2, String btnText3) {
        if (btnText1.equals(mark) && btnText1.equals(btnText2) && btnText3.isEmpty()) {
            return 3;
        } else if (btnText1.equals(mark) && btnText1.equalsIgnoreCase(btnText3) && btnText2.isEmpty()) {
            return 2;
        } else if (btnText2.equals(mark) && btnText2.equalsIgnoreCase(btnText3) && btnText1.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

    Moves getGameBoardIndicesByBestMove(int move) {

        switch (move) {
            case 0:
                return new Moves(0, 0);
            case 1:
                return new Moves(0, 1);
            case 2:
                return new Moves(0, 2);
            case 3:
                return new Moves(1, 0);
            case 4:
                return new Moves(1, 1);
            case 5:
                return new Moves(1, 2);
            case 6:
                return new Moves(2, 0);
            case 7:
                return new Moves(2, 1);
            default:
                return new Moves(2, 2);
        }
    }

}
