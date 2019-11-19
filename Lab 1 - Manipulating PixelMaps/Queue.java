package TP01_2;

/**
 * Interface des operations sur une Queue
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */
public interface Queue<AnyType> 
{
	public boolean empty();
	public int size();
	public AnyType peek();
	public void pop() throws EmptyQueueException;
	public void push(AnyType item);
}
