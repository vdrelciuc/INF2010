import java.util.*;


public class Main {
    /**
     * Fonction principale
     */
    public static void main(String[] args) {
        // creer un monceau avec 22 elements et un tableau equivalent
        int numItems = 22;
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(true);

        Integer[] items = new Integer[numItems];

        int i;
        int j;

        // en inserant les elements un a un
        for (i = 11, j = 0; j != numItems; i = (i + 37), j++) {
            heap.offer(i);
            items[j] = i;

            i %= numItems;
        }

        // en construisant le monceau depuis le depart
        System.out.println("Monceau min contruit element par element:");
        System.out.println(heap.printFancyTree());

        heap = new BinaryHeap<Integer>(false);
        // en inserant les elements un a un
        for (Integer item : items)
            heap.offer(item);

        // en construisant le monceau depuis le depart
        System.out.println("Monceau max contruit element par element:");
        System.out.println(heap.printFancyTree());

        heap = new BinaryHeap<Integer>(items, false);
        System.out.println("Monceau max contruit a partir d'un tableau:");
        System.out.println(heap.printFancyTree());

        heap = new BinaryHeap<Integer>(items, true);
        System.out.println("Monceau min contruit a partir d'un tableau:");
        System.out.println(heap.printFancyTree());

        System.out.println();
        System.out.println("Affichage recursif:");
        System.out.println(heap.printFancyTree());

        System.out.println("Affichage non recursif:");
        System.out.println(heap.nonRecursivePrintFancyTree());

        System.out.println();
        System.out.println("Tableau d'origine:");
        System.out.println(printArray(items));

        BinaryHeap.heapSort(items);
        System.out.println("Tableau ordonne:");
        System.out.println(printArray(items));

        BinaryHeap.heapSortReverse(items);
        System.out.println("Tableau inversement ordonne:");
        System.out.println(printArray(items));


        /*
         * Ajouter appels pour repondre a la question
         **/

        // Initializing min test heap
        System.out.println("\n" + "Initializing min testing heap... ");
        BinaryHeap<Integer> test = new BinaryHeap<Integer>(true);
        Integer[] tab = new Integer[numItems];

        // en inserant les elements un a un
        for (i = 11, j = 0; j != numItems; i = (i + 37), j++) {
            test.offer(i);
            tab[j] = i;
            i %= numItems;
        }
        // Testing peek()
        int expectedValue = tab[0];
        for (int k = 0; k < tab.length; k++)
            if (tab[k] < expectedValue)
                expectedValue = tab[k];
        int value = test.peek();
        System.out.println("Testing peek() (expected value: " + expectedValue + ") - Success? " + (expectedValue == value));

        // Testing poll()
        expectedValue = 37;
        test.poll();
        value = test.peek();
        System.out.println("Testing poll() + peek() (expected value: " + expectedValue + ") - Success? " + (expectedValue == value));

        // Testing offer()
        expectedValue = -5;
        test.offer(expectedValue);
        value = test.peek();
        System.out.println("Testing offer() + peek() (expected value: " + expectedValue + ") - Success? " + (expectedValue == value));

        // Testing hasNext()
        Iterator<Integer> iterator = test.iterator();
        boolean answer = iterator.hasNext();
        System.out.println("Testing hasNext() at current position (expected value: " + true + ") - Success? " + (true == answer));

        // Testing next()
        iterator = test.iterator();
        expectedValue = 37;
        value = iterator.next();
        System.out.println("Testing next() (expected value: " + expectedValue + ") - Success? "  + (expectedValue == value));

        // Testing iterator out of bounds
        test.offer(1000);
        System.out.print("Testing hasNext() on highest value of heap (expected value: throw exception) - Success? ");
        try {
            iterator.next();
            System.out.println(false);
        } catch (ConcurrentModificationException e) {
            System.out.println(true);
        }

        // Initializing max test heap
        System.out.println("\n" + "Initializing max testing heap... ");
        test = new BinaryHeap<Integer>(items, false);

        // Testing peek()
        expectedValue = tab[0];
        for (int k = 0; k < tab.length; k++){
            if (tab[k] > expectedValue){
                expectedValue = tab[k];
            }
        }

        value = test.peek();
        System.out.println("Testing peek() (expected value: " + expectedValue + ") - Success? " + (expectedValue == value));

        // Testing poll()
        expectedValue = 57;
        test.poll();
        value = test.peek();
        System.out.println("Testing poll() + peek() (expected value: " + expectedValue + ") - Success? " + (expectedValue == value));

        // Testing offer()
        expectedValue = 100;
        test.offer(expectedValue);
        value = test.peek();
        System.out.println("Testing offer() + peek() (expected value: " + expectedValue + ") - Success? " + (expectedValue == value));

        // Testing hasNext()
        iterator = test.iterator();
        answer = iterator.hasNext();
        System.out.println("Testing hasNext() at current position (expected value: " + true + ") - Success? " + (true == answer));

        // Testing next()
        iterator = test.iterator();
        expectedValue = 57;
        value = iterator.next();
        System.out.println("Testing next() (expected value: " + expectedValue + ") - Success? "  + (expectedValue == value));

        // Testing iterator out of bounds
        test.offer(-50);
        System.out.print("Testing hasNext() on lowest value of heap (expected value: throw exception) - Success? ");
        try {
            iterator.next();
            System.out.println(false);
        } catch (ConcurrentModificationException e) {
            System.out.println(true);
        }

    }

    private static <AnyType>
    String printArray(AnyType[] a) {
        String outputString = "";

        for (AnyType item : a) {
            outputString += item;
            outputString += ", ";
        }

        return outputString.substring(0, outputString.length() - 2);
    }
}
