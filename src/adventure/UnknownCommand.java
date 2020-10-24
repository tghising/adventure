package adventure;

/**
 * UnknownCommand class extends abstract class Command to handle unknown
 * commands in the world
 *
 * @author tghising
 */
public class UnknownCommand extends Command {

    // constructor  initialize to super class Command default constructor
    public UnknownCommand() {
        super();
    }

    // override perform() to display the unknown command from the user : Override abstract method perform() in Command
    @Override
    public String perform(World world) {
        return String.format("unknown command");
    }
}
