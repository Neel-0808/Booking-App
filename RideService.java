import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RideService {
    private List<Ride> availableRides;
    private RideMatchingAlgorithm rideMatchingAlgorithm;
    private PaymentCalculator paymentCalculator;
    private NotificationService notificationService;

    public RideService() {
        this.availableRides = new ArrayList<>();
        this.rideMatchingAlgorithm = new RideMatchingAlgorithm();
        this.paymentCalculator = new PaymentCalculator();
        this.notificationService = new NotificationService();
    }

    public List<Ride> getAvailableRides() {
        return availableRides;
    }

    public void postRide(Driver driver, Scanner scanner) {
        System.out.println("Enter Starting Point:");
        String startPoint = scanner.nextLine();
        System.out.println("Enter Destination:");
        String destination = scanner.nextLine();
        System.out.println("Enter Date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        System.out.println("Enter Time (HH:MM):");
        String time = scanner.nextLine();
        System.out.println("Enter Number of Available Seats:");
        int availableSeats = scanner.nextInt();
        scanner.nextLine(); // consume newline
    
        Ride ride = new Ride(startPoint, destination, date, time, availableSeats, driver);
        availableRides.add(ride);
        System.out.println("Ride posted. Current number of rides: " + availableRides.size()); // Debug statement
        driver.postRide(ride);
    
        System.out.println("Ride posted successfully.");
    }
    
    public void displayAvailableRides(Rider rider, Scanner scanner) {
        if (availableRides.isEmpty()) {
            System.out.println("No rides available at the moment.");
        } else {
            System.out.println("Available Rides:");
            for (Ride ride : availableRides) {
                if (ride.getAvailableSeats() > 0) {
                    System.out.println(ride);
                }
            }
            System.out.println("Enter the Ride ID you want to book:");
            int rideId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Ride selectedRide = null;
            for (Ride ride : availableRides) {
                if (ride.getId() == rideId && ride.getAvailableSeats() > 0) {
                    selectedRide = ride;
                    break;
                }
            }

            if (selectedRide != null) {
                selectedRide.addRider(rider);
                selectedRide.setAvailableSeats(selectedRide.getAvailableSeats() - 1); // Decrease available seats
                rider.addRideToHistory(selectedRide);
                notificationService.sendRideRequestNotification(selectedRide.getDriver());
                notificationService.sendRideConfirmationNotification(rider);
                processPayment(rider);
            } else {
                System.out.println("Invalid Ride ID or no available seats.");
            }
        }
    }

    public void viewRideRequests(Driver driver) {
        List<Ride> driverRides = driver.getPostedRides();
        if (driverRides.isEmpty()) {
            System.out.println("No rides posted by this driver.");
        } else {
            System.out.println("Ride Requests for " + driver.getName() + ":");
            for (Ride ride : driverRides) {
                if (!ride.getRiders().isEmpty()) {
                    System.out.println("Ride ID: " + ride.getId() + " | Riders: ");
                    for (Rider rider : ride.getRiders()) {
                        System.out.println(" - " + rider.getName());
                    }
                }
            }
        }
    }

    private void processPayment(Rider rider) {
        double baseFare = 5.0;  // Base fare can be customized
        double distance = 10.0; // Example distance, can be fetched dynamically
        double totalFare = paymentCalculator.calculateFare(baseFare, distance);
        double riderFare = paymentCalculator.splitFare(totalFare, 1); // Assuming only one rider for simplicity
        notificationService.sendPaymentNotification(rider, riderFare);
    }
}
