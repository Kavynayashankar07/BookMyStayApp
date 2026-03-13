import java.util.HashMap;
import java.util.Map;
import java.util.*;


abstract class Room {

    private String roomType;
    private int beds;
    private int size;
    private double price;

    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sq ft");
        System.out.println("Price     : $" + price);
    }
}


// Concrete Room Types
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 100);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 180);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 600, 400);
    }
}


// Centralized Inventory Manager
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 2);
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Write operation (not used by search)
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Returns all availability data
    public Map<String, Integer> getAllAvailability() {
        return inventory;
    }
}


// Search Service (Read-only access)
class RoomSearchService {

    private RoomInventory inventory;
    private List<Room> roomCatalog;

    public RoomSearchService(RoomInventory inventory, List<Room> roomCatalog) {
        this.inventory = inventory;
        this.roomCatalog = roomCatalog;
    }

    // Search available rooms
    public void searchAvailableRooms() {

        System.out.println("\n===== AVAILABLE ROOMS =====\n");

        for (Room room : roomCatalog) {

            int available = inventory.getAvailability(room.getRoomType());

            // Validation: show only rooms with availability
            if (available > 0) {

                room.displayRoomDetails();
                System.out.println("Available : " + available);
                System.out.println("----------------------------------");

            }
        }
    }
}



public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Booking System");
        System.out.println("Hotel Booking System v4.0");
        System.out.println("Application Started Successfully");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Room catalog
        List<Room> rooms = new ArrayList<>();
        rooms.add(single);
        rooms.add(doubleRoom);
        rooms.add(suite);

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize search service
        RoomSearchService searchService =
                new RoomSearchService(inventory, rooms);

        System.out.println("Guest is searching for available rooms...");

        // Perform read-only search
        searchService.searchAvailableRooms();

        System.out.println("\nSystem state unchanged. Application terminated.");
    }
}