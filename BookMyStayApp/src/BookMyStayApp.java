/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 3.1
 */

import java.util.HashMap;

class RoomInventory {

    HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.get(roomType);
    }

    void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    void displayInventory() {
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Available: " + inventory.get(roomType));
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {

        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 3.1");

        RoomInventory inventory = new RoomInventory();

        System.out.println();
        inventory.displayInventory();
    }
}