package treePackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import stackPackage.*;

/**
 * This is the main body of binary tree.
 * @author Berke Can Kandemir and Cem Ozan.
 * @param <T> The given type argument.
 */
public class BinaryTree<T> implements IBinaryTree<T> {
	private BinaryNode<T> root;
	/**
	 * The first constructor of the binary tree.
	 */
	public BinaryTree() {
		root = null;
	}
	/**
	 * The overwritten constructor of the binary tree.
	 * @param rootData The given data for the root node.
	 */
	public BinaryTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}
	/**
	 * The overwritten constructor of the binary tree.
	 * @param rootData The given data for the root node.
	 * @param leftTree The given left subtree of the root node.
	 * @param rightTree The given right subtree of the root node.
	 */
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		privateSetTree(rootData, leftTree, rightTree);
	}
	/**
	 * This method assigns the data to the root node.
	 * @param rootData The given data.
	 */
	public void setTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}
	/**
	 * This method calls creator of the full tree with root and subtrees.
	 * @param rootData The given data for the root.
	 * @param leftTree The given left subtree of the root node.
	 * @param rightTree The given right subtree of the root node.
	 */
	public void setTree(T rootData, IBinaryTree<T> leftTree, IBinaryTree<T> rightTree) {
		privateSetTree(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
	}
	/**
	 * This method creates the full tree with root and subtrees.
	 * @param rootData The given data for the root.
	 * @param leftTree The given left subtree of the root node.
	 * @param rightTree The given right subtree of the root node.
	 */
	private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		root = new BinaryNode<>(rootData);
		if ((leftTree != null) && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
		}
		if ((rightTree != null) && !rightTree.isEmpty()) {
			if (rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			} else {
				root.setRightChild(rightTree.root.copy());
			}
		}
		if ((leftTree != null) && (leftTree != this)) {
			leftTree.clear();
		}
		if ((rightTree != null) && (rightTree != this)) {
			rightTree.clear();
		}
	}
	/**
	 * This method gets the root node's data.
	 * @return The data of the root node.
	 */
	public T getRootData() {
		if (isEmpty()) {
			throw new IllegalStateException();
		} else {
			return root.getData();
		}
	}
	/**
	 * This method checks if the tree is empty or not.
	 * @return True or false according to the result.
	 */
	public boolean isEmpty() {
		return root == null;
	}
	/**
	 * This method clears the whole tree.
	 */
	public void clear() {
		root = null;
	}
	/**
	 * This method sets the root's data.
	 * @param rootData The given data for the root node.
	 */
	protected void setRootData(T rootData) {
		root.setData(rootData);
	}
	/**
	 * This method reassigns the root node.
	 * @param rootNode The given node to be the root of the tree.
	 */
	protected void setRootNode(BinaryNode<T> rootNode) {
		root = rootNode;
	}
	/**
	 * This method gets the root node of the tree.
	 * @return The root node of the tree.
	 */
	protected BinaryNode<T> getRootNode() {
		return root;
	}
	/**
	 * This method finds the height of the tree.
	 * @return The height of the tree.
	 */
	public int getHeight() {
		return root.getHeight();
	}
	/**
	 * This method finds the number of nodes of the tree.
	 * @return The number of nodes of the tree.
	 */
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}
	/**
	 * This method calls the private inorderTraverse method.
	 */
	public void inorderTraverse() {
		inorderTraverse(root);
	}
	/**
	 * This method traverse the tree recursively with inorder method.
	 * @param node The given node to start traversing.
	 */
	private void inorderTraverse(BinaryNode<T> node) {
		if (node != null) {
			inorderTraverse(node.getLeftChild());
			System.out.println(node.getData());
			inorderTraverse(node.getRightChild());
		}
	}
	/**
	 * This method creates the iterator object.
	 * @return The iterator object.
	 */
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}
	/**
	 * This method is not implemented because we did not need it.
	 * We solve the problems with inorder iterator.
	 */
	public Iterator<T> getPreorderIterator() {
		return null;
	}
	/**
	 * This method is not implemented because we did not need it.
	 * We solve the problems with inorder iterator.
	 */
	public Iterator<T> getPostorderIterator() {
		return null;
	}
	/**
	 * This method is not implemented because we did not need it.
	 * We solve the problems with inorder iterator.
	 */
	public Iterator<T> getLevelOrderIterator() {
		return null;
	}
	/**
	 * This class is the main body of nodes.
	 * @param <T> The given type argument.
	 */
	@SuppressWarnings("hiding")
	class BinaryNode<T> {
		private T data;
		private BinaryNode<T> leftChild;
		private BinaryNode<T> rightChild;
		/**
		 * The first constructor of the class.
		 */
		public BinaryNode() {
			this(null);
		}
		/**
		 * The overloaded constructor of the class.
		 * @param dataPortion The given data to be assigned the node. 
		 */
		public BinaryNode(T dataPortion) {
			this(dataPortion, null, null);
		}
		/**
		 * The overloaded constructor of the class.
		 * @param dataPortion The given data to be assigned the node. 
		 * @param newLeftChild The given left child to be assigned the node. 
		 * @param newRightChild The given right child to be assigned the node. 
		 */
		public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild) {
			data = dataPortion;
			leftChild = newLeftChild;
			rightChild = newRightChild;
		}
		/**
		 * The getter for the data attribute.
		 * @return The data of the node.
		 */
		public T getData() {
			return data;
		}
		/**
		 * The setter for the data attribute.
		 * @param newData The data to be set to the node.
		 */
		public void setData(T newData) {
			data = newData;
		}
		/**
		 * This method gets the left child of the node.
		 * @return The left child of the node.
		 */
		public BinaryNode<T> getLeftChild() {
			return leftChild;
		}
		/**
		 * This method sets the left child of the node.
		 * @param newLeftChild The given left child to be set to the node.
		 */
		public void setLeftChild(BinaryNode<T> newLeftChild) {
			leftChild = newLeftChild;
		}
		/**
		 * This method checks if the node has a left child.
		 * @return True or false according to the result.
		 */
		public boolean hasLeftChild() {
			return leftChild != null;
		}
		/**
		 * This method gets the right child of the node.
		 * @return The right child of the node.
		 */
		public BinaryNode<T> getRightChild() {
			return rightChild;
		}
		/**
		 * This method sets the right child of the node.
		 * @param newLeftChild The given right child to be set to the node.
		 */
		public void setRightChild(BinaryNode<T> newRightChild) {
			rightChild = newRightChild;
		}
		/**
		 * This method checks if the node has a right child.
		 * @return True or false according to the result.
		 */
		public boolean hasRightChild() {
			return rightChild != null;
		}
		/**
		 * The method checks if the node is a leaf node.
		 * @return True or false according to the result.
		 */
		public boolean isLeaf() {
			return (leftChild == null) && (rightChild == null);
		}
		/**
		 * The method gets the height of the node.
		 * @return The height of the node.
		 */
		public int getHeight() {
			return getHeight(this);
		}
		/**
		 * The method gets the height of the node.
		 * @param node The given node to be checked.
		 * @return The height of the node.
		 */
		private int getHeight(BinaryNode<T> node) {
			int height = 0;
			if (node != null) {
				height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
			}
			return height;
		}
		/**
		 * The method gets the number of nodes under the node.
		 * @return The number of nodes under the node.
		 */
		public int getNumberOfNodes() {
			int leftNumber = 0;
			int rightNumber = 0;
			if (leftChild != null) {
				leftNumber = leftChild.getNumberOfNodes();
			}
			if (rightChild != null) {
				rightNumber = rightChild.getNumberOfNodes();
			}
			return 1 + leftNumber + rightNumber;
		}
		/**
		 * This method copies the node.
		 * @return The copied node.
		 */
		public BinaryNode<T> copy() {
			BinaryNode<T> newRoot = new BinaryNode<>(data);
			if (leftChild != null) {
				newRoot.setLeftChild(leftChild.copy());
			}
			if (rightChild != null) {
				newRoot.setRightChild(rightChild.copy());
			}
			return newRoot;
		}
	}
	/**
	 * This is the main body of the inorderIterator class.
	 */
	class InorderIterator implements Iterator<T>{
		
		private IStack<BinaryNode<T>> nodeStack;
		private BinaryNode<T> currentNode;
		/**
		 * The constructor of the class.
		 */
		public InorderIterator() {
			nodeStack = new LinkedStack<>();
			currentNode = root;
		}
		/**
		 * This method checks if the iterator object has any next element.
		 * @return True or false according to the result.
		 */
		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		}
		/**
		 * This method gets the next element of the iterator object.
		 * @return The next element.
		 */
		public T next() {
			BinaryNode<T> nextNode = null;
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			}
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				assert nextNode != null;
				currentNode = nextNode.getRightChild();
			} else {
				throw new NoSuchElementException();
			}
			return nextNode.getData();
		}
		/**
		 * This method is not supported.
		 */
		public void remove () {
			throw new UnsupportedOperationException();
		}
	}
}
