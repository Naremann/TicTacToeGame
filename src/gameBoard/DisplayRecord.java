/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameBoard;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import mynev.Mynav;
import recordTable.RecordList;

/**
 *
 * @author HimaMarey
 */
public class DisplayRecord extends GameBoardUI{

    public DisplayRecord(int index)
    {
        playRecord(index);
        recBtn.setText("Play Again");
        recBtn.setOnAction((ActionEvent event) -> {
           playRecord(index);
        });
        resetBtn.setText("Back");
        resetBtn.setOnAction((ActionEvent event) -> {
          Mynav.navigateTo(new RecordList(),event);
        });
    }
    
    void playRecord(int index)
    {
            resetGride();
            loadMovesFromFile();
            new Thread() {
                public void run() {
                    //String d=rMoves.get(0) + "?";
                    String str = rMoves.get(index) + "#";
                     
                    while (!str.isEmpty()) {
                        // int x = str.indexOf('?')+1;
                        int hashtagIndex = str.indexOf('#');
                        if(str.contains("!")){ 
                          //  System.out.println("goa");
                            int nameIndex =str.indexOf('!');
                          //System.out.println("name index "+nameIndex);
                           String name = str.substring(0,nameIndex);
                              System.out.println(name);
                              XN.setText(name);
                              if(nameIndex !=-1){int nameIndexx=str.indexOf('!', nameIndex+1);
                                 String name2=str.substring(nameIndex+1,nameIndexx);
                                         ON.setText(name2);
                                   if(nameIndex !=-1&&nameIndexx !=-1){int nameIndexxx=str.indexOf('!', nameIndexx+1);
                                   if(nameIndex !=-1&&nameIndexx !=-1&&nameIndexxx !=-1){int score=str.indexOf('!', nameIndexxx+1);
                                   String namescore=str.substring(nameIndexxx+1,score);
                                         XSF.setText(namescore);
                                         if(nameIndex !=-1&&nameIndexx !=-1&&nameIndexxx !=-1&& score !=1){int score2=str.indexOf('!', score+1);
                                   String namescore2=str.substring(score+1,score2);
                                         OSF.setText(namescore2);}
                                   }
                                   
                                         
                                   }
                             
                              }
                              
                        }
                      
                      

                     ;
                        if (hashtagIndex < 0) {
                            break; // No more valid moves
                        }
                       
                        
                        
                        
                        
                       
                        String s = str.substring(0, hashtagIndex);
                        
                        if (s.length() >= 5) {
                            System.out.println(">5");
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
                          //  System.out.println("<leng");
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
