package tp2;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType> {
	static int p = 46337;

	int a, b, n, memorySize;
	QuadraticSpacePerfectHashing<AnyType>[] data;
	Random generator;

	LinearSpacePerfectHashing() {
		clear();
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array) {
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
		a = b = n = memorySize = 0;
		data = null;
	}

	private int findPos(AnyType x) {
		return ((a * x.hashCode() + b) % p) % n;
	}

	public boolean contains(AnyType x) {
		if (n == 0)
			return false;

		int index = findPos(x);
		return ((data[index] != null) && (data[index].contains(x)));
	}

	@SuppressWarnings("unchecked")
	private void allocateMemory(ArrayList<AnyType> array) {
		clear();

		if (array == null || array.size() == 0)
			return;

		n = array.size();
		data = new QuadraticSpacePerfectHashing[n];

		if (n == 1) {
			data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
			memorySize = 1;
			return;
		}
		
		a = generator.nextInt(p - 1) + 1;
		b = generator.nextInt(p);
		int pos;

		// Set up temporary array
		ArrayList<AnyType>[] arrayBuffer = new ArrayList[n];
	    
	      for (int i = 0; i < n; i++) {
	    	 // Look for each value's position in the temporary array
	    	 pos = findPos(array.get(i));

	    	 // If nothing found, instanciate new empty array
   	    	 //After several run, i notice that the array size almost never goes above n/3.This is why n/3 capacity is more appropriate. 
	         if (arrayBuffer[pos] == null)
	        	 arrayBuffer[pos] = new ArrayList<AnyType>(n/3);

	         // Add the value to the temporary array
	         arrayBuffer[pos].add(array.get(i));
	      }

	      // Copy temporary array into data and update memorySize
	      for (int i = 0; i < n; i++) {
	    	  //Check if there is an instaciate element in arrayBuffer[i]
	    	  //if yes that element is intanciated in the data array
	    	  if(arrayBuffer != null) {
	    		  data[i] = new QuadraticSpacePerfectHashing<AnyType>(arrayBuffer[i]);
	    		  memorySize += data[i].memorySize();
	         }
	      }

	}

	public int memorySize() {
		return memorySize;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < size(); i++) {
			if (data[i] != null)
				sb.append(i + "->" + data[i] + "\n");
		}

		return sb.toString();
	}
}
