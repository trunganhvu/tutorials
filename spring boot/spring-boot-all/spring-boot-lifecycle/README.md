# Định nghĩa Bean implements DisposableBean, InitializingBean
## Định nghĩa các xử lý và đối tượng Bird
Override method destroy() từ class DisposableBean
```java
@Override
public void destroy() {
    System.out.println("Bird destroy");
}
```

Override method afterPropertiesSet() từ class InitializingBean
```java
@Override
public void afterPropertiesSet() {
    System.out.println("Bird afterPropertiesSet");
}
```

## Khai báo bean Bird trong @Configuration
```java
@Bean
public Bird bird() {
    System.out.println("WebConfig - Bird");
    return new Bird("A");
}
```

## Triển khai IOC để inject dependency
```java
@Autowired
private Bird bird;
```

Kết quả instant của bean Bird là new Bird("A")


# Định nghĩa Bean sử dụng annotation @PostConstruct, @PreDestroy
## Định nghĩa các xử lý và đối tượng Fish
Override method @PostConstruct
```java
@PostConstruct
public void init() {
    System.out.println("Fish PostConstruct");
}
```

Override method @PreDestroy
```java
@PreDestroy
public void destory() {
    System.out.println("Fish PreDestroy");
}
```

## Khai báo bean Fish trong @Configuration
```java
@Bean
public Fish fish(){
    System.out.println("WebConfig - Fish");
    return new Fish("B");
}
```

## Triển khai IOC để inject dependency
```java
@Autowired
private Fish fish;
```

Kết quả instant của bean Bird là new Fish("B")


# Định nghĩa Bean sử dụng method object và initMethod + destroyMethod trong @Bean
## Định nghĩa các xử lý và đối tượng User
Implement method init
```java
public void init() {
    System.out.println("User init");
}
```

Implement method destory
```java
public void destroy() {
    System.out.println("User destroy");
}
```

## Khai báo bean User trong @Configuration
```java
@Bean(initMethod = "init", destroyMethod = "destroy")
public User user() {
    System.out.println("WebConfig - User");
    return new User("C");
}
```

## Triển khai IOC để inject dependency
```java
@Autowired
private User user;
```

Kết quả instant của bean Bird là new User("C")

# AnnotationConfigApplicationContext định nghĩa context các bean config
Mỗi khi tạo new AnnotationConfigApplicationContext(WebConfig.class) các bean được khởi tạo mới
* -> Sử dụng trong class @SpringBootApplication để get data từ Bean đã được khởi tạo khi bắt đầu start project
* -> Sử dụng trong controller sẽ khởi tạo lại toàn bộ beans
```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
User user = context.getBean(User.class);    // Get bean from context
System.out.println(user.getName());         // C

Bird bird = context.getBean(Bird.class);
System.out.println(bird.getName());         // A
```

Kết console khi sử dụng AnnotationConfigApplicationContext trong controller
```shell
WebConfig - MyBeanPostProcessor
WebConfig - Bird
Bird constructor2
bird postProcessBeforeInitialization
Bird afterPropertiesSet
bird postProcessAfterInitialization
WebConfig - Fish
Fish constructor2
fish postProcessBeforeInitialization
Fish PostConstruct
fish postProcessAfterInitialization
AnnotationConfigApplicationContext
WebConfig - User
User constructor2
user postProcessBeforeInitialization
User init
user postProcessAfterInitialization
C
A
Fist PreDestroy
Bird destroy
```

# Interface BeanPostProcessor
Implement 2 methods postProcessBeforeInitialization() và postProcessAfterInitialization()
```java
@Override
public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println(beanName + " postProcessBeforeInitialization");
    return bean;
}

@Override
public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println(beanName + " postProcessAfterInitialization");
    return bean;
}
```

Output log console tham khảo file log
```shell
./bean.log
```
