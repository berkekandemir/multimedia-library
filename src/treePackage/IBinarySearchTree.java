package treePackage;
import java.util.Iterator;

/**
 * This is the interface of the binary search tree.
 * @author Berke Can Kandemir and Cem Ozan
 * @param <T> The given type argument.
 */
public interface IBinarySearchTree<T extends Comparable<? super T>> extends ITree<T> {
	public boolean contains(T entry);
	public T getEntry(T entry);
	public T add(T newEntry);
	public T remove(T entry);
	public Iterator<T> getInorderIterator();
}
