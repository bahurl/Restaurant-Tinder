package com.techelevator.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Restaurant {
    private int restaurantId;
    @JsonProperty("image_url")
    private String imgUrl;
    private float rating;
    private String Address1;
    private String Address2;
    private String Address3;
    private String city;
    private String state;
    private String name;
    @JsonProperty("zip_code")
    private String zipCode;
    private String phone;
    private String openHour;
    private String closeHour;
    private String type;
    private List<Times> timesList;
    private boolean isOpen;


    public Restaurant(int restaurantId, String address1, String city, String state, String name, String zipCode, String openHour, String closeHour, String type) {
        this.restaurantId = restaurantId;
        Address1 = address1;
        this.city = city;
        this.state = state;
        this.name = name;
        this.zipCode = zipCode;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.type = type;
    }

    public Restaurant() {
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getAddress1() {
        return this.Address1;
    }

    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }

    public String getAddress2() {
        return this.Address2;
    }

    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }

    public String getAddress3() {
        return this.Address3;
    }

    public void setAddress3(String Address3) {
        this.Address3 = Address3;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getOpenHour() {
        return this.openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getCloseHour() {
        return this.closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Times> getTimesList() {
        return timesList;
    }

    public void setTimesList(List<Times> timesList) {
        this.timesList = timesList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
