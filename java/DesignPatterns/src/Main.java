import Builder.Immutable.BankAccountImmutable;
import Builder.Mutable.BankAccountMutable;
import Builder.Mutable.BankAccountMutableBuilderImpl;
import FactoryPattern.BeanCase.AppConfig;
import FactoryPattern.BeanCase.Product;
import FactoryPattern.EasyCase.Bank;
import FactoryPattern.EasyCase.BankFactory;
import FactoryPattern.EasyCase.BankType;
import Singleton.*;

import java.io.*;
import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) {

        // Builder (Spring use as @Builder and BankObject.builder())
        builderPattern();

        // Factory (Spring use as @Bean to config can return new instant of project, and
        //          this new instant can call in Service, Controller is Dependency injection)
        factoryPattern();

        singletonPattern();
    }

    /**
     * Builder
     */
    static void builderPattern() {
        System.out.println("-----------Builder pattern-----------");
        // Builder with immuable attribute
        BankAccountImmutable newAccount = new BankAccountImmutable
                .BankAccountBuilder("name1", "111122")
                .email("test@mail.com")
                .newsletter(true)
                .build();
        System.out.println(newAccount);

        // Builder with mutabl attribute
        BankAccountMutable accountMutable = new BankAccountMutableBuilderImpl()
                .name("name")
                .accountNumber("111")
                .address("VN")
                .email("email@email.com")
                .mobileBanking(true)
                .newsletter(false)
                .builder();
        System.out.println(accountMutable);
    }

    static void factoryPattern() {
        System.out.println("-----------Factory pattern-----------");

        // The common case
        Bank bank = BankFactory.getBank(BankType.TPBANK);
        System.out.println(bank.getBankName()); // TPBank

        // Instant of factory in spring
        Product product = AppConfig.getProduct();

        System.out.println(product);  // Output: Product{name='Product Name'}
    }

    static void singletonPattern() {
        System.out.println("-----------Singleton pattern-----------");

        System.out.println("-----------1.Eager initialization-----------");
        System.out.println("-----------1.1.With private constructor-----------");
        // Eager initialization
        // 1. Singleton object within private no param constructor (for CAN NOT create new instance)
        // Every variable is the same singleton instance
        EagerInitializedSingleton eagerInitializedSingleton1 = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton eagerInitializedSingleton2 = EagerInitializedSingleton.getInstance();
        //EagerInitializedSingleton eagerInitializedSingleton3 = new EagerInitializedSingleton(); // Error compiler

        eagerInitializedSingleton1.showMessage();
        eagerInitializedSingleton2.showMessage();
        System.out.println(eagerInitializedSingleton1 == eagerInitializedSingleton2); // true

        System.out.println("-----------1.2.Without private constructor-----------");
        // 2. Singleton object without private no param constructor (for CAN create new instance)
        // Every variable is the same singleton instance when using getInstance
        // Every variable is not the same when create new instance
        EagerInitializedSingletonNoConstructor eagerInitializedSingletonNoConstructor1 =
                EagerInitializedSingletonNoConstructor.getInstance();
        EagerInitializedSingletonNoConstructor eagerInitializedSingletonNoConstructor2 =
                EagerInitializedSingletonNoConstructor.getInstance();
        EagerInitializedSingletonNoConstructor eagerInitializedSingletonNoConstructor3 =
                new EagerInitializedSingletonNoConstructor();
        EagerInitializedSingletonNoConstructor eagerInitializedSingletonNoConstructor4 =
                new EagerInitializedSingletonNoConstructor();

        eagerInitializedSingletonNoConstructor1.showMessage();
        eagerInitializedSingletonNoConstructor2.showMessage();
        eagerInitializedSingletonNoConstructor3.showMessage();
        System.out.println(eagerInitializedSingletonNoConstructor1 == eagerInitializedSingletonNoConstructor2); // true
        System.out.println(eagerInitializedSingletonNoConstructor1 == eagerInitializedSingletonNoConstructor3); // false
        System.out.println(eagerInitializedSingletonNoConstructor4 == eagerInitializedSingletonNoConstructor3); // false

        System.out.println("-----------1.3.Using Reflection to break constructor-----------");
        ReflectionSingletonTest();

        System.out.println("-----------2.Static block initialization-----------");
        StaticBlockSingleton staticBlockSingleton1 = StaticBlockSingleton.getInstance();
        StaticBlockSingleton staticBlockSingleton2 = StaticBlockSingleton.getInstance();
        staticBlockSingleton1.showMessage();
        staticBlockSingleton2.showMessage();
        System.out.println(staticBlockSingleton1 == staticBlockSingleton2); // true

        System.out.println("-----------3.Lazy initialization-----------");
        // Lazy load have a problem in multi thread. See detail more in  LazyInitializedSingleton
        LazyInitializedSingleton lazyInitializedSingleton1 = LazyInitializedSingleton.getInstance();
        LazyInitializedSingleton lazyInitializedSingleton2 = LazyInitializedSingleton.getInstance();
        lazyInitializedSingleton1.showMessage();
        lazyInitializedSingleton2.showMessage();
        System.out.println(lazyInitializedSingleton1 == lazyInitializedSingleton2); // true

        System.out.println("-----------4.Thread Safe Singleton-----------");
        // Thread safety for lazy initialization using synchronized method
        // Using synchronized method have a performance problem what a queue and block method
        ThreadSafeLazyInitializedSingleton threadSafeLazyInitializedSingleton1 =
                ThreadSafeLazyInitializedSingleton.getInstance();
        ThreadSafeLazyInitializedSingleton threadSafeLazyInitializedSingleton2 =
                ThreadSafeLazyInitializedSingleton.getInstance();
        threadSafeLazyInitializedSingleton1.showMessage();
        threadSafeLazyInitializedSingleton2.showMessage();
        System.out.println(threadSafeLazyInitializedSingleton1 == threadSafeLazyInitializedSingleton2); // true

        System.out.println("-----------5.Double Check Locking Singleton-----------");
        // Thread safety for lazy initialization using synchronized method
        // Not block method as synchronized method and resolve performance problem
        DoubleCheckLockingSingleton doubleCheckLockingSingleton1 =
                DoubleCheckLockingSingleton.getInstance();
        DoubleCheckLockingSingleton doubleCheckLockingSingleton2 =
                DoubleCheckLockingSingleton.getInstance();
        doubleCheckLockingSingleton1.showMessage();
        doubleCheckLockingSingleton2.showMessage();
        System.out.println(doubleCheckLockingSingleton1 == doubleCheckLockingSingleton2); // true

        System.out.println("-----------6.Bill Pugh Singleton Implementation-----------");
        BillPughSingleton billPughSingleton1 = BillPughSingleton.getInstance();
        BillPughSingleton billPughSingleton2 = BillPughSingleton.getInstance();
        billPughSingleton1.showMessage();
        billPughSingleton2.showMessage();

        System.out.println("-----------7.Enum Singleton-----------");
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        System.out.println(enumSingleton); // Using toString method
        System.out.println(enumSingleton.name()); // Using name of enum
        System.out.println(enumSingleton.getText()); // Using field from getter in enum
        System.out.println(enumSingleton.getNumber()); // Using field from getter in enum

        System.out.println("-----------8.Serialization and Singleton-----------");
        try {
            SingletonSerializedTest();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ReflectionSingletonTest() {
        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;

        try {
            // Get all constructor using class.getDeclaredConstructors()
            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                // This code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
    }

    private static void SingletonSerializedTest() throws IOException, ClassNotFoundException {
        // Instance unique -> set to file -> read file again -> read instance have serializatized
        SerializedSingleton serializedSingleton1 = SerializedSingleton.getInstance();
        EnumSingleton enumSingleton1 = EnumSingleton.INSTANCE;

        // Write file code of instance
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("SingletonSerializedTest.txt"));
        out.writeObject(serializedSingleton1);
        out.writeObject(enumSingleton1);
        out.close();

        // De-serialize from file to object
        ObjectInput in = new ObjectInputStream(new FileInputStream("SingletonSerializedTest.txt"));
        SerializedSingleton serializedSingleton2 = (SerializedSingleton) in.readObject();
        EnumSingleton enumSingleton2 = (EnumSingleton) in.readObject();
        in.close();

        System.out.println("serializedSingleton1 hashCode=" + serializedSingleton1.hashCode());
        System.out.println("serializedSingleton2 hashCode=" + serializedSingleton2.hashCode());
        System.out.println(serializedSingleton1 == serializedSingleton2); // true - false when not implement readResolve()
        System.out.println(serializedSingleton1.equals(serializedSingleton2)); // true - false when not implement readResolve()
        System.out.println("enumSingleton1 hashCode=" + enumSingleton1.hashCode());
        System.out.println("enumSingleton2 hashCode=" + enumSingleton2.hashCode());
        System.out.println(enumSingleton1 == enumSingleton2); // true - always true

    }
}