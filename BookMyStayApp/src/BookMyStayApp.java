/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 5.1
 */

import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void displayReservation() {
        System.out.println("Guest: " + guestName + " requested " + roomType);
    }
}

class BookingRequestQueue {
    Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    void displayRequests() {
        for (Reservation r : queue) {
            r.displayReservation();
        }
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {

        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 5.1");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Aman", "Single Room");
        Reservation r2 = new Reservation("Neha", "Double Room");
        Reservation r3 = new Reservation("Rahul", "Suite Room");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        System.out.println();
        System.out.println("Booking Requests in Queue:");
        bookingQueue.displayRequests();
    }
}