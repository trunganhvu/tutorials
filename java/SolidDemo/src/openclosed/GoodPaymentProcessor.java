package openclosed;

/**
 * OPEN for add more payment (add class payment type A,B,.. and easy to implement with new payment)
 * CLOSE for not modify old payment
 */
public class GoodPaymentProcessor {
    public void processPayment(GoodPaymentMethod paymentMethod) {
        paymentMethod.processPayment();
    }
}
