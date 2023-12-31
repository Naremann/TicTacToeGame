/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
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
import dto.MyPlayer;
import homePage.XOgameUI;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.Moves;
import mynev.Mynav;
import network.NetWork;
import static org.apache.derby.impl.sql.compile.SQLParserConstants.T;
import remotePlay.listviewBase;
import tictactoe.AlertMessage;
import video.VideoAlert;

/**
 *
 * @author user
 */
public class OnlineGame extends GameBoardUI {
    public static String  firstSymbol="X";
    boolean isBlock;
    NetWork gameNetWork;

    public OnlineGame(String playerOne,String playerTwo , boolean turn) {
        gameNetWork= NetWork.getInstance(MyPlayer.serverIP);
        isX=turn;
        isBlock=turn;
        super.XN.setText(playerOne);
        super.ON.setText(playerTwo);

        super.XN.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        super.ON.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        flowPane0.getChildren().remove(resetBtn);
        exitBtn.setPrefWidth(235.0);
    }

    @Override
    void onBtnClicked(Button btn) {

        if (btn.getText().isEmpty()) {
            if (isBlock) {
                mark = isX ? "X" : "O";
                btn.setText(mark);
                int row = gride.getRowIndex(btn);
                int col = gride.getColumnIndex(btn);
               
                isBlock=false;
                
                String opponent=isX ? ON.getText():XN.getText();
                System.out.println(opponent+"bo");
                sendMove(opponent, row, col);
                System.out.println(isRecord);
                if (isRecord) {
                    recordMove(btn);
                }
                btn.setTextFill(javafx.scene.paint.Color.valueOf("#000000"));

                if (isWinner(mark)) {
                    try {
                    VideoAlert.showWinAlert(isX ? XN.getText() : ON.getText());
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocallGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                    isRecord = false;
                    resetGride();
                    isBlock=isX;
                    int myScore=isX?xCount:oCount;
                    updatePlayerScore(MyPlayer.userName,myScore);
                    //winnerAlert(isX ? XN.getText():ON.getText());
                        updateScore(isX);
                        if (isRecord) {
                            recordMovesToFile();
                        }  
                    
                } else if (super.gameOver()) {
                    try {
                        VideoAlert.showDrawAlert();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MediumLevelWithPc.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (isRecord) {
                        recordMovesToFile();
                    }
                    isRecord = false;
                    resetGride();
                    //super.grideFullAlert();
                    //Mynav.navigateTo(new listviewBase(MyPlayer.serverIP));
                    isBlock=isX;
                } else 
                {
                    isBlock=false;
                }
            }
        }
    }

    private void sendMove(String opponentUserName, int row, int col) {
        Gson gson = new GsonBuilder().create();
        JsonObject jObject = new JsonObject();
        jObject.addProperty("key", "saveMove");
        jObject.addProperty("userName", opponentUserName);
        System.out.println(opponentUserName);
        jObject.addProperty("mark", mark);
        jObject.addProperty("row", String.valueOf(row));
        jObject.addProperty("col", String.valueOf(col));
        String jString = gson.toJson(jObject);
        gameNetWork.sendMessage(jString);
    }
    public void getMove(String mark,int row,int col ) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                grideButtons[row][col].setText(mark);
                isBlock=true;
                if (isRecord) {
            Button btn =grideButtons[row][col];
                    recordMove(btn);
                }
    
            if (isWinner(mark)) {
                try {
                    VideoAlert.showPlayerLoseAlert();
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocallGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateScore(!isX);
                resetGride();
                if (isRecord) {
                                recordMovesToFile();
                            }
                isBlock=isX;
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
                isBlock=isX;
            }else
            {
                isBlock=true;
            }
    
            }
        });
    }
    
    private void handleExitPlayer(String userName,String opponentName) {
        Gson gson = new GsonBuilder().create();
        JsonObject jObject = new JsonObject();
        jObject.addProperty("key", "exitPlayer");
        jObject.addProperty("userName", userName);
        jObject.addProperty("opponentName", opponentName);
        System.out.println(opponentName);
        System.out.println(opponentName);
        String jString = gson.toJson(jObject);
        System.out.println(jString);
        gameNetWork.sendMessage(jString);
        
        Mynav.navigateTo(new XOgameUI());
    }
    private void showEixtAlert(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(null);
        ButtonType buttonNo = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonYes = new ButtonType("Ok");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonNo)
        {
            System.out.println("*********************");
        } else {
            System.out.println("*********************");
           handleExitPlayer(MyPlayer.userName,MyPlayer.opponentName);
        }
    }
    
    void updatePlayerScore(String userName,int score)
    {
        Gson gson = new GsonBuilder().create();
        JsonObject jObject = new JsonObject();
        jObject.addProperty("key", "updateScore");
        jObject.addProperty("userName", userName);
        jObject.addProperty("score", String.valueOf(score));
        String jString = gson.toJson(jObject);
        gameNetWork.sendMessage(jString);
    }
    
    void updatePlayerStatus(String userName,String status)
    {
        Gson gson = new GsonBuilder().create();
        JsonObject jObject = new JsonObject();
        jObject.addProperty("key", "updateScore");
        jObject.addProperty("userName", userName);
        jObject.addProperty("status", status);
        String jString = gson.toJson(jObject);
        gameNetWork.sendMessage(jString);
    }
    @Override
    void exitBtnClicked() {
         showEixtAlert("Exit Game","Are You Sure to Exit this Game ?");
    }
    
    @Override
    protected void resetGride() {
        for (Button[] row : grideButtons) {
            for (Button button : row) {
                button.setText("");
            }
        }
    }  
}
