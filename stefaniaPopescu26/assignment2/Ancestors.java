import java.util.Vector;

/*
    When there are no ancestors to print, the printAncestors method
    will print "No ancestors to show".
    When the method is called for a node that doesn't exist, it
    will print "Node not found" and "No ancestors to show".
 */

public class Ancestors {

    /*
        The node structure for the tree.
     */
    class Node<T> {
        private T data;
        private Node<T> left, right, parent;

        public Node(T data, Node<T> left, Node<T> right, Node<T> parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node() {
            this(null, null, null, null);
        }

        public T getData() {
            return data;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }
    }

    /*
        The tree structure.
     */
    class Tree<T> {
        private Node<T> root;

        public Tree() {
            root = new Node<>();
        }

        public Node<T> getRoot() {
            return root;
        }

        public boolean isEmpty() {
            return root.getData() == null;
        }

        public Node<T> findNode(Node<T> start, T data) {
            Node<T> left = null;
            Node<T> right = null;
            if (start.getData() == data)
                return start;

            if (start.getLeft() != null)
                left = findNode(start.getLeft(), data);

            if (start.getRight() != null)
                right = findNode(start.getRight(), data);

            if (left != null)
                return left;
            if (right != null)
                return right;

            return null;
        }

        public void add (T parent_data, int direction, T data) {
            /*
                direction == 0 -> node to the left
                direction == 1 -> node to the right
             */
            Node<T> aux;
            Node<T> parent;

            if (isEmpty()) {
                root.setData(data);
                return;
            } else {
                parent = findNode(root, parent_data);
            }

            if (parent == null)
                return;

            /*
                If the parent already has a child to the left or to the right,
                that child will be transferred to the new node.
             */
            if (direction == 0) {

                if (parent.getLeft() != null) {
                    aux = new Node<>(data, parent.getLeft(), null, parent);
                } else {
                    aux = new Node<>(data, null, null, parent);
                }

                parent.setLeft(aux);
            } else {

                if (parent.getRight() != null) {
                    aux = new Node<>(data, null, parent.getRight(), parent);
                } else {
                    aux = new Node<>(data, null, null, parent);
                }

                parent.setRight(aux);
            }
        }

        public Vector<Node<T>> getAncestors(T data) {
            Vector<Node<T>> ancestors = new Vector<>();
            Node<T> node = findNode(root, data);

            if (node == null) {
                System.out.println("Node not found");
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
    }

    public static void main(String[] args) {
        Ancestors ancestors = new Ancestors();
        Tree<Integer> tree = ancestors. new Tree<>();

        /*
            The tree from the example.
         */
        tree.add(0, 0, 7);
        tree.add(7, 0, 3);
        tree.add(7, 1, 4);
        tree.add(3, 0, 2);
        tree.add(4, 1, 8);
        tree.add(3, 1, 5);
        tree.add(2, 0, 1);
        tree.add(2, 1, 6);

        tree.printAncestors(1);
        tree.printAncestors(7);
        tree.printAncestors(3);
        tree.printAncestors(-1);
    }
}
