package com.codecool.servlet;

public class Item {

    private static int nextId = 1;
    private int id;
    private String name;
    private double price;

    Item() {
        id = nextId++;
    }

    double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
