package com.example.ptuxiakh.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

    @Id
    private String id;

    @NotBlank
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Size(max = 40)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    private Set<Role> roles = new HashSet<>();

    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    private String role;

    private String gender;

    @JsonProperty("dob")
    private Date dateOfBirth;;

    private Date dateCreated;

    int age;

    ArrayList<String> favourites;

    public User() {
    }

    public User(String firstName, String username, String email, String password) {
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(@NotBlank @Size(max = 40) String firstName, @NotBlank @Size(max = 15) String username,
                @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(min = 4, max = 40) String lastName,
                String role, Date dateOfBirth, String gender) {
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.lastName = lastName;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public User(String id, @NotBlank @Size(max = 40) String firstName, @NotBlank @Size(max = 40) String username, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password, Set<Role> roles, @NotBlank @Size(min = 4, max = 40) String lastName, String role, String gender, Date dateOfBirth, Date dateCreated, int age, ArrayList<String> favourites) {
        this.id = id;
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.lastName = lastName;
        this.role = role;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateCreated = dateCreated;
        this.age = age;
        this.favourites = favourites;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<String> favourites) {
        this.favourites = favourites;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateCreated=" + dateCreated +
                ", age=" + age +
                ", favourites=" + favourites +
                '}';
    }
}

