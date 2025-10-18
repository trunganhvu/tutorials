package org.anhvt.springbootgson.controller;

import com.google.gson.Gson;
import org.anhvt.springbootgson.dto.UserRequest;
import org.anhvt.springbootgson.dto.UserResponse;
import org.anhvt.springbootgson.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Gson gson;
    private final AtomicLong counter = new AtomicLong();

    public UserController(Gson gson) {
        this.gson = gson;
    }

    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest request) {
        return new UserResponse(request.getName(), request.getEmail(), request.getBirthDate());
    }

    /**
     * Danh sách user mẫu.
     */
    @GetMapping
    public String listUsers() {
        List<User> list = List.of(
                new User(1L, "Alice", "alice@example.com", new Date()),
                new User(2L, "Bob", "bob@example.com", new Date())
        );
        return gson.toJson(list);
    }

    private Date convertToUTC(Date date, String clientZoneId) {
        if (clientZoneId == null || clientZoneId.isEmpty()) {
            clientZoneId = "UTC";
        }
        TimeZone clientZone = TimeZone.getTimeZone(clientZoneId);
        long localTime = date.getTime();
        int offset = clientZone.getOffset(localTime);
        return new Date(localTime - offset);
    }
}