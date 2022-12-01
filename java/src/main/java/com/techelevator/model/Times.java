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
        int dayFrom = 0;
        int dayTo = 0;
        if (getDayFrom().equals("Sunday")){
            dayFrom = 1;
        } else if (getDayFrom().equals("Monday")) {
            dayFrom = 2;
        } else if (getDayFrom().equals("Tuesday")){
            dayFrom = 3;
        } else if (getDayFrom().equals("Wednesday")) {
            dayFrom = 4;
        }else if (getDayFrom().equals("thursday")){
            dayFrom = 5;
        } else if (getDayFrom().equals("Friday")) {
            dayFrom = 6;
        } else if (getDayFrom().equals("Saturday")) {
            dayFrom = 7;
        }
        if (getDayTo().equals("Sunday")){
            dayTo = 1;
        } else if (getDayTo().equals("Monday")) {
            dayTo = 2;
        } else if (getDayTo().equals("Tuesday")){
            dayTo = 3;
        } else if (getDayTo().equals("Wednesday")) {
            dayTo = 4;
        }else if (getDayTo().equals("thursday")){
            dayTo = 5;
        } else if (getDayTo().equals("Friday")) {
            dayTo = 6;
        } else if (getDayTo().equals("Saturday")) {
            dayTo = 7;
        }
        Calendar date = Calendar.getInstance();
        int currentHour = date.get(Calendar.HOUR_OF_DAY);
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        if (dayFrom <= dayOfWeek && dayTo >= dayOfWeek) {
            if (timeOpen > timeClose && currentHour < timeClose) {
                currentHour += 24;
            }
            if (timeOpen > timeClose) {
                timeClose += 24;
            }
            if (timeOpen < currentHour && currentHour < timeClose) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

}
