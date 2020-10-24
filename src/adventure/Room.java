package adventure;

import java.util.ArrayList;

/**
 * Room is Nodes in the graph which has doors(two way or one way door)
 *
 * @author tghising
 */
public class Room {

    private String roomID; //the unique room id represented as a String
    //    private String initialDescription; //the initial room description
    private String description; //the initial room description
    private String searchDescription; //the more detailed description after
    //the user searches the room
    private ArrayList<Door> doors = new ArrayList<Door>(); //a list of all the doors (or graph edges)

    // constructor to initialize the room id, description and search description
    public Room(String id, String desc, String searchDesc) {
        this.roomID = id;
        this.description = desc;
        this.searchDescription = searchDesc;
    }

    // void method to add doors in the room
    public void addDoor(String to) {
        doors.add(new Door(this.roomID, to));
    }

    // boolean method to check the given door number is available in the current room or not.
    public boolean hasDoor(String door) {
        for (int i = 0; i < doors.size(); i++) {
            if (doors.get(i).getTo().equals(door)) {
                return true;
            }
        }
        return false;
    }

    // method to return room search description
    public String getSearchDescription() {
        return searchDescription;
    }

    // method to return room description as well as the list of doors that are accessible from that room
    public String roomDescription() {
        String doorsInformation = new String();
        for (int i = 0; i < doors.size(); i++) {
            doorsInformation += doors.get(i).toString();
        }

        return String.format("You are in room %s \n\n%s \n\nYou can see the following doors:\n%s\n", roomID, description, doorsInformation);
    }

    @Override
    public String toString() {
        String doorsInformation = new String();
        for (int i = 0; i < doors.size(); i++) {
            doorsInformation += doors.get(i).toString();
        }

        return String.format("You are in room %s \n\n%s \n%s \nYou can see the following doors:\n%s\n", roomID, description, searchDescription, doorsInformation);
    }
}
