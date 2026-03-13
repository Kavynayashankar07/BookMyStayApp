import java.util.*;

// Booking Request class
class BookingRequest {
    String customerName;
    String roomType;

    public BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Inventory Service
class InventoryService {

    private HashMap<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 3);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public boolean checkAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrementInventory(String roomType) {
        int count = inventory.get(roomType);
        inventory.put(roomType, count - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking Service
class BookingService {

    private Queue<BookingRequest> requestQueue;
    private HashMap<String, Set<String>> allocatedRooms;
    private Set<String> allRoomIds;
    private InventoryService inventory;

    public BookingService(InventoryService inventory) {
        this.inventory = inventory;
        requestQueue = new LinkedList<>();
        allocatedRooms = new HashMap<>();
        allRoomIds = new HashSet<>();
    }

    // Add booking request
    public void addRequest(String name, String roomType) {
        requestQueue.add(new BookingRequest(name, roomType));
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {
        String prefix = roomType.substring(0, 2).toUpperCase();
        String roomId;

        do {
            roomId = prefix + (100 + new Random().nextInt(900));
        } while (allRoomIds.contains(roomId));

        return roomId;
    }

    // Process booking queue
    public void processBookings() {

        while (!requestQueue.isEmpty()) {

            BookingRequest request = requestQueue.poll();
            String roomType = request.roomType;

            System.out.println("\nProcessing booking for " + request.customerName);

            if (inventory.checkAvailability(roomType)) {

                String roomId = generateRoomId(roomType);

                allRoomIds.add(roomId);

                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                allocatedRooms.get(roomType).add(roomId);

                inventory.decrementInventory(roomType);

                System.out.println("Reservation Confirmed!");
                System.out.println("Room Type: " + roomType);
                System.out.println("Allocated Room ID: " + roomId);

            } else {
                System.out.println("Reservation Failed: No " + roomType + " available.");
            }
        }
    }

    public void displayAllocations() {
        System.out.println("\nRoom Allocations:");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Hotel Booking System - Use Case 6");
        System.out.println("Reservation Confirmation & Room Allocation");

        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);

        // Add booking requests (FIFO Queue)
        bookingService.addRequest("Kavy", "Single Room");
        bookingService.addRequest("Monish", "Double Room");
        bookingService.addRequest("Sajani", "Single Room");
        bookingService.addRequest("Abishek", "Suite Room");
        bookingService.addRequest("Jisvin", "Suite Room");

        // Process bookings
        bookingService.processBookings();

        // Show allocations
        bookingService.displayAllocations();

        // Show updated inventory
        inventory.displayInventory();

        System.out.println("\nProgram Completed.");
    }
}