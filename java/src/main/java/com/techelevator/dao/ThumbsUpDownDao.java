package com.techelevator.dao;

import com.techelevator.model.ThumbsUpDown;
import com.techelevator.model.VoteRequest;

import java.util.List;

public interface ThumbsUpDownDao {

    List<ThumbsUpDown> getVote(VoteRequest voteRequest);
    boolean update(ThumbsUpDown vote);
    boolean createVote(ThumbsUpDown vote);
    boolean doesExist(List<Integer> restaurantIds, int invitationId);

}

