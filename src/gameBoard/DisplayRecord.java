/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author HimaMarey
 */
public class DisplayRecord extends GameBoardUI{

    public DisplayRecord(String record)
    {
        playRecord();
        recBtn.setText("Play Again");
        recBtn.setOnAction((ActionEvent event) -> {
           playRecord();
        });
    }
    
    void playRecord()
    {
            resetGride();
            loadMovesFromFile();
            new Thread() {
                public void run() {
                    String str = rMoves.get(1) + "#";
                    while (!str.isEmpty()) {
                        int hashtagIndex = str.indexOf('#');
                        if (hashtagIndex < 0) {
                            break; // No more valid moves
                        }

                        String s = str.substring(0, hashtagIndex);
                        if (s.length() >= 5) {
                            int row = Character.getNumericValue(s.charAt(2));
                            int col = Character.getNumericValue(s.charAt(4));

                            if (row >= 0 && row < grideButtons.length && col >= 0 && col < grideButtons[row].length) {
                                // To update the UI from a non-UI thread, you need to use Platform.runLater()
                                final String buttonText = Character.toString(s.charAt(0));
                                Platform.runLater(() -> grideButtons[row][col].setText(buttonText));
                            }
                        }

                        System.out.println(s);

                        int index = hashtagIndex + 1;
                        if (index < str.length()) {
                            str = str.substring(index);
                        } else {
                            break; // No more characters after the last '#'
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                }
            }.start();
    }
    @Override
    void onBtnClicked(Button btn) {
        
    }
    
}
