import java.util.HashMap;
import java.util.Map;

// Abstract Room class (Domain Model)
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
        System.out.println("Beds : " + beds);
        System.out.println("Size : " + size + " sq ft");
        System.out.println("Price : $" + price);
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

    // Constructor initializes inventory
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 2);
    }

    // Retrieve availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display entire inventory
    public void displayInventory() {
        System.out.println("\n===== CURRENT ROOM INVENTORY =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
        System.out.println("----------------------------------");
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Welcome to Hotel Booking System");
        System.out.println("Hotel Booking System v3.0");
        System.out.println("Application Started Successfully");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        System.out.println("\n===== ROOM DETAILS =====\n");

        single.displayRoomDetails();
        System.out.println("Available : " + inventory.getAvailability(single.getRoomType()));
        System.out.println("----------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + inventory.getAvailability(doubleRoom.getRoomType()));
        System.out.println("----------------------------------");

        suite.displayRoomDetails();
        System.out.println("Available : " + inventory.getAvailability(suite.getRoomType()));
        System.out.println("----------------------------------");

        // Display complete inventory
        inventory.displayInventory();

        System.out.println("\nApplication Terminated.");
    }
}