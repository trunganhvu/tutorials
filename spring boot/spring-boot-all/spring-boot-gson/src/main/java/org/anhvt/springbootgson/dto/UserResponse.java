package org.anhvt.springbootgson.dto;

import java.util.Date;

public class UserResponse {
    private String name;
    private String email;
    private Date birthDateUtc;

    public UserResponse(String name, String email, Date birthDateUtc) {
        this.name = name;
        this.email = email;
        this.birthDateUtc = birthDateUtc;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public Date getBirthDateUtc() { return birthDateUtc; }
}
