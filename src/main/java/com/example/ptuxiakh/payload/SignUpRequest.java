package com.example.ptuxiakh.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 40)
    private String firstName;

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

    @JsonProperty("dob")
    private Date dateOfBirth;

    private String gender;

    public SignUpRequest() {
    }

    public SignUpRequest(@NotBlank @Size(min = 4, max = 40) String firstName, String username,
                         @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(min = 6, max = 20) String password,
                         @NotBlank @Size(min = 4, max = 40) String lastName, String role, Date dateOfBirth, String gender) {
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
