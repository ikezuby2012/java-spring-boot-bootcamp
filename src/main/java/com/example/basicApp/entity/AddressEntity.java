package com.example.basicApp.entity;

import com.example.basicApp.DTO.UserDto;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name="address")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 6373874838728939L;
    @Id
    @GeneratedValue
    private long id;
    @Column(length=30, nullable = false)
    private String addressId;
    @Column(length=15, nullable = false)
    private String city;
    @Column(length=15, nullable = false)
    private String country;
    @Column(length=100, nullable = false)
    private String streetName;
    @Column(length=7, nullable = false)
    private String postalCode;
    @Column(length=10, nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserDto userDto;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
