package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }

    private double getDiscount(Showing showing) {
        double[] availableDiscounts = new double[5];
        int discountCounter = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            availableDiscounts[discountCounter++] = ticketPrice * 0.2;  // 20% discount for special movie
        }

        if (showing.getSequenceOfTheDay() == 1) {
            availableDiscounts[discountCounter++] = 3; // $3 discount for 1st show
        } else if (showing.getSequenceOfTheDay() == 2) {
            availableDiscounts[discountCounter++]  = 2; // $2 discount for 2nd show
        } else if(showing.getSequenceOfTheDay()==7){    // $1 discount for 7th show
            availableDiscounts[discountCounter++] = 1;
        }
        if(isItBetweenElevenAMAnd4PM(showing.getStartTime())){
            availableDiscounts[discountCounter++] = ticketPrice*0.25;   // 25% discount for the showing in between 11a.m - 4 p.m.
        }
        Arrays.sort(availableDiscounts);
        return availableDiscounts[availableDiscounts.length-1];

//        double specialDiscount = 0;
//        if (MOVIE_CODE_SPECIAL == specialCode) {
//            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
//        }
//
//        double sequenceDiscount = 0;
//        if (showSequence == 1) {
//            sequenceDiscount = 3; // $3 discount for 1st show
//        } else if (showSequence == 2) {
//
//            sequenceDiscount = 2; // $2 discount for 2nd show
//        }
//        else {
//            throw new IllegalArgumentException("failed exception");
//        }

        // biggest discount wins
//        return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
    }

    private boolean isItBetweenElevenAMAnd4PM(LocalDateTime showTime){
        LocalDateTime dateTodayElevenAM = LocalDateTime.of(showTime.getYear(),showTime.getMonth(),showTime.getDayOfMonth(),11,0);
        LocalDateTime dateTodayFourPM = LocalDateTime.of(showTime.getYear(),showTime.getMonth(),showTime.getDayOfMonth(),16,0);
        if(showTime.isAfter(dateTodayElevenAM)&&showTime.isBefore(dateTodayFourPM)){
            return true;
        }
        return false;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}