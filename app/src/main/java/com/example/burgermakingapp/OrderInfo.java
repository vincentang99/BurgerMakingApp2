package com.example.burgermakingapp;

public class OrderInfo {
    private String burgertype;
    private String doneness;
    
    public String getBurgertype() {
        return burgertype;
    }

    public String getDoneness() {
        return doneness;
    }

    public void setBurgertype(String burgertype) {
        this.burgertype = burgertype;
    }

    public void setDoneness(String doneness) {
        this.doneness = doneness;
    }

    public OrderInfo(){
    }
}

