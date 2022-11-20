package com.techelevator.dao;

import com.techelevator.model.ThumbsUpDown;

import java.util.List;

public interface ThumbsUpDownDao {

    List<ThumbsUpDown> getVote(List<Integer> restaurantIds, int invitationId);
    boolean update(ThumbsUpDown vote);
    boolean createVote(ThumbsUpDown vote);

}

