/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recordmenu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author DELL
 */
public class RecordController implements Initializable {
    
   
    private ListView<String> myListView;
    
    private Label label;
    
    String[] myRecord={"SAMI","",""};
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myListView.getItems().addAll(myRecord);
    }    
    
}
