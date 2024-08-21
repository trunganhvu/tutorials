package dependencyinversion;

/**
 * DIP easy to use in spring is using DI
 */
public class GoodCart {
    /**
     * Interface
     */
    public interface IDatabase {
        void Save(int orderId);
    }
    public interface ILogger {
        void LogInfo(String info);
    }
    public interface IEmailSender {
        void SendEmail(int userId);
    }

    /**
     * Modules implement Interfaces
     */
    public class Logger implements ILogger {
        public void LogInfo(String info) {}
    }
    public class Database implements IDatabase {
        public void Save(int orderId) {}
    }
    public class EmailSender implements IEmailSender {
        public void SendEmail(int userId) {}
    }

    // Hàm checkout mới sẽ như sau

    /**
     * High level dependency on interface (instance of
     * @param orderId
     * @param userId
     */
    public void Checkout(int orderId, int userId) {
        // Nếu muốn thay đổi database, logger ta chỉ cần thay dòng code dưới
        // Các Module XMLDatabase, SQLDatabase phải implement IDatabase
        //IDatabase db = new XMLDatabase();
        //IDatebase db = new SQLDatabase();
        IDatabase db = new Database();
        db.Save(orderId);

        ILogger log = new Logger();
        log.LogInfo("Order has been checkout");

        IEmailSender es = new EmailSender();
        es.SendEmail(userId);
    }
}

