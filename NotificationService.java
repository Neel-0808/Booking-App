public class NotificationService {

    public void sendRideRequestNotification(Driver driver) {
        System.out.println("Notification: A new ride request has been sent to " + driver.getName());
    }

    public void sendRideConfirmationNotification(Rider rider) {
        System.out.println("Notification: Your ride has been confirmed. Thank you for using RideShare, " + rider.getName());
    }

    public void sendPaymentNotification(Rider rider, double fare) {
        System.out.println("Notification: Payment of $" + fare + " has been processed for " + rider.getName());
    }

    public void sendFeedbackRequestNotification(User user) {
        System.out.println("Notification: Please provide feedback for your recent ride, " + user.getName());
    }
}
