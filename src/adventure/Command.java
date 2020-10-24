package adventure;

/**
 * Command: abstract class-super class to determine the different user commands
 *
 * @author tghising
 */
public abstract class Command {

    protected String argument;

    // constructor
    public Command() {
    }

    public Command(String arg) {
        this.argument = arg;
    }

    // abstract method to perform commands/actions in adventure
    public abstract String perform(World world);
}
