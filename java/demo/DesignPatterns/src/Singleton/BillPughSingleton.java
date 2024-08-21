package Singleton;

public class BillPughSingleton {

    private BillPughSingleton() {
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Static nested class to CAN access and SingletonHelper is not loading when BillPughSingleton loading
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public void showMessage(){
        System.out.println("Double Check Locking Singleton " + SingletonHelper.INSTANCE.toString());
    }
}