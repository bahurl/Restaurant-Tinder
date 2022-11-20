package com.techelevator.dao;

import com.techelevator.model.ThumbsUpDown;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcThumbsUpDownDao implements ThumbsUpDownDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcThumbsUpDownDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ThumbsUpDown> getVote(List<Integer> restaurantIds, int invitationId) {
        String sql = "SELECT * FROM vote WHERE invitation_id = ? AND restaurant_id = ?;";
        SqlRowSet results = null;
        for(Integer restaurantId : restaurantIds ){
             results = jdbcTemplate.queryForRowSet(sql, invitationId, restaurantId);
        }
        List<ThumbsUpDown> thumbsUpDown = new ArrayList<>();
        while(results.next()){
           thumbsUpDown.add(mapRowToThumbsUpDown(results));
        }
        return thumbsUpDown;
    }

    @Override
    public boolean update(ThumbsUpDown vote) {
        String sql = "UPDATE FROM vote(thumbs_up,thumbs_down) WHERE invitation_id = ? AND restaurant_id = ?" +
                "VALUES(? , ?);";
        try{
            jdbcTemplate.update(sql, vote.getThumbsUp(), vote.getThumbsDown());
            return true;
        } catch(Exception e){
            return false;
        }


    }

    @Override
    public boolean createVote(ThumbsUpDown vote) {
        String sql = "INSERT INTO vote(thumbs_up,thumbs_down, restaurant_id, invitation_id)" +
                "VALUES(?, ?, ?, ?);";
        try{
            jdbcTemplate.update(sql, vote.getThumbsUp(), vote.getThumbsDown(), vote.getRestaurantId(), vote.getInvitationId());
            return true;
        } catch(Exception e){
            return false;
        }
    }


    private ThumbsUpDown mapRowToThumbsUpDown(SqlRowSet rs){
        ThumbsUpDown thumbsUpDown = new ThumbsUpDown();
        thumbsUpDown.setThumbsUp(rs.getInt("thumbs_up"));
        thumbsUpDown.setThumbsDown(rs.getInt("thumbs_down"));
        thumbsUpDown.setInvitationId(rs.getInt("invite_id"));
        thumbsUpDown.setRestaurantId(rs.getInt("restaurant_id"));
        return thumbsUpDown;
    }
}
