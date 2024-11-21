# BaseEntity
```java
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_at")
    @LastModifiedDate
    private Instant modifiedAt;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
```

# Entity Product
```java
@Entity
@Table(name = "TBL_PRODUCT1")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue
    private  int id;
    @Column(unique=true)
    private String name;
    private int quantity;
    private long price;
}
```

# Config date and user
```java
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new UserAuditorAware();
    }
}
```

```java
public class UserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse("SYSTEM"));
    }
}
```

```java
public final class SecurityUtils {
    private SecurityUtils() {
    }
    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }
}
```

# Insert without set metadata
```java
List<Product> products = IntStream.rangeClosed(1, 50)
        .mapToObj(i -> new Product("Product" + i,
                new Random().nextInt(100), new Random().nextInt(50000)))
        .collect(Collectors.toList());
productRepository.saveAll(products);
```