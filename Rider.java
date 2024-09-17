import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rider extends User {
    private List<Ride> rideHistory;
    private FeedbackService feedbackService;

    public Rider(String email, String password) {
        super(email, password); // Initialize with email and password only
        this.rideHistory = new ArrayList<>();
        this.feedbackService = new FeedbackService();
    }

    public void viewRideHistory() {
        System.out.println("Ride History for " + getName() + ":");
        for (Ride ride : rideHistory) {
            System.out.println(ride);
        }
    }

    @Override
    public void provideFeedback(Scanner scanner) {
        feedbackService.collectFeedback(this, scanner);
    }

    public void addRideToHistory(Ride ride) {
        rideHistory.add(ride);
    }
}
