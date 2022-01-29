import java.util.ArrayList;

/**
 * 
 *  Module 2 assignment: Box Class implements boxable
 *
 * Module 2: 
 *
 * Class: CITC 1319, Spring 2022
 *
 * @author  Billy Bratz
 * @version January 29, 2022
 * 
 */

public class Box implements Boxable{
    private double maxWeight;
    private double currentWeight;
    private ArrayList<Boxable> contents;

    public Box(double maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
        this.contents = new ArrayList<Boxable>();
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public ArrayList<Boxable> getContents() {
        return contents;
    }

    public void add(Boxable item) {
        if (item.Weight() + this.currentWeight > this.maxWeight) {
            System.out.println("Item is too heavy to add to box.");
        } else {
            this.contents.add(item);
            this.currentWeight += item.Weight();
        }
    }

    public void remove(Boxable item) {
        if (this.contents.contains(item)) {
            this.contents.remove(item);
            this.currentWeight -= item.Weight();
        } else {
            System.out.println("Item is not in box.");
        }
    }

    public void printContents() {
        for (Boxable item : this.contents) {
            System.out.println(item.toString());
        }
    }

    @Override
    public String toString() {       
        String returnString = "Box: " + contents.size() + " items, " + "total weight " + getCurrentWeight() + " lbs";

        for (Boxable item : this.contents) {
            returnString += "\n" + item.toString();
        }

        return returnString;
    }

    @Override
    public double Weight() {
        double weight = 0;

        for (Boxable item : this.contents) {
            weight += item.Weight();
        }

        return weight;
    }
}
