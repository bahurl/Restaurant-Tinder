package com.techelevator.model;

import java.util.Calendar;
import java.util.Date;

public class Times {
    private String dayFrom;
    private String dayTo;
    private int open;
    private int Close;
    private boolean isOpen;

    public String getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(String dayFrom) {
        this.dayFrom = dayFrom;
    }

    public String getDayTo() {
        return dayTo;
    }

    public void setDayTo(String dayTo) {
        this.dayTo = dayTo;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getClose() {
        return Close;
    }

    public void setClose(int close) {
        Close = close;
    }

    public boolean isOpen() {
        int timeOpen = getOpen();
        int timeClose = getClose();
        Calendar date = Calendar.getInstance();
        int currentHour = date.get(Calendar.HOUR_OF_DAY);
        if(timeOpen > timeClose && currentHour < timeClose ){
            currentHour += 24;
        }
        if(timeOpen > timeClose ){
            timeClose += 24;
        }
        if(timeOpen < currentHour && currentHour < timeClose){
            return true;
        } else{
            return false;
        }
    }

}
