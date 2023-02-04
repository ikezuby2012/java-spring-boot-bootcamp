package com.example.basicApp.model.Request;

import org.springframework.data.annotation.Id;

import java.util.List;

public class UserRequestModel {

    private String fullName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressRequestModel> address;

    public List<AddressRequestModel> getAddress() {
        return address;
    }

    public void setAddress(List<AddressRequestModel> address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "UserRequestModel{" +
                ", fullName='" + fullName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
