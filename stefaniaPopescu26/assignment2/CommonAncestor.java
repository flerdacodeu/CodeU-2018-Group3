/*
    My code will return null for LCA of nodes that don't exist.
 */

public class CommonAncestor {

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

        private boolean isCommonAncestor(Node<T> node, T data1, T data2) {
            Node<T> left = node.getLeft();
            Node<T> right = node.getRight();

            if (left != null) {
                if (node.getData() == data1)
                    if (findNode(left, data2) != null)
                        return true;
                if (node.getData() == data2)
                    if (findNode(left, data1) != null)
                        return true;
            }

            if (right != null) {
                if (node.getData() == data1)
                    if (findNode(right, data2) != null)
                        return true;
                if (node.getData() == data2)
                    if (findNode(right, data1) != null)
                        return true;
            }

            if (left != null && right != null) {
                if (findNode(left, data1) != null && findNode(right, data2) != null)
                    return true;

                if (findNode(left, data2) != null && findNode(right, data1) != null)
                    return true;
            }

            return false;
        }

        private T LCA(Node<T> start, T data1, T data2) {
            T LCAleft = null, LCAright = null;
            Node<T> left = start.getLeft();
            Node<T> right = start.getRight();

            if (isCommonAncestor(start, data1, data2))
                return start.getData();

            if (left != null)
                LCAleft = LCA(left, data1, data2);

            if (right != null)
                LCAright = LCA(right, data1, data2);

            if (LCAleft != null)
                return LCAleft;

            if (LCAright != null)
                return LCAright;

            return null;
        }

        public T getCommonAncestor(T data1, T data2) {
            if (findNode(root, data1) == null || findNode(root, data2) == null)
                return null;

            return LCA(root, data1, data2);
        }
    }

    public static void main(String[] args) {
        CommonAncestor ancestor = new CommonAncestor();
        Tree<Integer> tree = ancestor. new Tree<>();

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

        System.out.println(tree.getCommonAncestor(5, 6));
        System.out.println(tree.getCommonAncestor(7, 6));
        System.out.println(tree.getCommonAncestor(1, 8));
        System.out.println(tree.getCommonAncestor(4, 8));
        System.out.println(tree.getCommonAncestor(10, 6));
        System.out.println(tree.getCommonAncestor(1, 6));
        System.out.println(tree.getCommonAncestor(-1, -2));
    }
}
