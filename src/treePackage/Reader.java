package treePackage;
import java.util.*;
import java.io.*;
/**
 * The reader class. This class reads the file and makes print operations.
 * @author Berke Can Kandemir and Cem Ozan.
 */
public class Reader {
	Scanner file = null;
	Scanner keyboard = null;
	IMedia media = null;
	IBinarySearchTree<IMedia> tree = new BinarySearchTree<IMedia>();
	Iterator<IMedia> iterator = null;
	/**
	 * The reader method.
	 * The method first reads the file and add the objects into the tree.
	 * Then prints the requirements of the homework to the console.
	 */
	public void fileReader() {
		String mediaType;
		String name;
		int price;
		int year;
		String authorName;
		String directorName;
		String actressName;
		String actorName;
		try {
			file = new Scanner(new File("CENG112_HW4_Media.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		
		while (file.hasNextLine()) {
			String line = file.nextLine();
			String[] lineArr = line.split(",");
			mediaType = lineArr[0];
			name = lineArr[1];
			price = Integer.parseInt(lineArr[2]);
			year = Integer.parseInt(lineArr[3]);
			if (mediaType.equals("Book")) {
				authorName = lineArr[4];
				media = new Book(name, price, year, authorName);
				tree.add(media);
			} else {
				directorName = lineArr[4];
				if (lineArr[5].equals(" ")) {
					actressName = null;
				} else {
					actressName = lineArr[5];
				}
				if (lineArr[6].equals(" ")) {
					actorName = null;
				} else {
					actorName = lineArr[6];
				}
				media = new Movie(name, price, year, directorName, actressName, actorName);
				tree.add(media);
			}
		}
		/**
		 * Here is the printing section.
		 */
		System.out.println("               * * * IZTECH MEDIA MARKET * * *");
		int choice;
		boolean iterate = true;
		keyboard = new Scanner(System.in);
		while (iterate) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("Please enter '1' to print the minimum priced book whose author name is that you will enter.");
			System.out.println("Please enter '2' to print the maximum priced book whose author name is that you will enter.");
			System.out.println("Please enter '3' to print the minimum priced movie whose director name that you will enter.");
			System.out.println("Please enter '4' to print the maximum priced movie whose director name that you will enter.");
			System.out.println("Please enter '5' to print all media whose prices are less or equal than the amount that you will enter.");
			System.out.println("Please enter '6' to print all media whose prices are greater than the amount that you will enter.");
			System.out.println("Please enter '7' to print all media in descending order in terms of the price.");
			System.out.println("Please enter '8' to print all media in ascending order in terms of the price.");
			System.out.println("Please enter '9' to print all books in descending order in terms of the price.");
			System.out.println("Please enter '10' to print all books in ascending order in terms of the price.");
			System.out.println("Please enter '11' to print all movies in descending order in terms of the price.");
			System.out.println("Please enter '12' to print all movies in ascending order in terms of the price.");
			System.out.println("Please enter '0' to quit.");
			System.out.println("");
			System.out.print("Choice: ");
			choice = keyboard.nextInt();
			System.out.println("-----------------------------------------------------------------");
			if (choice == 0) {
				System.out.println("We'd like to see you again!");
				System.out.println("-----------------------------------------------------------------");
				System.out.println("             \u00a9 Copyright 2019 IZTECH Media Market");
				iterate = false;
			} else if (choice == 1) {
				authorNames();
				System.out.println("---------------------------");
				minPriBook();
			} else if (choice == 2) {
				authorNames();
				System.out.println("---------------------------");
				maxPriBook();
			} else if (choice == 3) {
				directorNames();
				System.out.println("-----------------------------");
				minPriMovie();
			} else if (choice == 4) {
				directorNames();
				System.out.println("-----------------------------");
				maxPriMovie();
			} else if (choice == 5) {
				priLess();
			} else if (choice == 6) {
				priGreater();
			} else if (choice == 7) {
				printAllDescending();
			} else if (choice == 8) {
				printAllAscending();
			} else if (choice == 9) {
				printBookDescending();
			} else if (choice == 10) {
				printBookAscending();
			} else if (choice == 11) {
				printMovieDescending();
			} else if (choice == 12) {
				printMovieAscending();
			} else {
				System.out.println("Wrong input!");
			}
		}
		
		
	}
	/**
	 * This method prints the usable author names to the console.
	 * First it takes all the author names.
	 * Then it gets rid of the duplicate names.
	 * Lastly, prints the elements of the array without nulls.
	 */
	public void authorNames() {
		iterator = tree.getInorderIterator();
		String nameArr[] = new String[tree.getNumberOfNodes()];
		String copyNameArr[];
		int itemCounter = 0;
		IMedia current = iterator.next();
		int count = 0;
		while (iterator.hasNext()) {
			boolean contains = false;
			if (current.mediaType().equals("Book")) {
				if (count > 0) {
					for (int i = 0; i < nameArr.length; i++) {
						if (nameArr[i] != null) {
							if (nameArr[i].equals(((Book) current).getAuthorName())) {
								contains = true;
								count--;
							}
						}
					}
				}
				if (contains == false) {
					nameArr[count] = ((Book) current).getAuthorName();
				}
				count++;
			}
			current = iterator.next();
		}
		
		for (int i = 0; i < nameArr.length; i++) {
			if (nameArr[i] != null) {
				itemCounter++;
			}
		}
		copyNameArr = new String[itemCounter];
		for (int i = 0; i < copyNameArr.length; i++) {
			copyNameArr[i] = nameArr[i];
		}
		System.out.print("The Available Authors List: ");
		for (int i = 0; i < copyNameArr.length; i++) {
			if (i == (copyNameArr.length - 1)) {
				System.out.print(copyNameArr[i]);
			} else {
				System.out.print(copyNameArr[i] + ", ");
			}
		}
		System.out.println("");
	}
	/**
	 * This method prints the usable director names to the console.
	 * First it takes all the author names.
	 * Then it gets rid of the duplicate names.
	 * Lastly, prints the elements of the array without nulls.
	 */
	public void directorNames() {
		iterator = tree.getInorderIterator();
		String nameArr[] = new String[tree.getNumberOfNodes()];
		String copyNameArr[];
		int itemCounter = 0;
		IMedia current = iterator.next();
		int count = 0;
		while (iterator.hasNext()) {
			boolean contains = false;
			if (current.mediaType().equals("Movie")) {
				if (count > 0) {
					for (int i = 0; i < nameArr.length; i++) {
						if (nameArr[i] != null) {
							if (nameArr[i].equals(((Movie) current).getDirectorName())) {
								contains = true;
								count--;
							}
						}
					}
				}
				if (contains == false) {
					nameArr[count] = ((Movie) current).getDirectorName();
				}
				count++;
			}
			current = iterator.next();
		}
		
		for (int i = 0; i < nameArr.length; i++) {
			if (nameArr[i] != null) {
				itemCounter++;
			}
		}
		copyNameArr = new String[itemCounter];
		for (int i = 0; i < copyNameArr.length; i++) {
			copyNameArr[i] = nameArr[i];
		}
		System.out.print("The Available Directors List: ");
		for (int i = 0; i < copyNameArr.length; i++) {
			if (i == (copyNameArr.length - 1)) {
				System.out.print(copyNameArr[i]);
			} else {
				System.out.print(copyNameArr[i] + ", ");
			}
		}
		System.out.println("");
	}
	/**
	 * This method finds the minimum priced book of the given author from the console.
	 * It takes the author name and search for his/her books.
	 * When it finds the books, it compares the prices.
	 * Lastly, prints the minimum priced one.
	 */
	public void minPriBook() {
		iterator = tree.getInorderIterator();
		String nameArr[] = new String[tree.getNumberOfNodes()];
		int priceArr[] = new int[tree.getNumberOfNodes()];
		int count = 0;
		IMedia current;
		keyboard = new Scanner(System.in);
		System.out.print("Please enter the author name to reach his/her minimum priced book: ");
		String name = keyboard.nextLine();
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaType().equals("Book")) {
				if (((Book) current).getAuthorName().equals(name)) {
					nameArr[count] = current.mediaName();
					priceArr[count] = current.mediaPrice();
					count++;
				}
			}
		}
		int result = 0;
		int counter = 0;
		for (int i = 0; i < priceArr.length; i++) {
			if (i == (priceArr.length - 1)) {
				break;
			} else if (priceArr[i] < priceArr[i + 1]) {
				if (counter == 0) {
					result = i;
					counter++;
				} else if (priceArr[i] < result) {
					result = priceArr[i];
					counter++;
				}
			} else {
				if (priceArr[i + 1] != 0) {
					result = i + 1;
				}
			}
			
		}
		if (nameArr[result] == null) {
			System.out.println("");
			System.out.println("No such book of this writer in stock!");
		} else {
			System.out.println("");
			System.out.println(nameArr[result] + " - " + priceArr[result] + " TL");
		}
	}
	/**
	 * This method finds the maximum priced book of the given author from the console.
	 * It takes the author name and search for his/her books.
	 * When it finds the books, it compares the prices.
	 * Lastly, prints the maximum priced one.
	 */
	public void maxPriBook() {
		iterator = tree.getInorderIterator();
		String nameArr[] = new String[tree.getNumberOfNodes()];
		int priceArr[] = new int[tree.getNumberOfNodes()];
		int count = 0;
		IMedia current;
		keyboard = new Scanner(System.in);
		System.out.print("Please enter the author name to reach his/her maximum priced book: ");
		String name = keyboard.nextLine();
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaType().equals("Book")) {
				if (((Book) current).getAuthorName().equals(name)) {
					nameArr[count] = current.mediaName();
					priceArr[count] = current.mediaPrice();
					count++;
				}
			}
		}
		int result = 0;
		int counter = 0;
		for (int i = 0; i < priceArr.length; i++) {
			if (i == (priceArr.length - 1)) {
				break;
			} else if (priceArr[i] > priceArr[i + 1]) {
				if (counter == 0) {
					result = i;
					counter++;
				} else if (priceArr[i] > result) {
					result = priceArr[i];
					counter++;
				}
			} else {
				if (priceArr[i + 1] != 0) {
					result = i + 1;
				}
			}
			
		}
		if (nameArr[result] == null) {
			System.out.println("");
			System.out.println("No such book of this writer in stock!");
		} else {
			System.out.println("");
			System.out.println(nameArr[result] + " - " + priceArr[result] + " TL");
		}
	}
	/**
	 * This method finds the minimum priced movie of the given director from the console.
	 * It takes the director name and search for his/her movies.
	 * When it finds the movies, it compares the prices.
	 * Lastly, prints the minimum priced one.
	 */
	public void minPriMovie() {
		iterator = tree.getInorderIterator();
		String nameArr[] = new String[tree.getNumberOfNodes()];
		int priceArr[] = new int[tree.getNumberOfNodes()];
		int count = 0;
		IMedia current;
		keyboard = new Scanner(System.in);
		System.out.print("Please enter the director name to reach his/her minimum priced movie: ");
		String name = keyboard.nextLine();
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaType().equals("Movie")) {
				if (((Movie) current).getDirectorName().equals(name)) {
					nameArr[count] = current.mediaName();
					priceArr[count] = current.mediaPrice();
					count++;
				}
			}
		}
		int result = 0;
		int counter = 0;
		for (int i = 0; i < priceArr.length; i++) {
			if (i == (priceArr.length - 1)) {
				break;
			} else if (priceArr[i] < priceArr[i + 1]) {
				if (counter == 0) {
					result = i;
					counter++;
				} else if (priceArr[i] < result) {
					result = priceArr[i];
					counter++;
				}
			} else {
				if (priceArr[i + 1] != 0) {
					result = i + 1;
				}
			}
			
		}
		if (nameArr[result] == null) {
			System.out.println("");
			System.out.println("No such movie of this director in stock!");
		} else {
			System.out.println("");
			System.out.println(nameArr[result] + " - " + priceArr[result] + " TL");
		}
	}
	/**
	 * This method finds the maximum priced movie of the given director from the console.
	 * It takes the director name and search for his/her movies.
	 * When it finds the movies, it compares the prices.
	 * Lastly, prints the maximum priced one.
	 */
	public void maxPriMovie() {
		iterator = tree.getInorderIterator();
		String nameArr[] = new String[tree.getNumberOfNodes()];
		int priceArr[] = new int[tree.getNumberOfNodes()];
		int count = 0;
		IMedia current;
		keyboard = new Scanner(System.in);
		System.out.print("Please enter the director name to reach his/her maximum priced movie: ");
		String name = keyboard.nextLine();
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaType().equals("Movie")) {
				if (((Movie) current).getDirectorName().equals(name)) {
					nameArr[count] = current.mediaName();
					priceArr[count] = current.mediaPrice();
					count++;
				}
			}
		}
		int result = 0;
		int counter = 0;
		for (int i = 0; i < priceArr.length; i++) {
			if (i == (priceArr.length - 1)) {
				break;
			} else if (priceArr[i] > priceArr[i + 1]) {
				if (counter == 0) {
					result = i;
					counter++;
				} else if (priceArr[i] > result) {
					result = priceArr[i];
					counter++;
				}
			} else {
				if (priceArr[i + 1] != 0) {
					result = i + 1;
				}
			}
			
		}
		if (nameArr[result] == null) {
			System.out.println("");
			System.out.println("No such movie of this director in stock!");
		} else {
			System.out.println("");
			System.out.println(nameArr[result] + " - " + priceArr[result] + " TL");
		}
	}
	/**
	 * This method finds the media object with price less than or equal to the taken one from console.
	 * It iterates whole tree and prints the objects with less price than or equal to the taken one from console.
	 */
	public void priLess() {
		int count = 0;
		iterator = tree.getInorderIterator();
		keyboard = new Scanner(System.in);
		System.out.print("Please enter the upper price limit: ");
		int price = keyboard.nextInt();
		IMedia current;
		System.out.println("");
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaPrice() <= price) {
				System.out.println(current + " - " + current.mediaPrice() + " TL");
				count++;
			}
		}
		if (price < 0) {
			System.out.println("Wrong input!");
		} else if (count == 0) {
			System.out.println("No such media cheaper than " + price + " TL!");
		}
	}
	/**
	 * This method finds the media object with price more than the taken one from console.
	 * It iterates whole tree and prints the objects with price more than the taken one from console.
	 */
	public void priGreater() {
		int count = 0;
		iterator = tree.getInorderIterator();
		keyboard = new Scanner(System.in);
		System.out.print("Please enter the lower price limit: ");
		int price = keyboard.nextInt();
		IMedia current;
		System.out.println("");
		while (iterator.hasNext()) {
			current = iterator.next();
			if (current.mediaPrice() > price) {
				System.out.println(current + " - " + current.mediaPrice() + " TL");
				count++;
			}
		} 
		if (price < 0) {
			System.out.println("Wrong input!");
		} else if (count == 0) {
			System.out.println("No such media more expensive than " + price + " TL!");
		}
	}
	/**
	 * This method prints all media objects with the descending order in terms of the price.
	 * It basically print the tree with inorder method but reverse.
	 */
	public void printAllDescending() {
		System.out.println("Descending order of all media products according to price: ");
		System.out.println("");
		iterator = tree.getInorderIterator();
		int number = tree.getNumberOfNodes();
		String nameArr[] = new String[number];
		int priceArr[] = new int[number];
		IMedia current;
		while (number > 0) {
			current = iterator.next();
			nameArr[number - 1] = current.mediaName();
			priceArr[number - 1] = current.mediaPrice();
			number--;
		}
		
		for (int i = 0; i < nameArr.length; i++) {
			System.out.println(nameArr[i] + " - " + priceArr[i] + " TL");
		}
	}
	/**
	 * This method prints all media objects with the ascending order in terms of the price.
	 * It basically print the tree with inorder method.
	 */
	public void printAllAscending() {
		System.out.println("Ascending order of all media products according to price: ");
		System.out.println("");
		iterator = tree.getInorderIterator();
		int number = 0;
		int range = tree.getNumberOfNodes();
		String nameArr[] = new String[range];
		int priceArr[] = new int[range];
		IMedia current;
		while (number < range) {
			current = iterator.next();
			nameArr[number] = current.mediaName();
			priceArr[number] = current.mediaPrice();
			number++;
		}
		
		for (int i = 0; i < nameArr.length; i++) {
			if (nameArr[i] != null) {
				System.out.println(nameArr[i] + " - " + priceArr[i] + " TL");
			}
		}
	}
	/**
	 * This method prints book objects with the descending order in terms of the price.
	 * It basically print the tree with inorder method but reverse.
	 */
	public void printBookDescending() {
		System.out.println("Descending order of books according to price: ");
		System.out.println("");
		iterator = tree.getInorderIterator();
		int number = tree.getNumberOfNodes();
		String nameArr[] = new String[number];
		int priceArr[] = new int[number];
		IMedia current;
		while (number > 0) {
			current = iterator.next();
			if (current.mediaType().equals("Book")) {
				nameArr[number - 1] = current.mediaName();
				priceArr[number - 1] = current.mediaPrice();
				}
			number--;
		}
		
		for (int i = 0; i < nameArr.length; i++) {
			if (nameArr[i] != null) {
				System.out.println(nameArr[i] + " - " + priceArr[i] + " TL");
			}
		}
	}
	/**
	 * This method prints book objects with the ascending order in terms of the price.
	 * It basically print the tree with inorder method.
	 */
	public void printBookAscending() {
		System.out.println("Ascending order of books according to price: ");
		System.out.println("");
		iterator = tree.getInorderIterator();
		int number = 0;
		int range = tree.getNumberOfNodes();
		String nameArr[] = new String[range];
		int priceArr[] = new int[range];
		IMedia current;
		while (number < range) {
			current = iterator.next();
			if (current.mediaType().equals("Book")) {
				nameArr[number] = current.mediaName();
				priceArr[number] = current.mediaPrice();
				}
			number++;
		}
		
		for (int i = 0; i < nameArr.length; i++) {
			if (nameArr[i] != null) {
				System.out.println(nameArr[i] + " - " + priceArr[i] + " TL");
			}
		}
	}
	/**
	 * This method prints movie objects with the descending order in terms of the price.
	 * It basically print the tree with inorder method but reverse.
	 */
	public void printMovieDescending() {
		System.out.println("Descending order of movies according to price: ");
		System.out.println("");
		iterator = tree.getInorderIterator();
		int number = tree.getNumberOfNodes();
		String nameArr[] = new String[number];
		int priceArr[] = new int[number];
		IMedia current;
		while (number > 0) {
			current = iterator.next();
			if (current.mediaType().equals("Movie")) {
				nameArr[number - 1] = current.mediaName();
				priceArr[number - 1] = current.mediaPrice();
				}

			number--;
		}
		
		for (int i = 0; i < nameArr.length; i++) {
			if (nameArr[i] != null) {
				System.out.println(nameArr[i] + " - " + priceArr[i] + " TL");
			}
		}
	}
	/**
	 * This method prints movie objects with the ascending order in terms of the price.
	 * It basically print the tree with inorder method.
	 */
	public void printMovieAscending(){
		System.out.println("Ascending order of movies according to price: ");
		System.out.println("");
		iterator = tree.getInorderIterator();
		int number = 0;
		int range = tree.getNumberOfNodes();
		String nameArr[] = new String[range];
		int priceArr[] = new int[range];
		IMedia current;
		while (number < range) {
			current = iterator.next();
			if (current.mediaType().equals("Movie")) {
				nameArr[number] = current.mediaName();
				priceArr[number] = current.mediaPrice();
				}
			number++;
		}
		
		for (int i = 0; i < nameArr.length; i++) {
			if (nameArr[i] != null) {
				System.out.println(nameArr[i] + " - " + priceArr[i] + " TL");
			}
		}
	}
}
