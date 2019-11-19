package tp2;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> {
	static int p = 46337;

	int a, b, m, n;
	AnyType[] items;
	Random generator;

	QuadraticSpacePerfectHashing() {
		clear();
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array) {
		allocateMemory(array);
	}

	public void setArray(ArrayList<AnyType> array) {
		allocateMemory(array);
	}

	public int size() {
		return n;
	}

	public void clear() {
		generator = new Random(System.nanoTime());
		a = b = m = n = 0;
		items = null;
	}

	private int findPos(AnyType x) {
		return ((a * x.hashCode() + b) % p) % m;
	}

	public boolean contains(AnyType x) {
		if (n == 0)
			return false;

		int index = findPos(x);

		return ((items[index] != null) && (items[index].equals(x)));
	}

	@SuppressWarnings("unchecked")
	private void allocateMemory(ArrayList<AnyType> array) {
		clear();

		if (array == null || array.size() == 0)
			return;

		n = array.size();
		m = n * n;

		if (n == 1) {
			items = (AnyType[]) new Object[m];
			items[0] = array.get(0);
			return;
		}

		while (unsuccessfulMemoryAllocation(array))
			;
	}

	@SuppressWarnings("unchecked")
	private boolean unsuccessfulMemoryAllocation(ArrayList<AnyType> array) {
		a = generator.nextInt(p - 1) + 1;
		b = generator.nextInt(p);
		
		n = array.size();
		m = n * n;
		items = (AnyType[]) new Object[m];
		
		for (int i = 0; i < n; i++) {
			if (items[findPos(array.get(i))] == null)
				items[findPos(array.get(i))] = array.get(i);
			else
				// Il y a une collision
				return true;
		}

		// Tous les elements ont ete inseres avec succes
		return false;
	}

	public int memorySize() {
		return m;
	}

	public String toString() {
		if (n == 0)
			return "";

		StringBuilder sb = new StringBuilder();

		for (AnyType item : items)
			if (item != null)
				sb.append(item + ", ");

		return sb.toString();
	}
}
