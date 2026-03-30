/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 11.1
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingQueue {
    Queue<Reservation> queue;

    BookingQueue() {
        queue = new LinkedList<>();
    }

    synchronized void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    synchronized Reservation getNextRequest() {
        if (!queue.isEmpty()) {
            return queue.poll();
        }
        return null;
    }

    synchronized boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class RoomInventory {
    HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    synchronized boolean bookRoom(String roomType) {
        if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {
            inventory.put(roomType, inventory.get(roomType) - 1);
            return true;
        }
        return false;
    }

    synchronized void displayInventory() {
        System.out.println("Final Inventory Status");
        System.out.println("Single Room: " + inventory.get("Single Room"));
        System.out.println("Double Room: " + inventory.get("Double Room"));
        System.out.println("Suite Room: " + inventory.get("Suite Room"));
    }
}

class ConcurrentBookingProcessor extends Thread {
    BookingQueue bookingQueue;
    RoomInventory roomInventory;

    ConcurrentBookingProcessor(BookingQueue bookingQueue, RoomInventory roomInventory, String threadName) {
        super(threadName);
        this.bookingQueue = bookingQueue;
        this.roomInventory = roomInventory;
    }

    public void run() {
        while (true) {
            Reservation reservation = bookingQueue.getNextRequest();

            if (reservation == null) {
                break;
            }

            boolean booked = roomInventory.bookRoom(reservation.roomType);

            if (booked) {
                System.out.println(getName() + " confirmed booking for " + reservation.guestName + " in " + reservation.roomType);
            } else {
                System.out.println(getName() + " could not book " + reservation.roomType + " for " + reservation.guestName);
            }
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 11.1");
        System.out.println();

        BookingQueue bookingQueue = new BookingQueue();
        RoomInventory roomInventory = new RoomInventory();

        bookingQueue.addRequest(new Reservation("Aman", "Single Room"));
        bookingQueue.addRequest(new Reservation("Neha", "Double Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Single Room"));
        bookingQueue.addRequest(new Reservation("Priya", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Karan", "Single Room"));

        ConcurrentBookingProcessor t1 = new ConcurrentBookingProcessor(bookingQueue, roomInventory, "Thread-1");
        ConcurrentBookingProcessor t2 = new ConcurrentBookingProcessor(bookingQueue, roomInventory, "Thread-2");
        ConcurrentBookingProcessor t3 = new ConcurrentBookingProcessor(bookingQueue, roomInventory, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println();
        roomInventory.displayInventory();
    }
}