package com.techelevator.model;

public class ThumbsUpDown {
    private int thumbsUp;
    private int thumbsDown;
    private int invitationId;
    private int restaurantId;


    public ThumbsUpDown() {
    }

    public ThumbsUpDown(int ThumbsUp, int ThumbsDown, int invitationId, int restaurantId) {
        this.thumbsUp = ThumbsUp;
        this.thumbsDown = ThumbsDown;
        this.invitationId = invitationId;
        this.restaurantId = restaurantId;
    }


    public int getThumbsUp() {
        return this.thumbsUp;
    }

    public void setThumbsUp(int ThumbsUp) {
        this.thumbsUp = ThumbsUp;
    }

    public int getThumbsDown() {
        return this.thumbsDown;
    }

    public void setThumbsDown(int ThumbsDown) {
        this.thumbsDown = ThumbsDown;
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
