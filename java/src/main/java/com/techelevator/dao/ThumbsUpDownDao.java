package com.techelevator.dao;

import com.techelevator.model.ThumbsUpDown;
import java.util.List;

public interface ThumbsUpDownDao {
    List<ThumbsUpDown> getThumbUp(int ThumbsUp);

    List<ThumbsUpDown> getThumbDown(int ThumbsDown);
}

