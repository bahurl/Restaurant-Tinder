package com.techelevator.dao;

import com.techelevator.model.Invite;

public interface InviteDao {

    void createInvite(Invite invite);
    Invite getInviteByLinkId(String id);
}
