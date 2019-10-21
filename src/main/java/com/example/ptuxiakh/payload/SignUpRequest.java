package com.example.ptuxiakh.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    private String role;

    public SignUpRequest() {
    }

    public SignUpRequest(@NotBlank @Size(min = 4, max = 40) String name, @NotBlank @Size(min = 3, max = 15) String username,
                         @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(min = 6, max = 20) String password,
                         @NotBlank @Size(min = 4, max = 40) String lastName, String role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
