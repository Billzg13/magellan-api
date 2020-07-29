package com.example.ptuxiakh.model.auth;

import com.example.ptuxiakh.model.Favourite;
import com.example.ptuxiakh.model.Types;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
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
    private Date dateOfBirth;
    ;

    private Date dateCreated;

    int age;

    ArrayList<Favourite> favourites;

    @Min(0)
    @Max(5)
    int priceLevel; // 0 - 5

    Types types;

    public User() {
    }

    public User(String firstName, String username, String email, String password) {
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //this is used
    public User(@NotBlank @Size(max = 40) String firstName, @NotBlank @Size(max = 15) String username,
                @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(min = 4, max = 40) String lastName,
                String role, Date dateOfBirth, String gender, int priceLevel, Types types) {
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.lastName = lastName;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.priceLevel = priceLevel;
        this.types = types;
    }

    public User(String id, @NotBlank @Size(max = 40) String firstName, @NotBlank @Size(max = 40) String username, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password, Set<Role> roles, @NotBlank @Size(min = 4, max = 40) String lastName, String role, String gender, Date dateOfBirth, Date dateCreated, int age, ArrayList<Favourite> favourites) {
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

    public User(String id, @NotBlank @Size(max = 40) String firstName, @NotBlank @Size(max = 40) String username, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password, Set<Role> roles, @NotBlank @Size(min = 4, max = 40) String lastName, String role, String gender, Date dateOfBirth, Date dateCreated, int age, ArrayList<Favourite> favourites, int priceLevel, Types types) {
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
        //this.favourites = favourites;
        this.priceLevel = priceLevel;
        this.types = types;
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

    public ArrayList<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Favourite> favourites) {
        this.favourites = favourites;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
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
                ", priceLevel=" + priceLevel +
                ", types=" + types +
                '}';
    }
}

