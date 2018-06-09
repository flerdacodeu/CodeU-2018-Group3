import java.util.Scanner;
import java.util.Vector;

/*
    When there are no ancestors to print, the printAncestors method
    will print "No ancestors to show": for the root node or
    for a node that doesn't exist.
 */

public class Ancestors<T> extends Tree<T>{

    public Vector<Node<T>> getAncestors(T data) {
        Vector<Node<T>> ancestors = new Vector<>();
        Node<T> node = findNode(getRoot(), data);

        if (node == null) {
            return ancestors;
        }

        node = node.getParent();

        while(node != null) {
            ancestors.add(node);
            node = node.getParent();
        }

        return ancestors;
    }

    public void printAncestors(T data) {
        Vector<Node<T>> ancestors = getAncestors(data);

        if (ancestors.size() == 0) {
            System.out.println("No ancestors to show");
            return;
        }

        for (int i = 0; i < ancestors.size(); i++) {
            System.out.print(ancestors.get(i).getData() + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Ancestors tree = new Ancestors();

        tree.add(null, 0, 7);
        tree.add(7, 0, 3);
        tree.add(7, 1, 4);
        tree.add(3, 0, 2);
        tree.add(4, 1, 8);
        tree.add(3, 1, 5);
        tree.add(2, 0, 1);
        tree.add(2, 1, 6);

        Scanner in = new Scanner(System.in);
        Integer data;
        data = in.nextInt();

        tree.printAncestors(data);
    }
}
