/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 10.1
 */

import java.util.HashMap;
import java.util.Stack;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;
    String roomId;
    boolean cancelled;

    Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.cancelled = false;
    }

    void displayReservation() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Room ID: " + roomId);
        System.out.println("Cancelled: " + cancelled);
        System.out.println();
    }
}

class RoomInventory {
    HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    void increaseAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    int getAvailability(String roomType) {
        return inventory.get(roomType);
    }

    void displayInventory() {
        System.out.println("Current Inventory");
        System.out.println("Single Room: " + inventory.get("Single Room"));
        System.out.println("Double Room: " + inventory.get("Double Room"));
        System.out.println("Suite Room: " + inventory.get("Suite Room"));
        System.out.println();
    }
}

class BookingHistory {
    HashMap<String, Reservation> reservations;

    BookingHistory() {
        reservations = new HashMap<>();
    }

    void addReservation(Reservation reservation) {
        reservations.put(reservation.reservationId, reservation);
    }

    Reservation getReservation(String reservationId) {
        return reservations.get(reservationId);
    }

    void displayHistory() {
        System.out.println("Booking History");
        System.out.println();
        for (Reservation reservation : reservations.values()) {
            reservation.displayReservation();
        }
    }
}

class CancellationService {
    Stack<String> releasedRoomIds;

    CancellationService() {
        releasedRoomIds = new Stack<>();
    }

    void cancelBooking(String reservationId, BookingHistory history, RoomInventory inventory) {
        Reservation reservation = history.getReservation(reservationId);

        if (reservation == null) {
            System.out.println("Cancellation failed: Reservation does not exist");
            return;
        }

        if (reservation.cancelled) {
            System.out.println("Cancellation failed: Reservation is already cancelled");
            return;
        }

        releasedRoomIds.push(reservation.roomId);
        inventory.increaseAvailability(reservation.roomType);
        reservation.cancelled = true;

        System.out.println("Booking cancelled successfully");
        System.out.println("Reservation ID: " + reservation.reservationId);
        System.out.println("Released Room ID: " + reservation.roomId);
        System.out.println("Updated Availability for " + reservation.roomType + ": " + inventory.getAvailability(reservation.roomType));
        System.out.println();
    }

    void displayRollbackStack() {
        System.out.println("Rollback Stack");
        while (!releasedRoomIds.isEmpty()) {
            System.out.println(releasedRoomIds.pop());
        }
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 10.1");
        System.out.println();

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        CancellationService cancellationService = new CancellationService();

        Reservation r1 = new Reservation("R101", "Aman", "Single Room", "SR1");
        Reservation r2 = new Reservation("R102", "Neha", "Double Room", "DR1");

        history.addReservation(r1);
        history.addReservation(r2);

        history.displayHistory();
        inventory.displayInventory();

        cancellationService.cancelBooking("R101", history, inventory);
        cancellationService.cancelBooking("R101", history, inventory);
        cancellationService.cancelBooking("R999", history, inventory);

        history.displayHistory();
        inventory.displayInventory();
        cancellationService.displayRollbackStack();
    }
}