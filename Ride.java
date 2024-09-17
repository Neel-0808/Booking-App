import java.util.ArrayList;
import java.util.List;

public class Ride {
    private static int idCounter = 1; // Static counter to generate unique IDs
    private int id;
    private String startPoint;
    private String destination;
    private String date;
    private String time;
    private int availableSeats;
    private Driver driver; // The driver who posted the ride
    private List<Rider> riders; // List of riders who have booked the ride

    public Ride(String startPoint, String destination, String date, String time, int availableSeats, Driver driver) {
        this.id = idCounter++;
        this.startPoint = startPoint;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
        this.driver = driver;
        this.riders = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public Driver getDriver() {
        return driver;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public void addRider(Rider rider) {
        riders.add(rider);
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Ride ID: " + id + ", Start Point: " + startPoint + ", Destination: " + destination +
               ", Date: " + date + ", Time: " + time + ", Available Seats: " + availableSeats +
               ", Driver: " + driver.getName();
    }
}
