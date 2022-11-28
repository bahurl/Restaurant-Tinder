package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface VotingDataDao {
    VotingData getVotingData(String inviteId);
    List<ThumbsUpDown> getVote(VoteRequest voteRequest);
    Invite getInviteByLinkId(String id);
    List<Restaurant> getNearbyRestaurants(String location);
    List<Restaurant> getNearbyRestaurants(String location, String type);


}
