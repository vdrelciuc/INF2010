import java.util.List;


public class BinaryNode<T extends Comparable<? super T>> {
    private T data;
    private BinaryNode<T> right;
    private BinaryNode<T> left;

    // TODO: initialisation
    // O(1)
    public BinaryNode(T data) {
        this.data = data;
        right = null;
        left = null;
    }

    // TODO: on retourne la donnee voulue
    // O(1)
    public T getData() {
        return data;
    }

    // TODO: on ajoute une nouvelle donnee au bon endroit
    // O(log(n))
    public void insert(T item) {

        int compareResult = item.compareTo(data);

        if (compareResult > 0) {
            //if right node then a we need to continue looking for the place to insert
            if (right != null) {
                right.insert(item);
            } else {
                //no right node, we found the place to insert
                right = new BinaryNode<T>(item);
            }
            return;
        }

        if (compareResult <= 0) {
            //if left node then a we need to continue looking for the place to insert
            if (left != null) {
                left.insert(item);
            } else {
                //if no left node, we found the place to insert
                left = new BinaryNode<T>(item);
            }
            return;
        }
    }

    // TODO: est-ce que l'item fais partie du noeuds courant
    // O(log(n))
    public boolean contains(T item) {

        int compareResult = item.compareTo(data);

        //return true if the item was found
        if (compareResult == 0)
            return true;
        //return if item is contained in left sub-tree
        if (compareResult < 0 && left != null)
            return (left.contains(item));

        //return if item is contained in right sub-tree
        if (compareResult > 0 && right != null)
            return (right.contains(item));

        return false;
    }

    // TODO: on retourne la maximale de l'arbre
    // O(n)
    public int getHeight() {

        int leftHeight = 0;
        int rightHeight = 0;

        //calculate the height of the left sub-tree
        if (left != null)
            leftHeight = left.getHeight() + 1;


        //calculate the height of the right sub-tree
        if (right != null)
            rightHeight = right.getHeight() + 1;

        return Math.max(leftHeight, rightHeight);
    }

    // TODO: l'ordre d'insertion dans la liste est l'ordre logique
    // de manière que le plus petit item sera le premier inseré
    // O(n)
    public void fillListInOrder(List<BinaryNode<T>> result) {

        //add left sub-tree of current node to the list
        if (left != null)
            left.fillListInOrder(result);

        //add current node to the list
        result.add(this);

        //add right sub-tree of current node to the list
        if (right != null)
            right.fillListInOrder(result);

    }
}
