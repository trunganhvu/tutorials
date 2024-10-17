package SingletonPattern;
public class DoubleCheckLockingSingleton {

    // volatile variable is saved to disk not as normal variable saved in CPU
    private static volatile DoubleCheckLockingSingleton instance;

    private DoubleCheckLockingSingleton() {
    }

    public static DoubleCheckLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("Double Check Locking Singleton " + instance.toString());
    }
}