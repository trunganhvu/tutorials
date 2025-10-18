package org.anhvt.springbootgson.dto;

import java.util.Date;

public class UserRequest {
    private String name;
    private String email;
    private Date birthDate;
    private String timezone;

    public UserRequest() {}

    public UserRequest(String name, String email, Date birthDate, String timezone) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.timezone = timezone;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
}
