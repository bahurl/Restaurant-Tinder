
package com.techelevator.controller;

import com.techelevator.dao.InviteDao;
import com.techelevator.model.Invite;
import com.techelevator.model.InviteNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "invite/")
public class InviteController {

    private InviteDao inviteDao;

    public InviteController(InviteDao inviteDao) {
        this.inviteDao = inviteDao;
    }

    @PostMapping(path ="create")
    public void createInvitation(@RequestBody Invite invite) {
         inviteDao.createInvite(invite);
    }

    @GetMapping(path = "{id}")
    public Invite getInviteByLinkId(@PathVariable String id) {
        if(inviteDao.getInviteByLinkId(id) == null) {
            throw new InviteNotFoundException();
        } else {
            return inviteDao.getInviteByLinkId(id);
        }
    }
}

