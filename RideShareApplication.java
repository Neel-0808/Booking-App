import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RideShareApplication {
    private static RideService rideService = new RideService();
    private static User currentUser;
    private static Map<String, User> users = new HashMap<>(); // A simple in-memory user store

    public static void main(String[] args) {
        initializeDefaultUsers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                showRoleSelectionMenu(scanner);
            } else {
                showMainMenu(scanner);
            }
        }
    }

    private static void initializeDefaultUsers() {
        // Initialize users without default names
        users.put("rider@example.com", new Rider("rider@example.com", "password"));
        users.put("driver@example.com", new Driver("driver@example.com", "password"));
        users.put("admin@example.com", new Admin("Admin", "admin@example.com", "password", rideService));
    }

    private static void showRoleSelectionMenu(Scanner scanner) {
        System.out.println("Select User Role:");
        System.out.println("1. Login as Rider");
        System.out.println("2. Login as Driver");
        System.out.println("3. Login as Admin");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> currentUser = loginUser(scanner, "Rider");
            case 2 -> currentUser = loginUser(scanner, "Driver");
            case 3 -> currentUser = loginUser(scanner, "Admin");
            default -> System.out.println("Invalid choice, please try again.");
        }
    }

    private static User loginUser(Scanner scanner, String role) {
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Enter your name:");
            String name = scanner.nextLine();
            user.setName(name); // Set the user's name
            return user;
        } else {
            System.out.println("Invalid email or password.");
            return null;
        }
    }

    private static void showMainMenu(Scanner scanner) {
        if (currentUser instanceof Admin) {
            showAdminMenu(scanner);
        } else if (currentUser instanceof Rider) {
            showRiderMenu(scanner);
        } else if (currentUser instanceof Driver) {
            showDriverMenu(scanner);
        }
    }

    private static void showAdminMenu(Scanner scanner) {
        Admin admin = (Admin) currentUser;
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Users");
            System.out.println("2. Monitor Rides");
            System.out.println("3. Generate Reports");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> admin.manageUsers(scanner, users);
                case 2 -> admin.monitorRides();
                case 3 -> admin.generateReports();
                case 4 -> {
                    currentUser = null; // Logout
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void showRiderMenu(Scanner scanner) {
        Rider rider = (Rider) currentUser;
        while (true) {
            System.out.println("Rider Menu:");
            System.out.println("1. View Ride History");
            System.out.println("2. Search and Book Rides");
            System.out.println("3. Provide Feedback");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> rider.viewRideHistory();
                case 2 -> rideService.displayAvailableRides(rider, scanner);
                case 3 -> rider.provideFeedback(scanner);
                case 4 -> {
                    currentUser = null; // Logout
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void showDriverMenu(Scanner scanner) {
        Driver driver = (Driver) currentUser;
        while (true) {
            System.out.println("Driver Menu:");
            System.out.println("1. Post a Ride");
            System.out.println("2. View Ride Requests");
            System.out.println("3. Provide Feedback");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> rideService.postRide(driver, scanner);
                case 2 -> rideService.viewRideRequests(driver);
                case 3 -> driver.provideFeedback(scanner);
                case 4 -> {
                    currentUser = null; // Logout
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
