import java.util.ArrayList;
import java.util.List;

public class RideMatchingAlgorithm {

    public List<Ride> matchRides(String destination, String date, String time, List<Ride> availableRides) {
        List<Ride> matchedRides = new ArrayList<>();

        for (Ride ride : availableRides) {
            // Simple matching logic based on destination, date, and time
            if (ride.toString().contains(destination) && ride.toString().contains(date) && ride.toString().contains(time)) {
                matchedRides.add(ride);
            }
        }
        return matchedRides;
    }
}
