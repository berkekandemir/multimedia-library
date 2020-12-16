package stackPackage;

import java.util.EmptyStackException;
import java.lang.*;
/**
 * This method implements IStack interface, creates a stack to be used in iterator of tree package.
 * @author Berke Can Kandemir and Cem Ozan.
 * @param <T> The argument type.
 */
@SuppressWarnings("unused")
public class LinkedStack<T> implements IStack<T>{
	private Node topNode;
	public LinkedStack() {
		topNode = null;
	}
	
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	}
	
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return topNode.getData();
		}
	}
	
	public T pop() {
		T top = peek();
		assert topNode != null;
		topNode = topNode.getNextNode();
		return top;
	}
	
	public boolean isEmpty() {
		return topNode == null;
	}
	
	public void clear() {
		topNode = null;
	}
	
	private class Node {
		private T data;
		private Node next;
		
		public Node(T newData, Node nextNode) {
			data = newData;
			next = nextNode;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNextNode() {
			return next;
		}

		public void setNextNode(Node next) {
			this.next = next;
		}
	}
}