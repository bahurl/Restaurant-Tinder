package com.techelevator.dao;

import com.techelevator.model.Invite;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import static java.lang.Integer.parseInt;

@Component
public class JdbcInviteDao implements InviteDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcInviteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createInvite(Invite invite) {
        String sql;

        System.out.println(invite.getInvitationLink());

        try {
            if(invite.getCity() == null) {
                sql = "INSERT INTO invitations (owner_id, zip_code, invitation_date, invitation_link) VALUES (?, ?, ?, ?);";
                jdbcTemplate.update(sql, invite.getOwnerId(), invite.getZipCode(), invite.getInvitationDate(), invite.getInvitationLink());
            } else {
                sql = "INSERT INTO invitations (owner_id, city, invitation_date, invitation_link) VALUES (?, ?, ?, ?);";
                jdbcTemplate.update(sql, invite.getOwnerId(), invite.getCity(), invite.getInvitationDate(), invite.getInvitationLink());
            }
        } catch(DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }


    private Invite mapRowToInvite(SqlRowSet rs) {
        Invite invite = new Invite();
        invite.setInvitationId(rs.getInt("invitation_id"));
        invite.setOwnerId(rs.getInt("owner_id"));
        if(rs.getString("city") != null) {
            invite.setCity(rs.getString("city"));
        }
        if(rs.getString("zip_code") != null) {
            invite.setZipCode(rs.getString("zip_code"));
        }
        invite.setSelection(rs.getInt("selection"));
        invite.setInvitationDate(rs.getTimestamp("invitation_date").toLocalDateTime());
        invite.setInvitationLink(rs.getString("invitation_link"));
        return invite;
    }
}

