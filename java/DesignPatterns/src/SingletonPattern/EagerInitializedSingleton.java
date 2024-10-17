package SingletonPattern;

public class EagerInitializedSingleton {

    private static final EagerInitializedSingleton INSTANCE = new EagerInitializedSingleton();

    private EagerInitializedSingleton() {

    }

    public static EagerInitializedSingleton getInstance() {
        return INSTANCE;
    }

    // Example method for demonstration
    public void showMessage(){
        System.out.println("Eager Initialized Singleton " + INSTANCE.hashCode());
    }
}