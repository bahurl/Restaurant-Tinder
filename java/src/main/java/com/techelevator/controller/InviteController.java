//package com.techelevator.controller;
//
//import com.techelevator.dao.InviteDao;
//import com.techelevator.dao.RestaurantDao;
//import com.techelevator.model.Invite;
//import com.techelevator.model.Restaurant;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@CrossOrigin
//@RequestMapping(path = "invite")
//public class InviteController {
//
//    private InviteDao inviteDao;
//
//    public InviteController(InviteDao inviteDao) {
//        this.inviteDao = inviteDao;
//    }
//
//    @PostMapping()
//    public String getInviteLink(@RequestParam String location, @RequestParam LocalDateTime dateTime) {
//        return inviteDao.createInvite(location, dateTime);
////    }
////
//
//
//}
//
