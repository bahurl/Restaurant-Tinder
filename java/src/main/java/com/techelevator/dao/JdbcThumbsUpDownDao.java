package com.techelevator.dao;

import com.techelevator.model.ThumbsUpDown;
import com.techelevator.model.VoteRequest;
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
    public List<ThumbsUpDown> getVote(VoteRequest voteRequest) {
        String sql = "SELECT * FROM vote WHERE invite_id = ? AND restaurant_id = ?;";
        List<SqlRowSet> results = new ArrayList<>();
        for(Integer restaurantId : voteRequest.getRestaurantIds() ){
             results.add(jdbcTemplate.queryForRowSet(sql, voteRequest.getInvitationId(), restaurantId));
        }
        List<ThumbsUpDown> thumbsUpDown = new ArrayList<>();
        for(SqlRowSet i : results){
            while(i.next()) {
                thumbsUpDown.add(mapRowToThumbsUpDown(i));
            }
        }
        return thumbsUpDown;
    }

    @Override
    public boolean update(ThumbsUpDown vote) {
        String sql = "UPDATE FROM vote(thumbs_up,thumbs_down) WHERE invite_id = ? AND restaurant_id = ?" +
                "VALUES(? , ?);";
        try{
            jdbcTemplate.update(sql, vote.getInvitationId(),vote.getRestaurantId(),vote.getThumbsUp(), vote.getThumbsDown());
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean createVote(ThumbsUpDown vote) {
        String sql = "INSERT INTO vote(thumbs_up,thumbs_down, restaurant_id, invite_id)" +
                "VALUES(?, ?, ?, ?);";
        try{
            jdbcTemplate.update(sql, vote.getThumbsUp(), vote.getThumbsDown(), vote.getRestaurantId(), vote.getInvitationId());
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean doesExist(List<Integer> restaurantIds, int invitationId) {
        String sql = "SELECT ";
                return false;
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
