package TP01_2;

/**
 * Classe pour l'implementation d'une file a partir d'une liste chainee
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */
public class LinkedListQueue<AnyType> implements Queue<AnyType> {
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> {
		private AnyType data;
		private Node next;

		public Node(AnyType data, Node next) {
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node<AnyType> getNext() {
			return next;
		}

		public AnyType getData() {
			return data;
		}
	}

	private int size = 0; // Nombre d'elements dans la file.
	private Node<AnyType> last; // Dernier element de la liste
	private Node<AnyType> first;

	// Indique si la file est vide
	public boolean empty() {
		return size == 0;
	}

	// Retourne la taille de la file
	public int size() {
		return size;
	}

	// Retourne l'element en tete de file
	// Retourne null si la file est vide
	// complexit� asymptotique: O(1)
	public AnyType peek() {
		if (!empty())
			return first.getData();
		return null;
	}

	// Retire l'element en tete de file
	// complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException {
		if (empty())
			throw new EmptyQueueException();

		first = first.getNext();
		size--;
	}

	// Ajoute un element a la fin de la file
	// complexit� asymptotique: O(1)
	public void push(AnyType item) {
		if (empty()) {
			first = new Node<AnyType>(item, null);
			last = first;
		} else {
			Node<AnyType> newNode = new Node<AnyType>(item, null);
			last.setNext(newNode);
			last = newNode;
		}
		size++;
	}
}
