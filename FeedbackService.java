import java.util.Scanner;

public class FeedbackService {

    public void collectFeedback(User user, Scanner scanner) {
        System.out.println("Please provide your feedback, " + user.getName() + ": ");
        String feedback = scanner.nextLine();
        System.out.println("Thank you for your feedback!");
    }

    public void analyzeFeedback(String feedback) {
        // Simple feedback analysis logic
        if (feedback.toLowerCase().contains("good")) {
            System.out.println("Positive feedback received.");
        } else if (feedback.toLowerCase().contains("bad")) {
            System.out.println("Negative feedback received.");
        } else {
            System.out.println("Neutral feedback received.");
        }
    }
}
