package treePackage;

/**
 * This is the interface of media objects.
 * @author Berke Can Kandemir and Cem Ozan
 * @param <T> The given type argument.
 */
public interface IMedia extends Comparable<IMedia>{
	public String mediaName();
	public String mediaType();
	public int mediaPrice();
	public int mediaYear();
}
