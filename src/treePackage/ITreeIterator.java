package treePackage;
import java.util.Iterator;

/**
 * This is the interface of the tree iterators..
 * @author Berke Can Kandemir and Cem Ozan
 * @param <T> The given type argument.
 */
public interface ITreeIterator<T> {
	public Iterator<T> getPreorderIterator();
	public Iterator<T> getPostorderIterator();
	public Iterator<T> getInorderIterator();
	public Iterator<T> getLevelOrderIterator();
}
