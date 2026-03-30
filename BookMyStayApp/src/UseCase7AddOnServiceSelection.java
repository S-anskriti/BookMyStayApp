/**
 * Book My Stay App
 * Hotel Booking Management System
 * @author Sanskriti
 * @version 7.1
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class AddOnService {
    String serviceName;
    double cost;

    AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    HashMap<String, List<AddOnService>> reservationServices;

    AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    void addService(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
    }

    void displayServices(String reservationId) {
        if (reservationServices.containsKey(reservationId)) {
            System.out.println("Reservation ID: " + reservationId);
            System.out.println("Selected Add-On Services:");
            for (AddOnService service : reservationServices.get(reservationId)) {
                System.out.println(service.serviceName + " - " + service.cost);
            }
        }
    }

    double calculateTotalAdditionalCost(String reservationId) {
        double total = 0;
        if (reservationServices.containsKey(reservationId)) {
            for (AddOnService service : reservationServices.get(reservationId)) {
                total = total + service.cost;
            }
        }
        return total;
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 7.1");

        String reservationId = "R101";

        AddOnService service1 = new AddOnService("Breakfast", 500);
        AddOnService service2 = new AddOnService("Airport Pickup", 1200);
        AddOnService service3 = new AddOnService("Spa Access", 1500);

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(reservationId, service1);
        manager.addService(reservationId, service2);
        manager.addService(reservationId, service3);

        System.out.println();
        manager.displayServices(reservationId);
        System.out.println("Total Additional Cost: " + manager.calculateTotalAdditionalCost(reservationId));
    }
}