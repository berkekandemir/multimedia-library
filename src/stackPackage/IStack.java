package stackPackage;
/**
 * This is the stack interface that has been implemented in the LinkedStack class.
 * We have used this interface and the class for iterator in tree package.
 * @author Berke Can Kandemir and Cem Ozan.
 * @param <T> The given argument type.
 */
public interface IStack<T> {
	public void push(T newEntry);
	public T pop();
	public T peek();
	public boolean isEmpty();
	public void clear();
}
