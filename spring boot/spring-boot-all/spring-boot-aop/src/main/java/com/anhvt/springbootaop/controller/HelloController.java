/**
 * Copyright 2024
 * Name: HelloController
 */
package com.anhvt.springbootaop.controller;

import com.anhvt.springbootaop.aspects.CheckSomething;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/16/2024
 */
@RestController
public class HelloController {

    /**
     * Log Output:
     * Before in Around execution. - Roles: [role3]
     * Before execution - Roles: [role3]
     * After return execution - Roles: [role3]
     * After execution - Roles: [role3]
     * After in arround execution
     *
     * @return
     */
    @RequestMapping("/")
    @CheckSomething(roles = {"role3"})
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /**
     * Log Output:
     * Before in Around execution. - Roles: [role1, role2]
     * Before execution - Roles: [role1, role2]
     * After throwing an exception - Roles:[role1, role2]java.lang.Exception: Hello from exception!
     * After execution - Roles: [role1, role2]
     * 2024-10-16T21:06:08.393+07:00 ERROR 8572 --- [spring-boot-aop] [nio-8080-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: java.lang.Exception: Hello from exception!] with root cause
     *
     * java.lang.Exception: Hello from exception!
     * ...
     *
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/throwException")
    @CheckSomething(roles = {"role1", "role2"})
    public String throwAnException() throws Exception {
        throw new Exception("Hello from exception!");
    }
}
