public class Node<T> {
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

    public String toString() {
        String result = "";
        return result + data;
    }
}
