import java.util.List;


public class CompanyNode implements Comparable<CompanyNode> {
    private Integer money;
    private BinarySearchTree<CompanyNode> childs;
    public CompanyNode worstChild;

    // TODO: initialisation
    // O(1)
    public CompanyNode(Integer data) {
        money = data;
        childs = null;
        worstChild = null;
    }

    // TODO: la compagnie courante achete une autre compagnie
    // O(log(n))
    public void buy(CompanyNode item) {
        // Update worst child
        if (worstChild != null) { // root has a worstChild
            if (item.compareTo(worstChild) < 0)         // if item is worse than worseChild
                worstChild = item;                      // item becomes the worstChild
            if (item.worstChild != null)
                if (item.worstChild.compareTo(worstChild) < 0)  // if item.worstChild is worse than worstChild
                    worstChild = item.worstChild;               // item.worstChild becomes the worstChild
        } else { // root has no worstChild
            worstChild = item; // item becomes worstChild by default
            if (item.worstChild != null)
                if (item.compareTo(item.worstChild) < 0) // if item.worstChild is worse than item
                    worstChild = item.worstChild;       // item.worstChild becomes the worstChild
        }

        // Update money
        money += item.getMoney();

        // Add acquisition
        if (childs == null)
            childs = new BinarySearchTree<CompanyNode>(item);
        else
            childs.insert(item);
    }

    // TODO: on retourne le montant en banque de la compagnie
    // O(1)
    public Integer getMoney() {
        return money;
    }

    // TODO: on rempli le builder de la compagnie et de ses enfants avec le format
    //A
    // > A1
    // > A2
    // >  > A21...
    // les enfants sont afficher du plus grand au plus petit (voir TestCompany.testPrint)
    // O(n)
    public void fillStringBuilderInOrder(StringBuilder builder, String prefix) {
        builder.append(prefix + money + "\n");
        prefix += " > ";
        if (childs != null) {
            List<BinaryNode<CompanyNode>> childList = childs.getItemsInOrder();
            for (int i = childList.size() - 1; i >= 0; i--) {
                childList.get(i).getData().fillStringBuilderInOrder(builder, prefix);
            }
        }

    }

    // TODO: on override le comparateur pour defenir l'ordre
    @Override
    public int compareTo(CompanyNode item) {
        if (money < item.getMoney())
            return -1;
        else if (money > item.getMoney())
            return 1;
        else
            return 0;
    }
}
