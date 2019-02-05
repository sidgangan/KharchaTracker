package com.siddhesh.kharchatracker.SQLite;

public class Aggregate {

    private int food;
    private int travel;
    private int stationary;
    private int other;

    public int getFood() {
        return food;
    }

    public int getTravel() {
        return travel;
    }

    public int getStationary() {
        return stationary;
    }

    public int getOther() {
        return other;
    }

    public int getTotal(){
        return (food + travel + stationary + other);
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setOther(int other) {
        this.other = other;
    }

    public void setStationary(int stationary) {
        this.stationary = stationary;
    }

    public void setTravel(int travel) {
        this.travel = travel;
    }
}
