package com.techelevator.controller;

import com.techelevator.dao.VotingDataDao;
import com.techelevator.model.VotingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="data/")

public class VotingDataController {

    @Autowired
    private VotingDataDao votingDataDao;

    @GetMapping(path ="{id}")
    private VotingData getVotingData( @PathVariable String id){
        return votingDataDao.getVotingData(id);
    }


}
