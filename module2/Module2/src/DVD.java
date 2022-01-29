/**
 * 
 *  Module 2 assignment: DVD Class implements boxable
 *
 * Module 2: 
 *
 * Class: CITC 1319, Spring 2022
 *
 * @author  Billy Bratz
 * @version January 29, 2022
 * 
 */

public class DVD implements Boxable {
    
    private String name;
    private String director;
    private int year;
    private double weight;

    //constructor
    public DVD(String name, String director, int year) {
        this.setName(name);
        this.setDirector(director);
        this.setYear(year);
        this.weight = 0.2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return name + ", " + director + ", " + year + ", " + weight + "lbs.";
    }

    @Override
    public double Weight() {
        return weight;
    }

}
