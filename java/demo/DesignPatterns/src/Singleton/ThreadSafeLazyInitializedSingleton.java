package Singleton;

public class ThreadSafeLazyInitializedSingleton {
    // volatile variable is saved to disk not as normal variable saved in CPU
    private static volatile ThreadSafeLazyInitializedSingleton instance;

    private ThreadSafeLazyInitializedSingleton() {
    }

    public static synchronized ThreadSafeLazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeLazyInitializedSingleton();
        }
        return instance;
    }

    public void showMessage(){
        System.out.println("Thread Safe Lazy Initialized Singleton " + instance.toString());
    }
}
