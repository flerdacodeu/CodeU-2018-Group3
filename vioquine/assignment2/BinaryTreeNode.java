/**
 * Represents one node in a binary tree
 *
 * @param <T> type of the nodes content
 */
public class BinaryTreeNode<T> {
    private T content;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }
}
