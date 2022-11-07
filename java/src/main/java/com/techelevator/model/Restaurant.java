package com.techelevator.model;



public class Restaurant {
    private int restaurantId;
    private String Address1;
    private String Address2;
    private String Address3;
    private String city;
    private String state;
    private String name;  
    private String zipCode;
    private String openHour;
    private String closeHour;
    private String type;


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
}
