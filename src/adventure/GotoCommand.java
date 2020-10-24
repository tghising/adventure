package adventure;

/**
 * GotoCommand class extends abstract class Command to go to specific room in
 * the world
 *
 * @author tghising
 */
public class GotoCommand extends Command {

    // constructor  initialize to set argument to super class Command constructor
    public GotoCommand(String arg) {
        super(arg);
    }

    // override perform(), if the given room number is exists in the wold map then enter the specified room otherwise can't enter: Override abstract method perform() in Command
    @Override
    public String perform(World world) {
        String commandPerform = "";
        if (world.exists(super.argument)) {
            commandPerform = world.enter(super.argument);
            commandPerform += world.describe();
        } else {
            commandPerform = String.format("cannot enter %s room does not exist.", super.argument);
        }

        return commandPerform;
    }
}
