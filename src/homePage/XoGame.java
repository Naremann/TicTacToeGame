package homePage;
//
import gameBoard.PlayWithPc;
import gameBoard.PlayerNamesUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static mynev.Mynav.stage;
import homePage.XOgameUI;
//
///**
// *
// * @author DELL


// */
public class XoGame extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new XOgameUI();
        String image = XOgameUI.class.getResource("bkdnd.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); "+
                "-fx-background-size: 100% 100%;"+
                 "-fx-background-position: center center;");
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

     public static void main(String[] args) {
        launch(args);
    }

  
    }  
