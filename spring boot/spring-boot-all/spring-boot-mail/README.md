# Dependency
Starter mail
```shell
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

Thymeleaf UI
```shell
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

# Mail properties
```properties
spring.mail.host=sandbox.smtp.mailtrap.io   # Sử dụng mailtrap - mail 3rd service
spring.mail.port=587
spring.mail.username=xxx
spring.mail.password=xxx
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

# Cấu trúc
Cơ bản
```java
SimpleMailMessage message = new SimpleMailMessage();
message.setFrom(from);
message.setTo(MAIL_TO);
message.setSubject(SUBJECT);
message.setText("This is a body text"); // Hiển thị nội dung được set, html không work
jms.send(message);
```

Body HTML thông qua MimeMessageHelper
```java
StringBuffer sb = new StringBuffer(
        "<p style='color:#42b983'>Hello World</p>");
helper.setText(sb.toString(), true);    // Vẫn sử dung thuộc tính setText
```

Body HTML có ảnh sử dụng MimeMessageHelper và addInline() method
```java
helper.setText("<html><body>Image：<img src='cid:img'/></body></html>", true);
FileSystemResource file = new FileSystemResource(
        new File("src/main/resources/static/file/image.png"));
helper.addInline("img", file);
```

Đính kèm file
```java
message.setText("This is a body text");
FileSystemResource file = new FileSystemResource(
        new File("src/main/resources/static/file/20240920.txt"));
helper.addAttachment("20240920.txt", file);
```