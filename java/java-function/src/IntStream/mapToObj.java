package IntStream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}


/**
 * Map every int elements of Stream to Object 
 * @author AnhVT6
 *
 */
public class mapToObj {
    public static void main(String[] args) {

        // Map stream of int to stream of Object (string)
        IntStream.rangeClosed(1, 5).mapToObj(n -> "Number " + n)
                .forEach(System.out::println);	// Number 1 Number 2 ...

        
        IntStream ageStream = IntStream.rangeClosed(18, 30);
        // Map IntStream to Object Stream (Person)
        Stream<Person> personStream = ageStream.mapToObj(age -> new Person("Person " + age, age));
        personStream.forEach(System.out::println);	// Person{name='Person 18', age=18} ...

    }
}
