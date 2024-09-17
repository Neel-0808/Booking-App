import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Admin extends User {
    private RideService rideService;

    public Admin(String name, String email, String password, RideService rideService) {
        super(email, password);
        this.setName(name); // Set the admin's name
        this.rideService = rideService;
    }

    @Override
    public void provideFeedback(Scanner scanner) {
        System.out.println("Admin does not provide feedback.");
    }

    public void manageUsers(Scanner scanner, Map<String, User> users) {
        while (true) {
            System.out.println("User Management:");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Edit User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addUser(scanner, users);
                case 2 -> viewUsers(users);
                case 3 -> editUser(scanner, users);
                case 4 -> deleteUser(scanner, users);
                case 5 -> {
                    return; // Exit the user management menu
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addUser(Scanner scanner, Map<String, User> users) {
        System.out.println("Enter User Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        System.out.println("Enter User Role (Rider/Driver):");
        String role = scanner.nextLine().toLowerCase();

        User user;
        if (role.equals("rider")) {
            user = new Rider(email, password);
        } else if (role.equals("driver")) {
            user = new Driver(email, password);
        } else {
            System.out.println("Invalid role, user not added.");
            return;
        }

        user.setName(name); // Set the user's name
        users.put(email, user);
        System.out.println("User added successfully.");
    }

    private void viewUsers(Map<String, User> users) {
        System.out.println("List of Users:");
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users.values()) {
                System.out.println("Name: " + user.getName() + ", Role: " + user.getClass().getSimpleName() + ", Email: " + user.getEmail());
            }
        }
    }

    private void editUser(Scanner scanner, Map<String, User> users) {
        System.out.println("Enter User Email to Edit:");
        String email = scanner.nextLine();

        User userToEdit = users.get(email);

        if (userToEdit != null) {
            System.out.println("Enter New Name:");
            String newName = scanner.nextLine();
            userToEdit.setName(newName);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void deleteUser(Scanner scanner, Map<String, User> users) {
        System.out.println("Enter User Email to Delete:");
        String email = scanner.nextLine();

        User userToDelete = users.get(email);

        if (userToDelete != null) {
            users.remove(email);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void monitorRides() {
        System.out.println("Monitoring Rides:");

        List<Ride> rides = rideService.getAvailableRides();
        if (rides.isEmpty()) {
            System.out.println("No rides available to monitor.");
            return;
        }

        for (Ride ride : rides) {
            System.out.println("Ride ID: " + ride.getId());
            System.out.println("Destination: " + ride.getDestination());
            System.out.println("Date: " + ride.getDate());
            System.out.println("Time: " + ride.getTime());
            System.out.println("Available Seats: " + ride.getAvailableSeats());
            System.out.println("Driver: " + ride.getDriver().getName() + " (" + ride.getDriver().getEmail() + ")");
            System.out.println("Riders:");
            for (Rider rider : ride.getRiders()) {
                System.out.println(" - " + rider.getName() + " (" + rider.getEmail() + ")");
            }
            System.out.println(); // Add an extra line for readability
        }
    }

    public void generateReports() {
        System.out.println("Generating Reports:");

        List<Ride> rides = rideService.getAvailableRides();
        if (rides.isEmpty()) {
            System.out.println("No rides available to report.");
            return;
        }

        int totalRides = rides.size();
        int totalRiders = 0;
        int totalSeatsAvailable = 0;

        for (Ride ride : rides) {
            totalRiders += ride.getRiders().size();
            totalSeatsAvailable += ride.getAvailableSeats();
        }

        System.out.println("Total Rides: " + totalRides);
        System.out.println("Total Riders: " + totalRiders);
        System.out.println("Total Available Seats: " + totalSeatsAvailable);
    }
}
