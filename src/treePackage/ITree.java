package treePackage;

/**
 * This is the interface of general trees.
 * @author Berke Can Kandemir and Cem Ozan.
 * @param <T> Tje given type argument.
 */
public interface ITree<T> {
	public T getRootData();
	public int getHeight();
	public int getNumberOfNodes();
	public boolean isEmpty();
	public void clear();
}
