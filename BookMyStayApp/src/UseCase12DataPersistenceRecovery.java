/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 12.1
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void displayReservation() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println();
    }
}

class SystemState implements Serializable {
    HashMap<String, Integer> inventory;
    List<Reservation> bookingHistory;

    SystemState() {
        inventory = new HashMap<>();
        bookingHistory = new ArrayList<>();
    }
}

class PersistenceService {
    String fileName = "hotel_data.ser";

    void saveState(SystemState state) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(state);
            outputStream.close();
            System.out.println("System state saved successfully");
        } catch (IOException e) {
            System.out.println("Error while saving system state");
        }
    }

    SystemState loadState() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            SystemState state = (SystemState) inputStream.readObject();
            inputStream.close();
            System.out.println("System state restored successfully");
            return state;
        } catch (FileNotFoundException e) {
            System.out.println("Persistence file not found, starting with new system state");
            return new SystemState();
        } catch (IOException e) {
            System.out.println("Persistence file is corrupted, starting with new system state");
            return new SystemState();
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to restore saved data, starting with new system state");
            return new SystemState();
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 12.1");
        System.out.println();

        PersistenceService persistenceService = new PersistenceService();

        SystemState state = new SystemState();
        state.inventory.put("Single Room", 2);
        state.inventory.put("Double Room", 1);
        state.inventory.put("Suite Room", 1);

        state.bookingHistory.add(new Reservation("R101", "Aman", "Single Room"));
        state.bookingHistory.add(new Reservation("R102", "Neha", "Double Room"));

        System.out.println("Saving current system state...");
        persistenceService.saveState(state);

        System.out.println();
        System.out.println("Recovering system state after restart...");
        SystemState recoveredState = persistenceService.loadState();

        System.out.println();
        System.out.println("Recovered Inventory");
        System.out.println("Single Room: " + recoveredState.inventory.getOrDefault("Single Room", 0));
        System.out.println("Double Room: " + recoveredState.inventory.getOrDefault("Double Room", 0));
        System.out.println("Suite Room: " + recoveredState.inventory.getOrDefault("Suite Room", 0));

        System.out.println();
        System.out.println("Recovered Booking History");
        for (Reservation reservation : recoveredState.bookingHistory) {
            reservation.displayReservation();
        }
    }
}