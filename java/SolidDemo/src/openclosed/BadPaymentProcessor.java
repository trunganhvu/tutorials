package openclosed;

/**
 * Every addition payment method use thìs class and not good for OPEN
 * and old method have modified -> not good for CLOSE
 */
public class BadPaymentProcessor {
    public void processCreditCardPayment() {
        // Logic xử lý thanh toán bằng thẻ tín dụng
    }

    public void processPayPalPayment() {
        // Logic xử lý thanh toán bằng PayPal
    }
}
