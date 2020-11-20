package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models;

public class TicketModel {

    private final String name;
    private final String description;
    private final double price;
    private final String currency;
    private final String buyAddress;

    public TicketModel(String name, String description, double price, String currency, String buyAddress) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.buyAddress = buyAddress;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getBuyAddress() {
        return buyAddress;
    }
}
