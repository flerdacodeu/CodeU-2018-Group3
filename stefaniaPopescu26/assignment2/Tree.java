public class Tree<T> {
    private Node<T> root;

    public Tree() {
        root = new Node<>();
    }

    public Node<T> getRoot() {
        return root;
    }

    public String getInorder() {
        StringBuilder sb = new StringBuilder();
        inorderTraversal(root, sb);
        return sb.toString();
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

        if (left != null)
            return left;

        if (start.getRight() != null)
            right = findNode(start.getRight(), data);

        return right;
    }

    public void add (T parent_data, int direction, T data) {
        /*
            direction == 0 -> node to the left
            direction == 1 -> node to the right
         */
        Node<T> aux;
        Node<T> parent;

        if (parent_data == null) {
            root.setData(data);
            return;
        }

        parent = findNode(root, parent_data);
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

    private void inorderTraversal(Node<T> node, StringBuilder sb) {
        if (node == null)
            return;

        inorderTraversal(node.getLeft(), sb);
        sb.append(node.getData()).append(" ");
        inorderTraversal(node.getRight(), sb);

    }
}
