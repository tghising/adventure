package adventure;

/**
 * SearchCommand class extends abstract class Command to search the description
 * of the current room
 *
 * @author tghising
 */
public class SearchCommand extends Command {

    // constructor  initialize to super class Command default constructor
    public SearchCommand() {
        super();
    }

    // override perform() to show search description of current room details: Override abstract method perform() in Command
    @Override
    public String perform(World world) {
        return world.getSearchDescription();
    }
}
