/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynev;

import gameBoard.DisplayRecord;
import java.awt.event.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import tictactoe.TicTacToe;

/**
 *
 * @author Orignal Vip
 */
public class Mynav {

    public static Scene scene;
    public static Stage stage;
    static String image = TicTacToe.class.getResource("app.jpg").toExternalForm();

    public static void setRootStyle(Parent distinationRoot) {
        distinationRoot.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-size: 100% 100%;"
                + "-fx-background-position: center center;"
        );
    }

    public static void navigateTo(Parent distinationRoot, ActionEvent event) {

        setRootStyle(distinationRoot);
        scene = new Scene(distinationRoot);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showScene();
    }

    public static void navigateTo(Parent distinationRoot) {
        setRootStyle(distinationRoot);
        scene = new Scene(distinationRoot);
        showScene();
    }

    // navigate by event parameter
    public static void navigateTo(Parent distinationRoot, Event event) {
        setRootStyle(distinationRoot);
        scene = new Scene(distinationRoot);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showScene();
    }

    // navigate by mouse event
    public static void navigateTo(Parent distinationRoot, MouseEvent event) {
        setRootStyle(distinationRoot);
        scene = new Scene(distinationRoot);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showScene();
    }

    // navigate by stage parameter
    public static void navigateTo(Parent distinationRoot, Stage currentStage) {
        setRootStyle(distinationRoot);
        scene = new Scene(distinationRoot);
        stage = currentStage;
        showScene();
    }

    private static void showScene() {
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void navigateTo(DisplayRecord displayRecord, Window window) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
