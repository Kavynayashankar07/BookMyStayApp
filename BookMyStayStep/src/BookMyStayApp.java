/**
 * * MAIN CLASS
 Use Case 2: Basic Room Types & Static Availability
 Description:
 This abstract class represents a generic hotel room.
 It modets attributes tnat are intrinsic to a room type
 and remain constant regardless Of availability.
 Inventory-related concerns are intentionally excluded..
 @author kavynaya
 @version 2.0
 */

// CODE ->
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

    // Common method to display room details
    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Size      : " + size + " sq ft");
        System.out.println("Price     : $" + price);
    }
}


// Single Room Class
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 100);
    }
}
// Double Room Class
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 180);
    }
}
// Suite Room Class
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 600, 400);
    }
}
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Booking System");
        System.out.println("Hotel Booking System v2.0");
        System.out.println("Application Started Successfully");

        // Static availability variables (no data structures yet)
        int singleRoomAvailable = 10;
        int doubleRoomAvailable = 6;
        int suiteRoomAvailable = 2;

        // Creating room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("===== HOTEL ROOM AVAILABILITY =====\n");

        // Display Single Room
        single.displayRoomDetails();
        System.out.println("Available : " + singleRoomAvailable);
        System.out.println("----------------------------------");

        // Display Double Room
        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleRoomAvailable);
        System.out.println("----------------------------------");

        // Display Suite Room
        suite.displayRoomDetails();
        System.out.println("Available : " + suiteRoomAvailable);
        System.out.println("----------------------------------");

        System.out.println("\nApplication Terminated.");

    }
}