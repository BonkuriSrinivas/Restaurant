package com.example.restaurant;

public class Item {


    private String name;

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    private String cost;

    public Item(String name,String cost) {
        this.name = name;
        this.cost=cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
