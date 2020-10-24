package adventure;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXMLDocument controller is used to initialize the UI elements and
 * interconnect between UI and World
 *
 * @author tghising
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label heading;

    @FXML
    private TextArea output;

    @FXML
    private Label command;

    @FXML
    private TextField searchCommandTextField;

    @FXML
    private Button listCommandButton;

    @FXML
    private Button exitButton;

    private World map;

    private final String COMMANDS = String.format("Valid commands are:\n\tsearch \n\tdescribe \n\tenter n\n\tgoto n");

    @FXML
    void gameExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void listCommand(ActionEvent event) {
        output.setText(COMMANDS);
    }

    @FXML
    void searchCommand(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            searchCommandTextField.selectAll();

            Command commandType = createCommand(searchCommandTextField.getText());
            output.setText(commandType.perform(map));
        }
    }

    // method start game by initialize all world rooms and corresponding room and set Room 1 as current room
    public void startGame(World world) {
        map = world;
        map.start();
        String welcomeMessage = String.format("Welcome to your new Adventure\n\n");
        output.setText(welcomeMessage);
        // append current room description and doors
        output.appendText(map.describe());
        // append available commands
        output.appendText(COMMANDS);
    }

    // method to create type of command on the basis of given commands
    private Command createCommand(String searchCommand) {
        String[] commands = searchCommand.split("\\s");

        if (commands.length == 1 && commands[0].equalsIgnoreCase("describe")) {
            return new DescribeCommand();
        } else if (commands.length == 1 && commands[0].equalsIgnoreCase("search")) {
            return new SearchCommand();
        } else if (commands.length == 2 && commands[0].equalsIgnoreCase("enter") && commands[1].matches("[0-9]+")) {
            return new EnterCommand(commands[1]);
        } else if (commands.length == 2 && commands[0].equalsIgnoreCase("goto") && commands[1].matches("[0-9]+")) {
            return new GotoCommand(commands[1]);
        } else {
            return new UnknownCommand();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
