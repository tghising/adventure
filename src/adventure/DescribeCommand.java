package adventure;

/**
 * DescribeCommand class extends abstract class Command to describe the current
 * room details
 *
 * @author tghising
 */
public class DescribeCommand extends Command {

    // constructor  initialize to super class Command default constructor
    public DescribeCommand() {
        super();
    }

    // override perform() to describe current room details: Override abstract method perform() in Command
    @Override
    public String perform(World world) {
        return world.describe();
    }
}
