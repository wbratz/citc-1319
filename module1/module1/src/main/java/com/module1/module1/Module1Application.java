package com.module1.module1;

import java.util.ArrayList;

public class Module1Application {

	public static void main(String[] args) {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Movie("The Shawshank Redemption", 9.99f, 10, "Frank Darabont", "tt0111161"));
		products.add(new Movie("The Godfather", 9.99f, 10, "Francis Ford Coppola", "tt0068646"));
		products.add(new Movie("The Godfather: Part II", 9.99f, 10, "Francis Ford Coppola", "tt0071562"));
		products.add(new Movie("The Dark Knight", 9.99f, 10, "Christopher Nolan", "tt0468569"));
		products.add(new Ebook("The Lord of the Rings", 9.99f, 10, "J.R.R. Tolkien", "978-0-395-19395-8"));
		products.add(new Ebook("The Hobbit", 9.99f, 10, "J.R.R. Tolkien", "978-0-395-19395-8"));

		Movie movie1 = new Movie("The Shawshank Redemption", 9.99f, 10, "Frank Darabont", "tt0111161");
		Movie movie2 = new Movie("The Godfather", 9.99f, 10, "Francis Ford Coppola", "tt0068646");
		Movie movie3 = new Movie("The Godfather: Part II", 9.99f, 10, "Francis Ford Coppola", "tt0071562");

		Ebook ebook1 = new Ebook("The Lord of the Rings", 9.99f, 10, "J.R.R. Tolkien", "978-0-395-19395-8");
		Ebook ebook2 = new Ebook("The Hobbit", 9.99f, 10, "J.R.R. Tolkien", "978-0-395-19395-8");

		System.out.println("Example of toString method" + "\n\n" + movie1.toString() + "\n\n");
		System.out.println("Example of toString method" + "\n\n" + ebook1.toString() + "\n\n");
		System.out.println("Buy method invoked for " + movie1.getName() + "\n\n");
		movie1.buy();
		System.out.println(movie1.toString());

		System.out.println("Buy method invoked for " + ebook1.getName() + "\n\n");
		ebook1.buy();
		System.out.println(ebook1.toString());
	}

}
