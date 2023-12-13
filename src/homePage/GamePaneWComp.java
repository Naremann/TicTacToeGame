
package homePage;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


/**
 *
 * @author DELL
 */



public class GamePaneWComp extends Pane {

    GridPane boardPane = new GridPane();
    Button[] boardButtons = new Button[3*3];
    
    boolean isGameEnds;
    boolean isFirstPlayerTurn = true;
    int XOCounter = 0;
    Random random = new Random();
    int randomNumber;
    static boolean challengeComputer;
    
    EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
        actionPerformed(e);
    };

    private void createGameBoard() {
        
        int row = 0;
        int column = 0;
        
        for (int i = 0; i < boardButtons.length; i++) {

            boardButtons[i] = new Button();
            boardButtons[i].setPrefSize(90, 90);
            
            GridPane.setMargin(boardButtons[i], new Insets(5));
            boardPane.add(boardButtons[i], column, row);

            boardButtons[i].addEventHandler(ActionEvent.ACTION, e -> {
                actionPerformed(e);
            });
                
            column++;
            if(column == 3)
            {
                row++;
                column = 0;
            }
        }
        
    }

    private void checkIfGameEnds() {
            
        String bt1 = boardButtons[0].getText();
        String bt2 = boardButtons[1].getText();
        String bt3 = boardButtons[2].getText();
        String bt4 = boardButtons[3].getText();
        String bt5 = boardButtons[4].getText();
        String bt6 = boardButtons[5].getText();
        String bt7=  boardButtons[6].getText();
        String bt8 = boardButtons[7].getText();
        String bt9 = boardButtons[8].getText();
  
        if (bt1.equals(bt2) && bt1.equals(bt3) && !bt1.equals("")) isGameEnds = true;
        if (bt4.equals(bt5) && bt4.equals(bt6) && !bt4.equals("")) isGameEnds = true;
        if (bt7.equals(bt8) && bt7.equals(bt9) && !bt7.equals("")) isGameEnds = true;
        if (bt1.equals(bt4) && bt1.equals(bt7) && !bt1.equals("")) isGameEnds = true;
        if (bt2.equals(bt5) && bt2.equals(bt8) && !bt2.equals("")) isGameEnds = true;
        if (bt3.equals(bt6) && bt3.equals(bt9) && !bt3.equals("")) isGameEnds = true;
        if (bt1.equals(bt5) && bt1.equals(bt9) && !bt1.equals("")) isGameEnds = true;
        if (bt3.equals(bt5) && bt3.equals(bt7) && !bt3.equals("")) isGameEnds = true;
        
        if( XOCounter >= 9)
        {
            isGameEnds = true;
            isFirstPlayerTurn = true;
            XOCounter = 0;
        }
        
       
        
    }
    
   
    private void actionPerformed(ActionEvent e)
    {
        
        Button clickedButton = (Button) e.getSource();
        
        
        if( isGameEnds == false && clickedButton.getText().equals("") )
        {
            
            if (challengeComputer == true)
            {
                XOCounter++;
                isFirstPlayerTurn = true;
                clickedButton.setText("X");
                checkIfGameEnds();
                if(isGameEnds == false)
                {
                    for (Button boardButton : boardButtons) {
                        boardButton.removeEventHandler(ActionEvent.ACTION, eventHandler);
                    }
                    XOCounter++;
                    isFirstPlayerTurn = false;
                    for (;;) {
                        randomNumber = random.nextInt(9);
                        if (boardButtons[randomNumber].getText().equals(""))
                        {
                          
                            boardButtons[randomNumber].setText("O");
                            break;
                        }
                    }
                    checkIfGameEnds();
                    
                   
                    for (Button boardButton : boardButtons) {
                        boardButton.addEventHandler(ActionEvent.ACTION, eventHandler);
                    }
                }
            }
    
        } 
    }
    
    
    public GamePaneWComp() {
        boardPane.setPrefSize(300, 300);
        boardPane.setTranslateX(45);
        boardPane.setTranslateY(105);
       
        createGameBoard();
        getChildren().add(boardPane);
       
    }
    
}

