# Mapstruct
## Dependency
```xml
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.5.Final</version>
</dependency>
```

## Interface @Mapper
```java
@Mapper
public interface OrderMapper {

    @Mapping(target = "customer",
            expression = "java(new com.anhvt.springbootmapper.mapstruct.model.Customer(orderDTO.getCustomerFirstName(), orderDTO.getCustomerLastName()))")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "city", target = "city")
    Order toOrder(OrderDTO orderDTO);

    @Mapping(source = "customer.firstName", target = "customerFirstName")
    @Mapping(source = "customer.lastName", target = "customerLastName")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "city", target = "city")
    OrderDTO toOrderDTO(Order order);
}
```

Sử dụng mapper tương tự các service
```java
@Autowired
private OrderMapper orderMapper;
private void entityToDto() {
    // Assuming you have an existing Order object
    Order order = new Order(new Customer("trunganh", "vu"), "CG", "HN");

    // Map Order to OrderDTO
    OrderDTO orderDTO = orderMapper.toOrderDTO(order);

    System.out.println("OrderDTO mapped successfully: " + orderDTO);
    // OrderDTO mapped successfully: OrderDTO(customerFirstName=trunganh, customerLastName=vu, street=CG, city=HN)
}

private void dtoToEntity() {
    OrderDTO orderDTO1 = new OrderDTO("trunganh",
            "vu", "CG", "HN");
    // Map OrderDTO to Order
    Order order2 = orderMapper.toOrder(orderDTO1);

    System.out.println("Order mapped successfully: " + order2);
    // Order mapped successfully: Order(customer=Customer(firstName=trunganh, lastName=vu), street=CG, city=HN)
}
```

# Modelmapper
Định nghia modelMapper
```java
ModelMapper modelMapper1 = new ModelMapper();
OrderDTO orderDTO1 = modelMapper1.map(order, OrderDTO.class);
// OR
ModelMapper modelMapper2 = new ModelMapper();
modelMapper2.addMappings(new PropertyMap<Order, OrderDTO>() {
    @Override
    protected void configure() {
        map().setCustomerLastName(source.getCustomer().getFirstName());
        map().setCustomerFirstName(source.getCustomer().getLastName());
        map().setCity(source.getCity());
        map().setStreet(source.getStreet());
    }
});
```

Detail xử lý
```java
private static void entityToDto() {
    Order order = new Order(new Customer("trunganh", "vu"),
            "CG", "HN");

    ModelMapper modelMapper1 = new ModelMapper();
    OrderDTO orderDTO1 = modelMapper1.map(order, OrderDTO.class);
    System.out.println("Default mapper");
    System.out.println(orderDTO1);  // OrderDTO(customerFirstName=trunganh, customerLastName=vu, street=CG, city=HN)
    System.out.println(order);      // Order(customer=Customer(firstName=trunganh, lastName=vu), street=CG, city=HN)

    System.out.println("Custom mapper");
    ModelMapper modelMapper2 = new ModelMapper();
    modelMapper2.addMappings(new PropertyMap<Order, OrderDTO>() {
        @Override
        protected void configure() {
            map().setCustomerLastName(source.getCustomer().getFirstName());
            map().setCustomerFirstName(source.getCustomer().getLastName());
            map().setCity(source.getCity());
            map().setStreet(source.getStreet());
        }
    });
    OrderDTO orderDTO2 = modelMapper2.map(order, OrderDTO.class);
    System.out.println(orderDTO2);  // OrderDTO(customerFirstName=vu, customerLastName=trunganh, street=CG, city=HN)
    System.out.println(order);      // Order(customer=Customer(firstName=trunganh, lastName=vu), street=CG, city=HN)
}
```