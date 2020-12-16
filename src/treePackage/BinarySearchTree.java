package treePackage;
import java.util.Iterator;

/**
 * This class is the main body of the binary search tree.
 * @author Berke Can Kandemir and Cem Ozan.
 * @param <T> The given type of argument.
 */
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements IBinarySearchTree<T> {
	/**
	 * Constructor calls the super class' constructor to be used.
	 */
	public BinarySearchTree() {
		super();
	}
	/**
	 * Constructor calls the super class' constructor to be used.
	 * @param rootEntry The given root data.
	 */
	public BinarySearchTree(T rootEntry) {
		super();
		setRootNode(new BinaryNode<T>(rootEntry));
	}
	/**
	 * This operation is nut supported for binary search tree.
	 */
	public void setTree(T rootData) {
		throw new UnsupportedOperationException();
	}
	/**
	 * This operation is nut supported for binary search tree.
	 */
	public void setTree(T rootData, IBinaryTree<T> leftTree, IBinaryTree<T> rightTree) {
		throw new UnsupportedOperationException();
	}
	/**
	 * This method gets the specified entry from the tree.
	 * @param entry The given entry to be searched.
	 * @return findEntry method.
	 */
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	}
	/**
	 * This method finds the entry which will be searched in the tree.
	 * @param rootNode The given node to begin the search.
	 * @param entry The given entry to be searched.
	 * @return The entry that will be searched or null if it's not in tree.
	 */
	public T findEntry(BinaryNode<T> rootNode, T entry) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			if (entry.equals(rootEntry)) {
				result = rootEntry;
			} else if (entry.compareTo(rootEntry) < 0) {
				result = findEntry(rootNode.getLeftChild(), entry);
			} else {
				result = findEntry(rootNode.getRightChild(), entry);
			}
		}
		return result;
	}
	/**
	 * This method checks if the target entry is in the tree or not.
	 * @param entry The given entry as target.
	 * @return true or false according to the result.
	 */
	public boolean contains(T entry) {
		return getEntry(entry) != null;
	}
	/**
	 * This method adds the given entry to the tree according to its comparable value.
	 * @param rootNode The given root to begin searching for suitable place for the entry.
	 * @param newEntry The given entry to be added to the tree.
	 * @return Recursively returns the nodes.
	 */
	private T addEntry(BinaryNode<T> rootNode, T newEntry) {
		assert rootNode != null;
		T result = null;
		int comparison = newEntry.compareTo(rootNode.getData());
		if (comparison == 0) {
			result = rootNode.getData();
			rootNode.setData(newEntry);
		} else if (comparison < 0) {
			if (rootNode.hasLeftChild()) {
				result = addEntry(rootNode.getLeftChild(), newEntry);
			} else {
				rootNode.setLeftChild(new BinaryNode<>(newEntry));
			}
		} else {
			assert comparison > 0;
			if (rootNode.hasRightChild()) {
				result = addEntry(rootNode.getRightChild(), newEntry);
			} else {
				rootNode.setRightChild(new BinaryNode<>(newEntry));
			}
		}
		return result;
	}
	/**
	 * This method is the client's add method.
	 * @param newEntry The given entry to be added to the tree.
	 * @return private addEntry method.
	 */
	public T add(T newEntry) {
		T result = null;
		if (isEmpty()) {
			setRootNode(new BinaryNode<>(newEntry));
		} else {
			result = addEntry(getRootNode(), newEntry);
		}
		return result;
	}
	/**
	 * This method is the client's remove method from tree.
	 * @param entry The given entry to be removed from the tree.
	 * @return private removeEntry method.
	 */
	public T remove(T entry) {
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		setRootNode(newRoot);
		return oldEntry.get();
	}
	/**
	 * This is the main remove method of the tree.
	 * @param rootNode The given root to start searching for the target entry.
	 * @param entry	The target entry to be removed.
	 * @param oldEntry The removed entry object.
	 * @return The rootNode of the tree.
	 */
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, ReturnObject oldEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);
			if (comparison == 0) {
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			} else if (comparison < 0) {
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);
			} else {
				BinaryNode<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			}
		}
		return rootNode;
	}
	/**
	 * This is the case1 remove method.
	 * @param rootNode The given rootNode to be removed from the tree.
	 * @return The removed node.
	 */
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
			rootNode.setData(largestNode.getData());
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		} else if (rootNode.hasRightChild()) {
			rootNode = rootNode.getRightChild();
		} else {
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}
	/**
	 * This is the helper method of the removeLargest method.
	 * @param rootNode The given rootNode to begin traversing the tree.
	 * @return The largest node.
	 */
	private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			rootNode = findLargest(rootNode.getRightChild());
		}
		return rootNode;
	}
	/**
	 * This method removes the largest node.
	 * @param rootNode The root node given to the method to start traversing.
	 * @return The removed node.
	 */
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNode<T> rightChild = rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);
		} else {
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}
	/**
	 * This method creates the iterator objects to iterate over the tree.
	 * @return The iterator object.
	 */
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}
	/**
	 * This class is to specify the return object of the remove method.
	 */
	private class ReturnObject {
		T data;

		public ReturnObject(T data) {
			this.data = data;
		}

		public T get() {
			return data;
		}

		public void set(T data) {
			this.data = data;
		}
	}
}
