package adventure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * World is Graph, it can keep track the user's current Location i.e. Room
 *
 * @author tghising
 */
public class World {

    private HashMap<String, Room> map = new HashMap<String, Room>(); // List of nodes (or rooms) in the WORLD

    private Room current;   //keeps track of the user’s current location

    // Derby DB url, username, and password
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/adventure";
    static final String USER_NAME = "root";
    static final String USER_PASSWORD = "root";

    private Connection connection; // Connection object creation
    private PreparedStatement selectAllRooms;
    private PreparedStatement selectAllEdgesByROOMID;

    // constructor load the map of Room's
    public World() {
        try {
            // Connection object creation
            connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, USER_PASSWORD);
            selectAllRooms = connection.prepareStatement("SELECT * FROM ROOMS");
            selectAllEdgesByROOMID = connection.prepareStatement("SELECT * FROM EDGES WHERE ROOM0 = ?");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }

        loadMap();
    }

    // load data to build the map of the world with roomID as the key. nodes are the rooms, edges are the doors
    public void loadMap() {
        // retrieved rooms from table ROOMS and added to HashMap map
        try {
            ResultSet roomResultSet = selectAllRooms.executeQuery();
            while (roomResultSet.next()) {
                String roomId = roomResultSet.getString("ROOMID");
                String roomDesc = roomResultSet.getString("ROOMDESC");
                String searchDesc = roomResultSet.getString("SEARCHDESC");
                Room eachRoom = new Room(roomId, roomDesc, searchDesc);

                // query to search all doors for the room with given roomId
                selectAllEdgesByROOMID.setString(1, roomId);
                ResultSet edgesResultSet = selectAllEdgesByROOMID.executeQuery();
                while (edgesResultSet.next()) {
                    // adding all three doors for the room with given roomId
                    eachRoom.addDoor(edgesResultSet.getString("ROOM1"));
                    eachRoom.addDoor(edgesResultSet.getString("ROOM2"));
                    eachRoom.addDoor(edgesResultSet.getString("ROOM3"));
                }

                map.put(roomId, eachRoom);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    // method to display world's all information in details
    public void displayMapData() {
        for (String roomId : map.keySet()) {
            System.out.println(map.get(roomId));
        }
    }

    // method to return current room search description
    public String getSearchDescription() {
        return current.getSearchDescription();
    }

    // method to describe the current room description
    public String describe() {
        return current.roomDescription();
    }

    // method to enter room with room number and set current room with given room number or id
    public String enter(String room) {
        current = map.get(room);
        return String.format("Entered room: %s\n", room);
    }

    // method to check whether room is exist or not
    public boolean exists(String room) {
        if (map.containsKey(room)) {
            return true;
        } else {
            return false;
        }
    }

    // check room accessible from current room or not
    public boolean accessible(String room) {
        if (current.hasDoor(room)) {
            return true;
        } else {
            return false;
        }
    }

    // method to start world with current room with room id 1
    public void start() {
        current = map.get("1");
    }
}
