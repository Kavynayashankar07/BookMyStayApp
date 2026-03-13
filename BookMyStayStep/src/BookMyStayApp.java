import java.util.HashMap;
import java.util.Map;
import java.util.*;


// ---------------- ROOM DOMAIN MODEL ----------------
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


// ---------------- INVENTORY ----------------
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}


// ---------------- RESERVATION (BOOKING REQUEST) ----------------
class Reservation {

    private String guestName;
    private String roomType;
    private int nights;

    public Reservation(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public void displayReservation() {
        System.out.println("Guest Name : " + guestName);
        System.out.println("Room Type  : " + roomType);
        System.out.println("Nights     : " + nights);
        System.out.println("------------------------------");
    }
}


// ---------------- BOOKING REQUEST QUEUE ----------------
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("\nBooking request added to queue.");
    }

    // Show queued requests
    public void displayQueue() {

        System.out.println("\n===== BOOKING REQUEST QUEUE =====");

        if (requestQueue.isEmpty()) {
            System.out.println("No pending booking requests.");
            return;
        }

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}



public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Booking System");
        System.out.println("Hotel Booking System v5.0");
        System.out.println("Application Started Successfully");

        Scanner scanner = new Scanner(System.in);

        // Inventory (read only here)
        RoomInventory inventory = new RoomInventory();

        // Booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        System.out.println("===== BOOK MY STAY =====");
        System.out.println("Submit Booking Request\n");

        // Guest input
        System.out.print("Enter Guest Name: ");
        String name = scanner.nextLine();

        System.out.println("\nSelect Room Type:");
        System.out.println("1. Single Room");
        System.out.println("2. Double Room");
        System.out.println("3. Suite Room");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String roomType = "";

        switch (choice) {
            case 1:
                roomType = "Single Room";
                break;
            case 2:
                roomType = "Double Room";
                break;
            case 3:
                roomType = "Suite Room";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.print("Number of nights: ");
        int nights = scanner.nextInt();

        // Create reservation request
        Reservation reservation = new Reservation(name, roomType, nights);

        // Add request to queue
        bookingQueue.addRequest(reservation);

        // Display queue
        bookingQueue.displayQueue();

        System.out.println("\nRequest stored. Waiting for allocation system.");

        scanner.close();
    }
}