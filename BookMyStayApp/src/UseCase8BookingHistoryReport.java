/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 8.1
 */

import java.util.ArrayList;
import java.util.List;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;
    double totalAmount;

    Reservation(String reservationId, String guestName, String roomType, double totalAmount) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.totalAmount = totalAmount;
    }

    void displayReservation() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println();
    }
}

class BookingHistory {
    List<Reservation> reservations;

    BookingHistory() {
        reservations = new ArrayList<>();
    }

    void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    List<Reservation> getReservations() {
        return reservations;
    }
}

class BookingReportService {
    void displayBookingHistory(BookingHistory history) {
        System.out.println("Booking History");
        System.out.println();
        for (Reservation reservation : history.getReservations()) {
            reservation.displayReservation();
        }
    }

    void generateSummaryReport(BookingHistory history) {
        int totalBookings = history.getReservations().size();
        double totalRevenue = 0;

        for (Reservation reservation : history.getReservations()) {
            totalRevenue = totalRevenue + reservation.totalAmount;
        }

        System.out.println("Booking Summary Report");
        System.out.println("Total Confirmed Bookings: " + totalBookings);
        System.out.println("Total Revenue: " + totalRevenue);
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 8.1");
        System.out.println();

        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("R101", "Aman", "Single Room", 1500);
        Reservation r2 = new Reservation("R102", "Neha", "Double Room", 2500);
        Reservation r3 = new Reservation("R103", "Rahul", "Suite Room", 5000);

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        BookingReportService reportService = new BookingReportService();

        reportService.displayBookingHistory(history);
        reportService.generateSummaryReport(history);
    }
}