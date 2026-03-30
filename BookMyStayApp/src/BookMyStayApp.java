
/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 6.1
 */

import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {
    Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation r) {
        queue.add(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class RoomInventory {
    HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", Integer.valueOf(2));
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return inventory.get(type);
    }

    void decreaseAvailability(String type) {
        inventory.put(type, Integer.valueOf(inventory.get(type) - 1));
    }
}

class BookingService {

    HashMap<String, Set<String>> allocatedRooms;
    Set<String> usedRoomIds;

    BookingService() {
        allocatedRooms = new HashMap<>();
        usedRoomIds = new HashSet<>();
    }

    void allocateRoom(Reservation r, RoomInventory inventory) {

        if (inventory.getAvailability(r.roomType) > 0) {

            String roomId = r.roomType.substring(0, 2).toUpperCase() + (usedRoomIds.size() + 1);

            if (!usedRoomIds.contains(roomId)) {

                usedRoomIds.add(roomId);

                allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());
                allocatedRooms.get(r.roomType).add(roomId);

                inventory.decreaseAvailability(r.roomType);

                System.out.println("Reservation Confirmed for " + r.guestName);
                System.out.println("Room Type: " + r.roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println();
            }

        } else {
            System.out.println("No rooms available for " + r.guestName + " (" + r.roomType + ")");
            System.out.println();
        }
    }
}

 class UseCase6RoomAllocationService {
    public static void main(String[] args) {

        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 6.1");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService();

        queue.addRequest(new Reservation("Aman", "Single Room"));
        queue.addRequest(new Reservation("Neha", "Double Room"));
        queue.addRequest(new Reservation("Rahul", "Suite Room"));
        queue.addRequest(new Reservation("Priya", "Single Room"));

        while (queue.hasRequests()) {
            Reservation r = queue.getNextRequest();
            bookingService.allocateRoom(r, inventory);
        }
    }
}
