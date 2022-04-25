package com.company.books;

public class Book {

    // define properties
    private int id;
    private String name;
    private int price;

    // Define constructor
    public Book(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    // define getter
    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
