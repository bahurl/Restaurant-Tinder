package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcVotingDataDao implements VotingDataDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcVotingDataDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Invite getInviteByLinkId(String id) {
        Invite invite = null;
        String sql = "SELECT * from invitations where invitation_link = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            invite = mapRowToInvite(results);

            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime inviteDateTime = invite.getInvitationDate();
            if (currentDateTime.isAfter(inviteDateTime)) {
                invite = null;
            }
        }
        return invite;
    }

    public Invite getExpiredInviteById(String id){
        Invite invite = null;
        String sql = "SELECT * from invitations where invitation_link = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            invite = mapRowToInvite(results);
        }
        return invite;
    }


    public List<Restaurant> getRestaurantsFromExpiredLink(String location, int id){
        String sql = "SELECT * FROM restaurants as res " +
                " JOIN vote as v on v.restaurant_id = res.restaurant_id" +
                " WHERE (res.city = ? OR res.zip_code = ?) AND  v.invite_id = ? AND v.thumbs_up >= v.thumbs_down;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location,location,id);
        List<Restaurant> restaurants = new ArrayList<>();
        while(results.next()) {
            restaurants.add(mapRowToRestaurants(results));
        }
        return restaurants;
    }
    @Override
    public List<Restaurant> getNearbyRestaurants(String location) {
        String sql = "SELECT * from restaurants where city = ? OR zip_code = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location,location);
        List<Restaurant> restaurant = new ArrayList<>();
        while(results.next()) {
            restaurant.add(mapRowToRestaurants(results));
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> getNearbyRestaurants(String location, String type) {
        String sql = "SELECT * from restaurants where upper(city) = ? OR zip_code = ? AND type = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, location.toUpperCase(),location, type);
        List<Restaurant> restaurant = new ArrayList<>();
        if(results.next()) {
            restaurant.add(mapRowToRestaurants(results));
        } else {
            throw new RuntimeException("location "+location+" was not found.");
        }
        return restaurant;
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
    public VotingData getVotingData(String inviteId) {
        VotingData vote = new VotingData();
        vote.setInvite(getInviteByLinkId(inviteId));
        if(vote.getInvite() != null) {
            vote.setExpired(false);
            String location = "";
            if (vote.getInvite().getCity() == null) {
                location = vote.getInvite().getZipCode();
            } else {
                location = vote.getInvite().getCity();
            }
            List<Restaurant> restaurantList = getNearbyRestaurants(location);
            List<Integer> restaurantIds = new ArrayList<>();
            for (Restaurant restaurant : restaurantList) {
                restaurantIds.add(restaurant.getRestaurantId());
            }
            vote.setRestaurants(restaurantList);
            vote.setRestaurantIds(restaurantIds);
            VoteRequest request = new VoteRequest();
            request.setInvitationId(vote.getInvite().getInvitationId());
            request.setRestaurantIds(restaurantIds);
            if (getVote(request).size() < 1) {
                for (Restaurant restaurant : restaurantList) {
                    ThumbsUpDown thumbsUpDown = new ThumbsUpDown(0, 0, vote.getInvite().getInvitationId(), restaurant.getRestaurantId());
                    createVote(thumbsUpDown);
                }
            }
            vote.setVote(getVote(request));
        } else{
            vote.setExpired(true);
            vote.setInvite(getExpiredInviteById(inviteId));
            if(vote.getInvite() != null) {
                String location = "";
                if (vote.getInvite().getCity() == null) {
                    location = vote.getInvite().getZipCode();
                } else {
                    location = vote.getInvite().getCity();
                }
                List<Restaurant> restaurantList = getRestaurantsFromExpiredLink(location,vote.getInvite().getInvitationId());
                List<Integer> restaurantIds = new ArrayList<>();
                for (Restaurant restaurant : restaurantList) {
                    restaurantIds.add(restaurant.getRestaurantId());
                }
                vote.setRestaurants(restaurantList);
                vote.setRestaurantIds(restaurantIds);
                VoteRequest request = new VoteRequest();
                request.setInvitationId(vote.getInvite().getInvitationId());
                request.setRestaurantIds(restaurantIds);
                if (getVote(request).size() < 1) {
                    for (Restaurant restaurant : restaurantList) {
                        ThumbsUpDown thumbsUpDown = new ThumbsUpDown(0, 0, vote.getInvite().getInvitationId(), restaurant.getRestaurantId());
                        createVote(thumbsUpDown);
                    }
                }
                vote.setVote(getVote(request));
            }

        }

        return vote;
    }
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
    private Restaurant mapRowToRestaurants(SqlRowSet rs) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("restaurant_id"));
        restaurant.setAddress1(rs.getString("address1"));
        restaurant.setAddress2(rs.getString("address2"));
        restaurant.setAddress3(rs.getString("address3"));
        restaurant.setCity(rs.getString("city"));
        restaurant.setState(rs.getString("state"));
        restaurant.setName(rs.getString("name"));
        restaurant.setZipCode(rs.getString("zip_code"));
        restaurant.setPhone(rs.getString("phone"));
        restaurant.setImgUrl(rs.getString("img_url"));
        restaurant.setRating(rs.getFloat("rating"));
        restaurant.setOpenHour(rs.getString("open_hour"));
        restaurant.setCloseHour(rs.getString("close_hour"));
        restaurant.setType(rs.getString("type"));
        return restaurant;
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
