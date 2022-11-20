package com.techelevator.controller;


import com.techelevator.dao.ThumbsUpDownDao;
import com.techelevator.model.ThumbsUpDown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ThumbsUpDownController {

    @Autowired
    private ThumbsUpDownDao thumbsUpDownDao;


    @GetMapping(path = "vote")
    private List<ThumbsUpDown> getThumbsUpDown(@RequestBody List<Integer> restaurantIds, Integer invitationId){
        return thumbsUpDownDao.getVote(restaurantIds, invitationId);
    }

    @PutMapping(path = "save")
    private boolean update(@RequestBody ThumbsUpDown thumbsUpDown){
        return thumbsUpDownDao.update(thumbsUpDown);
    }

    @PostMapping(path = "create")
    private boolean create(@RequestBody ThumbsUpDown thumbsUpDown){
        return thumbsUpDownDao.createVote(thumbsUpDown);
    }

}
