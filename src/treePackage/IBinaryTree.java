package treePackage;

/**
 * This is the interface of the binary tree.
 * @author Berke Can Kandemir and Cem Ozan
 * @param <T> The given type argument.
 */
public interface IBinaryTree<T> extends ITree<T>, ITreeIterator<T> {
	public void setTree(T rootData);
	public void setTree(T rootData, IBinaryTree<T> leftTree, IBinaryTree<T> rightTree);
}
