/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.google.gson.JsonObject;
import model.Moves;
import static org.apache.derby.impl.sql.compile.SQLParserConstants.T;
import tictactoe.AlertMessage;

/**
 *
 * @author user
 */
public class OnlineGame extends GameBoardUI {
    public static String  firstSymbol="X";
    boolean isBlock=false;

    public OnlineGame() {
        super.XN.setText("");
        super.ON.setText("Computer");

        super.XN.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        super.ON.setFont(Font.font("Arial", FontWeight.BOLD, 18));
    }

    @Override
    void onBtnClicked(Button btn) {

        if (btn.getText().isEmpty()) {
            if (super.isX) {
                btn.setText("X");
                int row = gride.getRowIndex(btn);
                int col = gride.getColumnIndex(btn);
               
                isBlock=false;
                mark = isX ? "X" : "O";
                sendMove("Mona", row, col);
                System.out.println(isRecord);
                if (isRecord) {
                    recordMove(btn);
                }
                btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));

                if (isWinner()) {
                    if (isRecord) {
                        recordMovesToFile();
                    }
                    isRecord = false;
                    try {
                        AlertMessage.showWinAlert();
                        winnerAlert("YOU");
                        updateScore(isX);
                        super.resetGride();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MediumLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (super.gameOver()) {
                    if (isRecord) {
                        recordMovesToFile();
                    }
                    isRecord = false;
                    super.grideFullAlert();
                } else {
                    super.isX = !super.isX;
                    network.NetWork.getInstance(TackIP.IPAddress).reciveMessage();
                    isBlock=true;
                }
            }
        }
    }

    private void sendMove(String opponentUserName, int row, int col) {
        Gson gson = new GsonBuilder().create();
        JsonObject jObject = new JsonObject();
        jObject.addProperty("key", "saveMove");
        jObject.addProperty("userName", opponentUserName);
        jObject.addProperty("row", row);
        jObject.addProperty("col", col);
        String jString = gson.toJson(jObject);
        network.NetWork.getInstance(TackIP.IPAddress).sendMessage(jString);
     

    }

}
