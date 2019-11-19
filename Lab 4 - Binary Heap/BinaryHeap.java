import java.util.*;


public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType> {
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Nombre d'elements
    private AnyType[] array;    // Tableau contenant les donnees (premier element a l'indice 1)
    private boolean min;
    private int modifications;    // Nombre de modifications apportees a ce monceau

    @SuppressWarnings("unchecked")
    public BinaryHeap(boolean min) {
        this.min = min;
        currentSize = 0;
        array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(AnyType[] items, boolean min) {
        this.min = min;

        array = (AnyType[]) new Comparable[items.length + 1];
        for (int i = 1; i <= items.length; ++i) {
            array[i] = items[i - 1];
            currentSize++;
        }

        if (min) {
            buildMinHeap();
        } else {
            buildMaxHeap();
        }
    }

    public boolean offer(AnyType x) {
        if (x == null)
            throw new NullPointerException("Cannot insert null in a BinaryHeap");

        if (currentSize + 1 == array.length)
            doubleArray();

        int i = ++currentSize;

        if (min) {
            // Make sure heap order is respected in min heap
            for (; i > 1 && x.compareTo(array[i / 2]) < 0; i /= 2)
                swapReferences(i / 2, i);
        } else {
            // Make sure heap order is respected in max heap
            for (; i > 1 && x.compareTo(array[i / 2]) > 0; i /= 2)
                swapReferences(i / 2, i);
        }
        // Set x at next index
        array[i] = x;

        // Update modifications
        modifications++;

        return true;
    }

    public AnyType peek() {
        if (!isEmpty())
            return array[1];

        return null;
    }

    public AnyType poll() {
        // Set default index to 1
        int defaultIndex = 1;

        AnyType temp = array[defaultIndex];
        array[defaultIndex] = array[currentSize--];
        if (min)
            percolateDownMinHeap(defaultIndex, currentSize);
        else
            percolateDownMaxHeap(defaultIndex, currentSize);
        // Update modifications
        modifications++;
        return temp;
    }

    public Iterator<AnyType> iterator() {
        return new HeapIterator();
    }

    private void buildMinHeap() {
        // Start at n/2 element because leaf have no children
        for (int i = currentSize / 2; i > 0; i--)
            percolateDownMinHeap(i, currentSize);
    }

    private void buildMaxHeap() {
        // Start at n/2 element because leaf have no children
        for (int i = currentSize / 2; i > 0; i--)
            percolateDownMaxHeap(i, currentSize);
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    public void clear() {
        currentSize = 0;
        modifications = 0;
        array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    private static int leftChild(int i, boolean heapIndexing) {
        return (heapIndexing ? 2 * i : 2 * i + 1);
    }

    private void swapReferences(int index1, int index2) {
        swapReferences(array, index1, index2);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void swapReferences(AnyType[] array, int index1, int index2) {
        AnyType tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    @SuppressWarnings("unchecked")
    private void doubleArray() {
        AnyType[] newArray;

        newArray = (AnyType[]) new Comparable[array.length * 2];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        array = newArray;
    }


    /**
     * @param hole Position a percoler
     * @param size Indice max du tableau
     */
    private void percolateDownMinHeap(int hole, int size) {
        percolateDownMinHeap(array, hole, size, true);
    }

    /**
     * @param array        Tableau d'element
     * @param hole         Position a percoler
     * @param size         Indice max du tableau
     * @param heapIndexing True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percolateDownMinHeap(AnyType[] array, int hole, int size, boolean heapIndexing) {
        // Set child index
        int index = leftChild(hole, heapIndexing);
        // if array start at 0 index need to be +1
        if (array[0] != null)
            index += 1;
        // Check if out of bound
        if (index > size)
            return;

        // Check if left child is lower
        if (array[hole].compareTo(array[index]) > 0)
            swapReferences(array, hole, index);
        if (heapIndexing)
            // Check if swapped items need to be percolated with it's brother
            percolateDownMinHeap(array, hole, size, false);
        // Set hole to swapped index
        hole = index;
        // Check if current  items need to be percolated with it's new child
        percolateDownMinHeap(array, hole, size, true);

    }

    /**
     * @param hole Position a percoler
     * @param size Indice max du tableau
     */
    private void percolateDownMaxHeap(int hole, int size) {
        percolateDownMaxHeap(array, hole, size, true);
    }

    /**
     * @param array        Tableau d'element
     * @param hole         Position a percoler
     * @param size         Indice max du tableau
     * @param heapIndexing True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percolateDownMaxHeap(AnyType[] array, int hole, int size, boolean heapIndexing) {
        // Set child index
        int index = leftChild(hole, heapIndexing);
        // if array start at 0 index need to be +1
        if (array[0] != null)
            index += 1;

        // Check if out of bound
        if (index > size)
            return;

        // Check if left child is greater
        if (array[hole].compareTo(array[index]) < 0)
            swapReferences(array, hole, index);
        if (heapIndexing)
            // Check if swapped items need to be percolated with it's brother
            percolateDownMaxHeap(array, hole, size, false);
        // Set hole to swapped index
        hole = index;
        // Check if current  items need to be percolated with it's new child
        percolateDownMaxHeap(array, hole, size, true);

    }

    public static <AnyType extends Comparable<? super AnyType>>
    void heapSort(AnyType[] a) {
        for (int size = a.length; size > 0; size--) {
            for (int i = (size / 2) - 1; i >= 0; i--)
                percolateDownMaxHeap(a, i, size - 1, true);
            swapReferences(a, 0, size - 1);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void heapSortReverse(AnyType[] a) {
        for (int size = a.length; size > 0; size--) {
            for (int i = (size / 2) - 1; i >= 0; i--)
                percolateDownMinHeap(a, i, size - 1, true);
            swapReferences(a, 0, size - 1);
        }

    }

    public String nonRecursivePrintFancyTree() {
        String outputString = "";
        boolean leftBranch = false;
        int index = 1;
        String pref = "";

        while (0 < index) {
            outputString = outputString + (pref + "|__" + array[index] + "\n");

            // Dealing with left branch
            if (currentSize >= index * 2) {
                index = index * 2;
                if (leftBranch)
                    pref = pref + "|  ";
                else
                    pref = pref + "   ";
                leftBranch = true;
            }

            // Dealing with consecutive value from array
            else if (leftBranch & (index + 1) <= currentSize) {
                index++;
                leftBranch = false;
            }

            // Not dealing with left branch or consecutive value
            else {
                // Find nearest left branch
                do {
                    index = index / 2;
                    if (0 < index)
                        pref = pref.substring(0, pref.length() - 3);
                } while (0 < index & index % 2 != 0);

                // Index now on consecutive value
                if (0 < index)
                    index++;
                leftBranch = false;
            }
        }

        return outputString;
    }

    public String printFancyTree() {
        return printFancyTree(1, "");
    }

    private String printFancyTree(int index, String prefix) {
        String outputString = "";

        outputString = prefix + "|__";

        if (index <= currentSize) {
            boolean isLeaf = index > currentSize / 2;

            outputString += array[index] + "\n";

            String _prefix = prefix;

            if (index % 2 == 0)
                _prefix += "|  "; // un | et trois espace
            else
                _prefix += "   "; // quatre espaces

            if (!isLeaf) {
                outputString += printFancyTree(2 * index, _prefix);
                outputString += printFancyTree(2 * index + 1, _prefix);
            }
        } else
            outputString += "null\n";

        return outputString;
    }

    private class HeapIterator implements Iterator {

        private int iteratorIndex;
        private int expexctedModifications;

        public HeapIterator() {
            iteratorIndex = 1;
            expexctedModifications = modifications;
        }

        public boolean hasNext() {
            return (iteratorIndex < currentSize);
        }

        public Object next() throws NoSuchElementException,
                ConcurrentModificationException,
                UnsupportedOperationException {
            if (expexctedModifications != modifications)
                throw new ConcurrentModificationException();
            if (this.hasNext())
                return array[++iteratorIndex];
            else
                throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
