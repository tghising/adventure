package adventure;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import static javafx.application.Application.launch;

/**
 * Adventure is main method() class for Adventure game
 *
 * @author tghising
 */
public class Adventure extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // loading FXMLDocument for GUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));

        Parent root = loader.load();

        // object creation of World
        World map = new World();
        System.out.println("Data in Map:");
        map.displayMapData();

        // loading the FXMLDocument controller
        FXMLDocumentController controller = loader.getController();

        // call bind() method by passing the ContactList and display first contact record in the textarea
        controller.startGame(map);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
