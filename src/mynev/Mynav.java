/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynev;

import java.awt.event.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Orignal Vip
 */
public class Mynav {
    public static Scene scene;
    public static  Stage stage;
    // navigate by event action parameter
     public static void navigateTo(Parent distinationRoot, ActionEvent event){
        scene = new Scene(distinationRoot);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        showScene();
    }
        public static void navigateTo(Parent distinationRoot){
        scene = new Scene(distinationRoot);
        showScene();
    }
    
    // navigate by event parameter
    public static void navigateTo(Parent distinationRoot, Event event){
        scene = new Scene(distinationRoot);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        showScene();
    }
    // navigate by mouse event
     public static void navigateTo(Parent distinationRoot, MouseEvent event){
        scene = new Scene(distinationRoot);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        showScene();
    }
    
    // navigate by stage parameter
    public static void navigateTo(Parent distinationRoot, Stage currentStage){
        scene = new Scene(distinationRoot);
        stage = currentStage;
        showScene();
    }
   
    private static void showScene(){
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
}
    
    
}
