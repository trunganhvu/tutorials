package SingletonPattern;

public class LazyInitializedSingleton {

    private static LazyInitializedSingleton INSTANCE;

    private LazyInitializedSingleton() {
    }

    public static LazyInitializedSingleton getInstance() {
        // Sẽ có trường hợp 2 thread chạy đồng thời sẽ tạo ra 2 INSTANCE
        // Khi thực hiện sử dụng getInstance() trong thread như dưới đây
        // Runnable task = () -> {
        //    LazyInitializedSingleton singleton = LazyInitializedSingleton.getInstance();
        //    System.out.println(singleton.hashCode());
        //};
        //Thread thread1 = new Thread(task);
        //Thread thread2 = new Thread(task);
        //thread1.start();
        //thread2.start();
        if (INSTANCE == null) {
            INSTANCE = new LazyInitializedSingleton();
        }
        return INSTANCE;
    }

    public void showMessage(){
        System.out.println("Lazy Initialized Singleton " + INSTANCE.toString());
    }
}
