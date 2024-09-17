import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver extends User {
    private List<Ride> postedRides;
    private FeedbackService feedbackService;

    public Driver(String email, String password) {
        super(email, password); // Initialize with email and password only
        this.postedRides = new ArrayList<>();
        this.feedbackService = new FeedbackService();
    }

    public void postRide(Ride ride) {
        postedRides.add(ride);
    }

    public List<Ride> getPostedRides() {
        return postedRides;
    }

    @Override
    public void provideFeedback(Scanner scanner) {
        feedbackService.collectFeedback(this, scanner);
    }
}
