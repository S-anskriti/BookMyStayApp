/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 9.1
 */

import java.util.HashMap;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }

    int getAvailability(String roomType) {
        return inventory.get(roomType);
    }

    void reduceAvailability(String roomType) throws InvalidBookingException {
        if (!isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }

        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

class InvalidBookingValidator {
    void validateBooking(String guestName, String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Room type does not exist: " + roomType);
        }

        if (inventory.getAvailability(roomType) < 0) {
            throw new InvalidBookingException("Invalid inventory state for: " + roomType);
        }
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 9.1");
        System.out.println();

        RoomInventory inventory = new RoomInventory();
        InvalidBookingValidator validator = new InvalidBookingValidator();

        String guestName1 = "Aman";
        String roomType1 = "Single Room";

        String guestName2 = "Neha";
        String roomType2 = "Deluxe Room";

        String guestName3 = "Rahul";
        String roomType3 = "Suite Room";

        try {
            validator.validateBooking(guestName1, roomType1, inventory);
            inventory.reduceAvailability(roomType1);
            System.out.println("Booking successful for " + guestName1 + " in " + roomType1);
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        try {
            validator.validateBooking(guestName2, roomType2, inventory);
            inventory.reduceAvailability(roomType2);
            System.out.println("Booking successful for " + guestName2 + " in " + roomType2);
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        try {
            validator.validateBooking(guestName3, roomType3, inventory);
            inventory.reduceAvailability(roomType3);
            System.out.println("Booking successful for " + guestName3 + " in " + roomType3);
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}
