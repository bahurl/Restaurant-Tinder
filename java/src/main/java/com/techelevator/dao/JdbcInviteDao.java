package com.techelevator.dao;

import com.techelevator.model.Invite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDateTime;

public class JdbcInviteDao implements InviteDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcInviteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String createInvite(String location, LocalDateTime dateTime) {

        if(location.)
        String sql = "INSERT INTO invitations (owner_id, city, zip_code, invitation_date, invitation_link) VALUES (?, ?, ?, ?, ?) RETURNING invitation_id";

        int invitationId = jdbcTemplate.queryForObject(sql, int.class, )



    }


    private Invite mapRowToInvite(SqlRowSet rs) {
        Invite invite = new Invite();
        invite.setInvitationId(rs.getInt("invitation_id");
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
