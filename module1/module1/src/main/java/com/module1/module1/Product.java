package com.module1.module1;

/**
 * Module 1 assignment: Product class
 * 
 *
 * Class: CITC 1319, Spring 2022
 *
 * @author William Bratz
 * @version January 19, 2022
 */

public abstract class Product {
    private String name;
    private float price;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected Product(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void buy() {
        quantity--;
    }
}
