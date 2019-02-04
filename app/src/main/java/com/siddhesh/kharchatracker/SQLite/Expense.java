package com.siddhesh.kharchatracker.SQLite;

public class Expense {

    private String category;
    private String description;
    private int amount;
    private String day;
    private String month;
    private String year;

    public Expense(String cat , String desc, int amt , String day, String month, String year){
        this.category = cat;
        this.description = desc;
        this.amount = amt;
        this.day = day;
        this.month = month;
        this.year = year;

    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public int getAmount() {
        return amount;
    }
}
