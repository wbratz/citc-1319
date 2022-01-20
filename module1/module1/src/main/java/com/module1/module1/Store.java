package com.module1.module1;

import java.util.ArrayList;

/**
 * Module 1 assignment: Store class
 * 
 * implements concrete product classes and searches inventory by name
 *
 * Class: CITC 1319, Spring 2022
 *
 * @author William Bratz
 * @version January 19, 2022
 */
public class Store {
    private static ArrayList<Product> products = new ArrayList<>();

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static ArrayList searchProductName(String name) {
        ArrayList<Product> foundProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getName().contains(name)) {
                foundProducts.add(product);
            }
        }

        return foundProducts;
    }

    public static void main(String[] args) {
        Ebook ebook1 = new Ebook("The Lord of the Rings", 9.99f, 10, "J.R.R. Tolkien", "978-0-395-19395-8");
        Ebook ebook2 = new Ebook("The Hobbit", 9.99f, 10, "J.R.R. Tolkien", "978-0-395-19395-8");
        Ebook ebook3 = new Ebook("Odyssey", 9.99f, 10, "Homer", "978-0-395-19395-8");

        Movie movie1 = new Movie("The Shawshank Redemption", 9.99f, 10, "Frank Darabont", "tt0111161");
        Movie movie2 = new Movie("The Godfather", 9.99f, 10, "Francis Ford Coppola", "tt0068646");
        Movie movie3 = new Movie("The Godfather: Part II", 9.99f, 10, "Francis Ford Coppola", "tt0071562");

        addProduct(ebook1);
        addProduct(ebook2);
        addProduct(ebook3);
        addProduct(movie1);
        addProduct(movie2);
        addProduct(movie3);

        System.out.println("Searching for Lord");
        ArrayList<Product> result = searchProductName("Lord");

        for (Product product : result) {
            System.out.println(product.toString());
        }

        System.out.println("Searching for The");
        ArrayList<Product> result2 = searchProductName("The");

        for (Product product : result2) {
            System.out.println(product.toString() + "\n\n");
        }
    }
}
