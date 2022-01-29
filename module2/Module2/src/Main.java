/**
 * 
 *  Module 2 assignment: Main method demonstrating adding items to a box and retrieving box contents.
 *
 * Module 2: 
 *
 * Class: CITC 1319, Spring 2022
 *
 * @author  Billy Bratz
 * @version January 29, 2022
 * 
 * Answer to assignment question "What happens when you add a smaller boxes to a larger box. Why did this happen?"
 * 
 * What happens when you add a smaller box(es) to a larger box is that you get a box of boxes which is completely legal.
 * And you successfully can print the contents of each box. This works because they all implement the boxable interface, and override the toString method.
 * Which means if you call too string on the box itself, it reads the contents of the box, so if your box contains a box the tostring method get calls on the box inside which then reads the items.
 * 
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Box box = new Box(10);

        box.add(new Book("To Kill a Mocking Bird", "Harper Lee", "ISBN-10" +
            "0446310786", 2.4));
        box.add(new Book("Nineteen Eighty-Four", "George Orwell", "ISBN-10" +
                "9780451524935", 1.2));
        box.add(new Book("The Diary of Anne Frank", "Anne Frank", "ISBN-10" +
                "9780553296983", 1.2));
                    box.add(new DVD("The Matrix", "Lana Wachowski", 1999));
        box.add(new DVD("The Godfather", "Francis Ford Coppola", 1972));
        box.add(new DVD("The Good, The Bad, and the Ugly", "Sergio Leone", 1966));
        
        System.out.println(box); //calls toString that you have overridden in Box class.
        
        Box box2 = new Box(3);
        
        box2.add(new DVD("Band of Brothers", "Steven Spielberg", 2001));
        box2.add(new Book("Don Quixote", "Miguel de Cervantes", "ISBN-10" +
                "100142437239", 1.26));
        box2.add(new Book("Moby Dick", "Herman Melville", "ISBN-101503280780", 1.4));
        
        System.out.println(box2); //calls toString that you have overridden in Box class.

        Box newBox = new Box(20);
        newBox.add(box);
        newBox.add(box2);

        System.out.println(newBox); //calls toString that you have overridden in Box class.
    }    
}
