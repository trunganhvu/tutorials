# JoinPoint
> là một điểm trong quá trình thực thi của ứng dụng, nơi mà một khía cạnh (aspect) có thể được áp dụng
> Ví dụ từ một controller, service, class, method

# @Pointcut
> Pointcut là một tập hợp các điểm (join points) trong chương trình mà bạn muốn áp dụng các khía cạnh (aspects)

# @Around 
> Thực hiện tiến hành trước và sau point join
```java
joinPoint.proceed();
```

# @Before
> được thực thi trước khi phương thức thực sự được gọi.
# @After
> được thực thi sau khi phương thức đã hoàn thành, bất kể phương thức đó có thành công hay ném ra ngoại lệ.

# @AfterReturning 
> được thực thi sau khi return
# @AfterThrowing
> được thực thi sau khi xảy ra exception 


# Thứ tự
1. @Around before joinPoint.proceed()
2. @Before
3. @AfterReturning or @AfterThrowing
4. @After
5. @Around after joinPoint.proceed()