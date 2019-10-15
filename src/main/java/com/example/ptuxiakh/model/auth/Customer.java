package com.example.ptuxiakh.model.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Document(collection="customers")
public class Customer {

    @Id
    public String id;

    @NotEmpty(message = "name is required")
    public String firstName;
    @NotEmpty(message = "last name is required")
    public String lastName;
    @NotEmpty(message = "company name is required")
    public String companyName;
    @NotEmpty(message = "email is required")
    public String email;

    public Customer() {}

    public Customer(String id, @NotEmpty(message = "name is required") String firstName, @NotEmpty(message = "last name is required") String lastName, @NotEmpty(message = "company name is required") String companyName, @NotEmpty(message = "email is required") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}