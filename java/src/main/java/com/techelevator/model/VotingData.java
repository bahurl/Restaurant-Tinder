package com.techelevator.model;

import java.util.List;

public class VotingData {
    private List<Integer> restaurantIds;
    private List<Restaurant> restaurants;
    private Invite invite;
    private List<ThumbsUpDown> vote;

    public List<Integer> getRestaurantIds() {
        return restaurantIds;
    }

    public void setRestaurantIds(List<Integer> restaurantIds) {
        this.restaurantIds = restaurantIds;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Invite getInvite() {
        return invite;
    }

    public void setInvite(Invite invite) {
        this.invite = invite;
    }

    public List<ThumbsUpDown> getVote() {
        return vote;
    }

    public void setVote(List<ThumbsUpDown> vote) {
        this.vote = vote;
    }
}
