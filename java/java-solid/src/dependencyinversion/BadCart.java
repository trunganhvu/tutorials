package dependencyinversion;


/**
 * Cart là module high level
 * Database, Logger, EmailSender là module low level
 * High level dependency on low level
 */
public class BadCart {
    public void Checkout(int orderId, int userId)
    {
        /*
        // Database, Logger, EmailSender là module low level
        Database db = new Database();
        db.Save(orderId);

        Logger log = new Logger();
        log.LogInfo("Order has been checkout");

        EmailSender es = new EmailSender();
        es.SendEmail(userId);

         */
    }
}
