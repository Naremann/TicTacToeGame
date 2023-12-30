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
        if (!super.gameOver() && btn.getText().isEmpty() && super.isX) {
            btn.setText("X");
            mark = isX ? "X" : "O";
            System.out.println(isRecord);

            if (isRecord) {
                recordMove(btn);
            }

            btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));

            if (isWinner(mark)) {
                try {
                    //handleGameEnd("YOU");
                    VideoAlert.showWinAlert(XN.getText());
                } catch (InterruptedException ex) {
                    Logger.getLogger(HardLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
                }
                       updateScore(isX);
                        resetGride();
                        if (isRecord) {
                        recordMovesToFile();
                    }
            } else if (super.gameOver()) {
                try {
                    // handleGameEnd("TIE");
                    VideoAlert.showDrawAlert();
                } catch (InterruptedException ex) {
                    Logger.getLogger(HardLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
                }
               //super.grideFullAlert();
                super.resetGride();
                if (isRecord) {
                        recordMovesToFile();
                    }
            } else {
           
                makeComputerMove();
                
            }
        }
    }

    protected void makeComputerMove() {
        if (!super.gameOver()) {
            int[] bestMove = findBestMove(Integer.MIN_VALUE, Integer.MAX_VALUE, 3); // Increase the search depth
            int row = bestMove[0];
            int col = bestMove[1];

            grideButtons[row][col].setText("O");
            grideButtons[row][col].setTextFill(javafx.scene.paint.Color.valueOf("#000000"));
            mark = !isX ? "X" : "O";
            if (isWinner(mark)) {
                try {
                    // handleGameEnd("COMPUTER");
                    VideoAlert.showPCWinAlert();
                } catch (InterruptedException ex) {
                    Logger.getLogger(HardLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
                }
               resetGride();
                //winnerAlert("COMPUTER");
                updateScore(false);
                if (isRecord) {
                        recordMovesToFile();
                    }
//                super.resetGride();
    } 
            } else if (super.gameOver()) {
            try {
                VideoAlert.showDrawAlert();
            } catch (InterruptedException ex) {
                Logger.getLogger(HardLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
            }
              resetGride();
              if (isRecord) {
                        recordMovesToFile();
                    }
                
            }
            else{  isX = !isX;}
        }
    

    private int[] findBestMove(int alpha, int beta, int depth) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < grideSize; i++) {
            for (int j = 0; j < grideSize; j++) {
                if (grideButtons[i][j].getText().isEmpty()) {
                    grideButtons[i][j].setText("O");
                    int score = minimax(0, false, alpha, beta, depth - 1);
                    grideButtons[i][j].setText("");

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }

                    alpha = Math.max(alpha, bestScore);

                    if (alpha >= beta) {
                        break;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(int depth, boolean isMaximizing, int alpha, int beta, int maxDepth) {
        mark = isX ? "X" : "O";
        if (super.isWinner(mark)) {
            return isMaximizing ? -1 : 1;
        }

        if (super.gameOver() || depth == maxDepth) {
            return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < grideSize; i++) {
            for (int j = 0; j < grideSize; j++) {
                if (grideButtons[i][j].getText().isEmpty()) {
                    grideButtons[i][j].setText(isMaximizing ? "O" : "X");
                    int score = minimax(depth + 1, !isMaximizing, alpha, beta, maxDepth);
                    grideButtons[i][j].setText("");

                    if (isMaximizing) {
                        bestScore = Math.max(score, bestScore);
                        alpha = Math.max(alpha, bestScore);
                    } else {
                        bestScore = Math.min(score, bestScore);
                        beta = Math.min(beta, bestScore);
                    }

                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }

        return bestScore;
    }
    
  /*  private String checkGameResult() {
    if (super.isWinner("O")) {
        return "PC_WINS";
    } else if (super.isWinner("X")) {
        return "YOU_WIN";
    } else if (super.gameOver()) {
        return "DRAW";
    } else {
        return "CONTINUE";
    }
}

// Call this method after each move to check the result
private void checkAndHandleGameResult() {
    String result = checkGameResult();

    switch (result) {
        case "PC_WINS":
            handleGameEnd("PC");
            break;
        case "YOU_WIN":
            handleGameEnd("YOU");
            break;
        case "DRAW":
            handleGameEnd("DRAW");
            break;
        case "CONTINUE":
            // Continue the game
            break;
    }
}*/

  /*  private void handleGameEnd(String winner) {
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
    }*/
}
