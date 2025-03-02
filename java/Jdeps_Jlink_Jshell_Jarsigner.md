# jdeps
> You use the jdeps command to launch the Java class dependency analyzer. Working with .class, .jar

### Comon jdeps
```sh
jdeps ./target/classes/org/anhvt/springmaven/controller/IndexController.class
```

Result
```sh
IndexController.class -> java.base
IndexController.class -> not found
   org.anhvt.springmaven.controller                   -> java.lang                                          java.base
   org.anhvt.springmaven.controller                   -> org.springframework.web.bind.annotation            not found
```
### Summary jdeps
```sh
jdeps -summary ./target/classes/org/anhvt/springmaven/controller/IndexController.class
```

Result
```sh
IndexController.class -> java.base
IndexController.class -> not found
```

### Get details jdeps within verbose
```sh
jdeps -verbose ./target/classes/org/anhvt/springmaven/controller/IndexController.class
```

Result
```sh
IndexController.class -> java.base
IndexController.class -> not found
   org.anhvt.springmaven.controller.IndexController   -> java.lang.Object                                   java.base
   org.anhvt.springmaven.controller.IndexController   -> java.lang.String                                   java.base
   org.anhvt.springmaven.controller.IndexController   -> org.springframework.web.bind.annotation.GetMapping not found
   org.anhvt.springmaven.controller.IndexController   -> org.springframework.web.bind.annotation.RequestMapping not found
   org.anhvt.springmaven.controller.IndexController   -> org.springframework.web.bind.annotation.RestController not found
```

### Get details jdeps within verbose
```sh
jdeps -dotoutput F:\  ./target/spring-maven-0.0.1-SNAPSHOT.jar
```

Result .dot files successful exported

### Print all Module deps

jdeps --print-module-deps  ./target/spring-maven-0.0.1-SNAPSHOT.jar
```sh
spring-maven-0.0.1-SNAPSHOT.jar
   org.anhvt.springmaven.SpringMavenApplication       -> org.springframework.boot.SpringApplication         not found
   org.anhvt.springmaven.SpringMavenApplication       -> org.springframework.boot.autoconfigure.SpringBootApplication not found
   org.anhvt.springmaven.SpringMavenApplication       -> org.springframework.context.ConfigurableApplicationContext not found
   org.anhvt.springmaven.controller.IndexController   -> org.springframework.web.bind.annotation.GetMapping not found
   org.anhvt.springmaven.controller.IndexController   -> org.springframework.web.bind.annotation.RequestMapping not found
   org.anhvt.springmaven.controller.IndexController   -> org.springframework.web.bind.annotation.RestController not found
   org.springframework.boot.loader.launch.JarModeRunner -> org.springframework.core.io.support.SpringFactoriesLoader not found
   org.springframework.boot.loader.launch.JarModeRunner -> org.springframework.util.ClassUtils                not found
```
# jdeprscan
> You use the jdeprscan tool as a static analysis tool that scans a jar file (or some other aggregation of class files) for uses of deprecated API elements.

```sh
jdeprscan  ./target/spring-maven-0.0.1-SNAPSHOT.jar
```

Result
```
Jar file ./target/spring-maven-0.0.1-SNAPSHOT.jar:
error: cannot find class org/springframework/util/ClassUtils
error: cannot find class org/springframework/core/io/support/SpringFactoriesLoader
error: cannot find class org/springframework/boot/SpringApplication
```
# jlink
> You can use the jlink tool to assemble and optimize a set of modules and their dependencies into a custom runtime image.

```sh
jlink --module-path $JAVA_HOME/jmods --add-modules java.base,java.sql --output custom-runtime
```
# jarsigner

## Gen keystore
keytool -genkey -alias server -keyalg RSA -keypass password -storepass password -keystore keystore.jks

## Create certficate server.cer
keytool -export -alias server -storepass password -file server.cer -keystore keystore.jks

## Set keystore into jar file
jarsigner -keystore keystore.jks -signedjar signedjar.jar ./target/spring-maven-0.0.1-SNAPSHOT.jar server

## Verify jar file
jarsigner -verify signedjar.jar