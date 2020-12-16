package treePackage;

/**
 * This is the main body of the movie class.
 * @author Berke Can Kandemir and Cem Ozan.
 */
public class Movie implements IMedia {
	private String directorName;
	private String actressName;
	private String actorName;
	private String name;
	private int price;
	private int year;
	/**
	 * The constructor of the class.
	 * @param name The given movie name.
	 * @param price The given price of the movie.
	 * @param year The given year of publishing of the movie.
	 * @param directorName The given name of the director.
	 * @param actressName The given name of the actress.
	 * @param actorName The given name of the actor.
	 */
	public Movie(String name, int price, int year, String directorName, String actressName, String actorName) {
		this.name = name;
		this.price = price;
		this.year = year;
		this.directorName = directorName;
		this.actressName = actressName;
		this.actorName = actorName;
	}
	/**
	 * This method is the overwritten version of the toString method.
	 * @return The name of the movie.
	 */
	@Override
	public String toString() {
		return name;
	}
	/**
	 * This method gets the name of the movie.
	 * @return The name of the movie.
	 */
	public String mediaName() {
		return name;
	}
	/**
	 * This method gets the type of the item.
	 * @return The type movie.
	 */
	public String mediaType() {
		return "Movie";
	}
	/**
	 * This method gets the price of the movie.
	 * @return The price of the movie.
	 */
	public int mediaPrice() {
		return price;
	}
	/**
	 * This method gets the year of publishing year of the movie.
	 * @return The year of publishing of the movie.
	 */
	public int mediaYear() {
		return year;
	}
	/**
	 * This method gets the director of the movie.
	 * @return The director of the movie.
	 */
	public String getDirectorName() {
		return directorName;
	}
	/**
	 * This method gets the actress of the movie.
	 * @return The actress of the movie.
	 */
	public String getActressName() {
		return actressName;
	}
	/**
	 * This method gets the actor of the movie.
	 * @return The actor of the movie.
	 */
	public String getActorName() {
		return actorName;
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
