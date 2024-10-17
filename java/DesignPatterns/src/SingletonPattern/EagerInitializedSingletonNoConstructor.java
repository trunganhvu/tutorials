package SingletonPattern;

public class EagerInitializedSingletonNoConstructor {
    private static final EagerInitializedSingletonNoConstructor INSTANCE = new EagerInitializedSingletonNoConstructor();

    public static EagerInitializedSingletonNoConstructor getInstance() {
        return INSTANCE;
    }

    // Example method for demonstration
    public void showMessage(){
        System.out.println("Eager Initialized Singleton No Constructor " + INSTANCE.hashCode());
    }
}
