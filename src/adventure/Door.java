package adventure;

/**
 * Door is edges in the graph, it has direction(either both way or one way)
 *
 * @author tghising
 */
public class Door {

    private final String FROM; // the “from” room – this will be the current room
    private final String TO; // the “to” room – this is the room that this door will take you to

    public Door(String from, String to) {
        this.FROM = from;
        this.TO = to;
    }

    public String getTo() {
        return TO;
    }

    @Override
    public String toString() {
        return String.format("<door from %s to %s>\n", FROM, TO);
    }
}
