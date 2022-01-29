
/**
 * 
 *  Module 2 assignment: Book Class implements boxable
 *
 * Module 2: 
 *
 * Class: CITC 1319, Spring 2022
 *
 * @author  Billy Bratz
 * @version January 29, 2022
 * 
 */

public class Book implements Boxable {

    private String author;
    private String name;
    private String isbn;
    private double weight;

    //constructor
    public Book(String author, String name, String isbn, double weight) {
        this.setAuthor(author);
        this.setName(name);
        this.setIsbn(isbn);
        this.setWeight(weight);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + ", " + author + ", " + isbn + ", " + weight + " lbs.";
    }

    @Override
    public double Weight() {
        return weight;
    }
}
