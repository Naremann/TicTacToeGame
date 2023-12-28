package gameBoard;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tictactoe.AlertMessage;
import video.VideoAlert;

public class HardLevelWithPc extends GameBoardUI {

    public HardLevelWithPc(String player1_Name) {
        super.XN.setText(player1_Name);
        super.ON.setText("Computer");

        super.XN.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        super.ON.setFont(Font.font("Arial", FontWeight.BOLD, 18));
    }

    @Override
    void onBtnClicked(Button btn) {
        if (btn.getText().isEmpty() && super.isX) {
            btn.setText("X");
            super.mark = super.isX ? "X" : "O";
            System.out.println(isRecord);

            if (isRecord) {
                recordMove(btn);
            }

            btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));

            if (super.isWinner(mark)) {
                handleGameEnd("YOU");
            } else if (super.gameOver()) {
                handleGameEnd("TIE");
            } else {
                super.isX = !super.isX;
                makeComputerMove();
            }
        }
    }

    protected void makeComputerMove() {
        int[] bestMove = findBestMove();
        int row = bestMove[0];
        int col = bestMove[1];

        grideButtons[row][col].setText("O");
        grideButtons[row][col].setTextFill(javafx.scene.paint.Color.valueOf("#000000"));
        mark = isX?"X":"O";
        if (super.isWinner(mark)) {
            handleGameEnd("COMPUTER");
        } else if (super.gameOver()) {
            handleGameEnd("TIE");
        } else {
            super.isX = !super.isX;
        }
    }

    private int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < grideSize; i++) {
            for (int j = 0; j < grideSize; j++) {
                if (grideButtons[i][j].getText().isEmpty()) {
                    grideButtons[i][j].setText("O");
                    int score = minimax(0, false);
                    grideButtons[i][j].setText("");

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(int depth, boolean isMaximizing) {
        mark = isX?"X":"O";
        if (super.isWinner(mark)) {
            return isMaximizing ? -1 : 1;
        }

        if (super.gameOver()) {
            return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < grideSize; i++) {
            for (int j = 0; j < grideSize; j++) {
                if (grideButtons[i][j].getText().isEmpty()) {
                    grideButtons[i][j].setText(isMaximizing ? "O" : "X");
                    int score = minimax(depth + 1, !isMaximizing);
                    grideButtons[i][j].setText("");

                    bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
                }
            }
        }

        return bestScore;
    }

    private void handleGameEnd(String winner) {
        if (isRecord) {
            recordMovesToFile();
        }
        isRecord = false;

        try {
            //AlertMessage.showWinAlert();
            VideoAlert.showLoseAlert();
            winnerAlert(winner);
            updateScore(winner.equals("YOU"));
            resetGride();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameBoardUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
