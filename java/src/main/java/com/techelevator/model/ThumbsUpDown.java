package com.techelevator.model;

public class ThumbsUpDown {
    private int ThumbsUp;
    private int ThumbsDown;




    public ThumbsUpDown(int ThumbsUp, int ThumbsDown) {
        this.ThumbsUp = ThumbsUp;
        this.ThumbsDown = ThumbsDown;
    }


    public int getThumbsUp() {
        return this.ThumbsUp;
    }

    public void setThumbsUp(int ThumbsUp) {
        this.ThumbsUp = ThumbsUp;
    }

    public int getThumbsDown() {
        return this.ThumbsDown;
    }

    public void setThumbsDown(int ThumbsDown) {
        this.ThumbsDown = ThumbsDown;
    }

}
