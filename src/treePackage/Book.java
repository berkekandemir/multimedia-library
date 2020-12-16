package treePackage;

/**
 * This is the main body of the book class.
 * @author Berke Can Kandemir and Cem Ozan.
 */
public class Book implements IMedia {
	private String authorName;
	private String name;
	private int year;
	private int price;
	/**
	 * The constructor of the class.
	 * @param name The given book name.
	 * @param price The given price of the book.
	 * @param year The given year of publishing of the book.
	 * @param authorName The given author of the book.
	 */
	public Book(String name, int price, int year, String authorName) {
		this.name= name;
		this.price = price;
		this.year = year;
		this.authorName = authorName;
	}
	/**
	 * This method is the overwritten version of the toString method.
	 * @return The name of the book.
	 */
	@Override
	public String toString() {
		return name;
	}
	/**
	 * This method gets the name of the book.
	 * @return The name of the book.
	 */
	public String mediaName() {
		return name;
	}
	/**
	 * This method gets the type of the item.
	 * @return The type book.
	 */
	public String mediaType() {
		return "Book";
	}
	/**
	 * This method gets the price of the book.
	 * @return The price of the book.
	 */
	public int mediaPrice() {
		return price;
	}
	/**
	 * This method gets the year of publishing year of the book.
	 * @return The year of publishing of the book.
	 */
	public int mediaYear() {
		return year;
	}
	/**
	 * This method gets the author of the book.
	 * @return The author of the book.
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * This method compares the items.
	 * @param other The given item to be compared.
	 * @return -1, 1 or 0 according to the result.
	 */
	@Override
	public int compareTo(IMedia other) {
		if (price < other.mediaPrice()) {
			return -1;
		} else if (price > other.mediaPrice()) {
			return 1;
		} else {
			return 0;
		}
	}
}
