package TP01_2;

/**
 * Classe pour l'implementation d'une file a partir d'un tableau
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */
public class ArrayQueue<AnyType> implements Queue<AnyType> {
	private int size = 0; // Nombre d'elements dans la file.
	private int startIndex = 0; // Index du premier element de la file
	private AnyType[] table;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		table = (AnyType[]) new Comparable[1013];// set default capacity to 1013
	}

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
			return table[startIndex];

		return null;
	}

	// Retire l'element en tete de file
	// complexit� asymptotique: O(1)
	public void pop() throws EmptyQueueException {
		if (empty())
			throw new EmptyQueueException();

		for (int i = 0; i < size; ++i) {
			if ((i + 1) < table.length)
				table[i] = table[i + 1];
		}
		size--;
	}

	// Ajoute un element a la fin de la file
	// Double la taille de la file si necessaire (utiliser la fonction resize
	// definie plus bas)
	// complexit� asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est
	// necessaire )
	public void push(AnyType item) {
		if (table.length == size)
			resize(2); // Double la capacite de la file
		table[size] = item;
		size++;

	}

	// Redimensionne la file. La capacite est multipliee par un facteur de
	// resizeFactor.
	// Replace les elements de la file au debut du tableau
	// complexit� asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor) {
		AnyType[] old = table;
		table = (AnyType[]) new Comparable[table.length * resizeFactor];
		for (int i = 0; i < size; ++i)
			table[i] = old[i];
	}
}
