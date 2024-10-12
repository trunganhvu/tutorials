package interfacesegregation;

import openclosed.GoodBankingPayment;
import openclosed.GoodPaymentProcessor;

public class MainInterface {
    public static void main(String[] args) {
        // Like a OpenClose: A client should never be forced to implement an interface that it doesn’t use,
        // or clients shouldn’t be forced to depend on methods they do not use.

        // Good => PaymentProcessor implement only payment interface (not have get information, get history)
        GoodPaymentProcessor goodPaymentProcessor = new GoodPaymentProcessor();
        goodPaymentProcessor.processPayment(new GoodBankingPayment());

        // Bad => PaymentProcessor have a function not mean is payment

        // ----------Other explain----------------
        // interface Worker {work(), eat()}
        // Robot, Human implements Worker but Robot needn't eat()
        //
        // Solutions: crate new interface Eatable
        // Robot implements Worker
        // Human implements Worker, Eatable
    }


}
