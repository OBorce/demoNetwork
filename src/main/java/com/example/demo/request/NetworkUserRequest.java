package com.example.demo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NetworkUserRequest {
    @Email
    @Size(max = 120, message = "Email must be less than 120 characters long")
    private String email;

    @NotNull
    @Size(min = 8, max = 120, message = "Password must have between 8 and 120 alpha numeric characters ")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must only contain alpha numeric characters")
    private String password;

    @NotNull
    @Size(max = 20, message = "Email be less than 20 characters long")
    private String nickname;

    @Size(max = 40, message = "Email must be less than 40 characters long")
    private String firstName;

    @Size(max = 40, message = "Email must be less than 40 characters long")
    private String lastName;

    private LocationRequest location;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocationRequest getLocation() {
        return location;
    }
}
