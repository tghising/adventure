package adventure;

/**
 * EnterCommand class extends abstract class Command to enter another door
 * interconnected room from the current room
 *
 * @author tghising
 */
public class EnterCommand extends Command {

    // constructor  initialize to set argument to super class Command constructor
    public EnterCommand(String arg) {
        super(arg);
    }

    // override perform() to check the given room number is accessible from the current room or not : Override abstract method perform() in Command
    @Override
    public String perform(World world) {
        String commandPerform = "";

        if (world.accessible(super.argument)) {
            commandPerform = world.enter(super.argument);
        } else {
            commandPerform = String.format("cannot enter %s from here.\n", super.argument);
        }

        commandPerform += world.describe();

        return commandPerform;
    }
}
