package com.module1.module1;

/**
 * Module 1 assignment: Ebook class
 * extends Product class and adds author and isbn
 * buy does not change quantity
 * 
 * Class: CITC 1319, Spring 2022
 *
 * @author William Bratz
 * @version January 19, 2022
 */
public class Ebook extends Product {

    private String author;
    private String isbn;

    protected Ebook(String name, float price, int quantity) {
        super(name, price, quantity);

    }

    public Ebook(String name, float price, int quantity, String author, String isbn) {
        super(name, price, quantity);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public void buy() {
        // intentionally left blank
    }

    @Override
    public String toString() {
        return "Name: " + getName()
                + "\n" + "Price: " + getPrice()
                + "\n" + "Quantity: " + getQuantity()
                + "\n" + "Author: " + getAuthor()
                + "\n" + "ISBN: " + getIsbn();
    }
}
