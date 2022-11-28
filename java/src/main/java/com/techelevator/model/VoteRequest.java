package com.techelevator.model;

import java.util.List;

public class VoteRequest {
    private List<Integer> restaurantIds;
    private int invitationId;

    public List<Integer> getRestaurantIds() {
        return restaurantIds;
    }

    public void setRestaurantIds(List<Integer> restaurantIds) {
        this.restaurantIds = restaurantIds;
    }

    public int getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(int invitationId) {
        this.invitationId = invitationId;
    }
}
