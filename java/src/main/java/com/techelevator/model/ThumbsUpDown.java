package com.techelevator.model;

public class ThumbsUpDown {
    private int ThumbsUp;
    private int ThumbsDown;
    private int invitationId;
    private int restaurantId;


    public ThumbsUpDown() {
    }

    public ThumbsUpDown(int ThumbsUp, int ThumbsDown, int invitationId, int restaurantId) {
        this.ThumbsUp = ThumbsUp;
        this.ThumbsDown = ThumbsDown;
        this.invitationId = invitationId;
        this.restaurantId = restaurantId;
    }


    public int getThumbsUp() {
        return this.ThumbsUp;
    }

    public void setThumbsUp(int ThumbsUp) {
        this.ThumbsUp = ThumbsUp;
    }

    public int getThumbsDown() {
        return this.ThumbsDown;
    }

    public void setThumbsDown(int ThumbsDown) {
        this.ThumbsDown = ThumbsDown;
    }

    public int getInvitationId() {
        return this.invitationId;
    }

    public void setInvitationId(int invitationId) {
        this.invitationId = invitationId;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    

}
