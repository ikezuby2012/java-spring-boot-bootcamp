package com.example.basicApp.model.Response;

import java.util.List;

public class UserResponseObj {
    private String userId;
    private String fullName;
    private String lastName;
    private String email;
    private List<AddressRest> address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public List<AddressRest> getAddress() {
        return address;
    }

    public void setAddress(List<AddressRest> address) {
        this.address = address;
    }
}
