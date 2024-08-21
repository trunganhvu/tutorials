# @SuperBuilder
Annotation là một phiên bản mở rộng của @Builder được Lombok cung cấp để hỗ trợ tốt hơn cho các lớp kế thừa.

### Cat sử dụng @SuperBuilder
**Parent class**: Animal
```java
@Getter
@SuperBuilder
public class Animal {
    private String name;
}
```

**Children class**: Cat
```java
@Getter
@SuperBuilder
public class Cat extends Animal {
    private String action;
}
```


### Cách sử dụng
```shell
Cat cat = Cat.builder()  // Create object with builder
                .name("Cat1")       // Set field in parent object using @SuperBuilder
                .action("cat action")// Set field in current child using @SuperBuilder
                .build();       // final to created new object

        System.out.println(cat.getName());      // Cat1
        System.out.println(cat.getAction());    // cat action
```

### Tạo bộ code sau khi compile
**Animal.class*
```javapublic class Animal {
    private String name;

    @Generated
    protected Animal(final AnimalBuilder<?, ?> b) {
        this.name = b.name;
    }

    @Generated
    public static AnimalBuilder<?, ?> builder() {
        return new AnimalBuilderImpl();
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public abstract static class AnimalBuilder<C extends Animal, B extends AnimalBuilder<C, B>> {
        @Generated
        private String name;

        public AnimalBuilder() {
        }

        @Generated
        public B name(final String name) {
            this.name = name;
            return this.self();
        }

        @Generated
        protected abstract B self();

        @Generated
        public abstract C build();

        @Generated
        public String toString() {
            return "Animal.AnimalBuilder(name=" + this.name + ")";
        }
    }

    @Generated
    private static final class AnimalBuilderImpl extends AnimalBuilder<Animal, AnimalBuilderImpl> {
        @Generated
        private AnimalBuilderImpl() {
        }

        @Generated
        protected AnimalBuilderImpl self() {
            return this;
        }

        @Generated
        public Animal build() {
            return new Animal(this);
        }
    }
}
```

Cat.class
```java
public class Cat extends Animal {
    private String action;

    @Generated
    protected Cat(final CatBuilder<?, ?> b) {
        super(b);
        this.action = b.action;
    }

    @Generated
    public static CatBuilder<?, ?> builder() {
        return new CatBuilderImpl();
    }

    @Generated
    public String getAction() {
        return this.action;
    }

    @Generated
    public abstract static class CatBuilder<C extends Cat, B extends CatBuilder<C, B>> extends Animal.AnimalBuilder<C, B> {
        @Generated
        private String action;

        public CatBuilder() {
        }

        @Generated
        public B action(final String action) {
            this.action = action;
            return this.self();
        }

        @Generated
        protected abstract B self();

        @Generated
        public abstract C build();

        @Generated
        public String toString() {
            String var10000 = super.toString();
            return "Cat.CatBuilder(super=" + var10000 + ", action=" + this.action + ")";
        }
    }

    @Generated
    private static final class CatBuilderImpl extends CatBuilder<Cat, CatBuilderImpl> {
        @Generated
        private CatBuilderImpl() {
        }

        @Generated
        protected CatBuilderImpl self() {
            return this;
        }

        @Generated
        public Cat build() {
            return new Cat(this);
        }
    }
}
```