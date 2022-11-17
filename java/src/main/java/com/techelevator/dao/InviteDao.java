package com.techelevator.dao;

import com.techelevator.model.Invite;

import java.time.LocalDateTime;

public interface InviteDao {

    void createInvite(Invite invite);
    Invite getInviteByLinkId(String id);
}
