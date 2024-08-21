# Tight coupling vs Loose coupling
## **Tight coupling**

là ràng buộc liên kết chặc chẽ khi class khai báo trực tiếp các class khác. Khi này thì class phụ thuộc cao vào dependencies, ,mỗi khi dependencies cấp nhật thì main class sẽ bị ảnh hưởng

```java
public class Car {
    void move() {
        System.out.prinln("Move by Car");
    }
}

public class Train {
    void move() {
        System.out.prinln("Move by Train");
    }
}

public class Travel {
    Car car = new Car(); // Khởi tạo trực tiếp đối tượng
    void startJourney() {
        car.travel();
    }
}
```

## **Loose coupling**

là ràng buộc liên kết yếu, class không phụ thuộc trực tiếp vào class khác mà cần thông quan interface hoặc abstract classes

**Sử dụng interface**

```java
public interface Vehicle {
    void move();
}

public class Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}

public class Bike implements Vehicle {
    @Override
    public void move() {
        System.out.println("Bike is moving");
    }
}

// Class Travel sử dụng interface Vehicle
public class Travel {
    private Vehicle vehicle; // TRAVEL KHÔNG PHỤ THUỘC LÀ BIKE HAY CAR

    public Travel(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void startJourney() {
        vehicle.move();
    }
}

// Sử dụng trong main
public class Main {
    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bike = new Bike();

				// TRAVEL KHÔNG PHỤ THUỘC LÀ BIKE HAY CAR
        Travel travelByCar = new Travel(car);
        Travel travelByBike = new Travel(bike);

        travelByCar.startJourney();  // Output: Car is moving
        travelByBike.startJourney(); // Output: Bike is moving
    }
}

```

**Sử dụng abstract class**

```java
public abstract class Vehicle {
    public abstract void move();

    public void startEngine() {
        System.out.println("Engine started");
    }
}

public class Car extends Vehicle {
    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}

public class Bike extends Vehicle {
    @Override
    public void move() {
        System.out.println("Bike is moving");
    }
}

// Class Travel using abstract class Vehicle
public class Travel {
    private Vehicle vehicle; // TRAVEL KHÔNG PHỤ THUỘC LÀ BIKE HAY CAR

    public Travel(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void startJourney() {
        vehicle.startEngine();
        vehicle.move();
    }
}

// Usage in main
public class Main {
    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bike = new Bike();

				// TRAVEL KHÔNG PHỤ THUỘC LÀ BIKE HAY CAR
        Travel travelByCar = new Travel(car);
        Travel travelByBike = new Travel(bike);

        travelByCar.startJourney();  // Output: Engine started \n Car is moving
        travelByBike.startJourney(); // Output: Engine started \n Bike is moving
    }
}
```

IOC, DI là các pattern có mục đích để loose coupling