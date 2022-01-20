package com.module1.module1;

/**
 * Module 1 assignment: Movie class extends Product class
 * and adds director and imdb
 *
 * Class: CITC 1319, Spring 2022
 *
 * @author William Bratz
 * @version January 19, 2022
 */
public class Movie extends Product {

    private String director;
    private String imdb;

    public Movie(String name, float price, int quantity, String director, String imdb) {
        super(name, price, quantity);
        this.setDirector(director);
        this.setImdb(imdb);
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Name: " + getName()
                + "\n" + "Price: " + getPrice()
                + "\n" + "Quantity: " + getQuantity()
                + "\n" + "Director: " + getDirector()
                + "\n" + "IMDB: " + getImdb();
    }

}
