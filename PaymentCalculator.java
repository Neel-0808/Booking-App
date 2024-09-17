public class PaymentCalculator {

    public double calculateFare(double baseFare, double distance) {
        double fare = baseFare + (distance * 0.5); // Example calculation, can be customized
        return fare;
    }

    public double splitFare(double totalFare, int numberOfRiders) {
        return totalFare / numberOfRiders;
    }
}
