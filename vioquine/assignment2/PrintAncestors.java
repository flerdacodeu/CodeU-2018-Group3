import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrintAncestors {
    public static void main(String... args) {
        printAncestors(TreeGenerator.generateBinaryTree(), 6);
    }

    /**
     * Prints all the ancestors of the key in the given binary tree
     *
     * @param root root node of the binary tree
     * @param key  content of the searched node
     * @param <T>  type of the node contents
     */
    private static <T> void printAncestors(BinaryTreeNode<T> root, T key) {
        Optional<List<T>> result = getAncestors(root, key);
        result.ifPresent(ts -> {
            String ancestors = "";
            for (T item : ts) {
                ancestors = ancestors.equals("") ? item.toString() : ancestors + ", " + item.toString();
            }
            System.out.println(ancestors);
        });
    }

    /**
     * Generates recursively the list of ancestors for the given key in the given binary tree
     *
     * @param root root node of the binary tree
     * @param key  content of the searched node
     * @param <T>  type of the node contents
     * @return Optional of the list of ancestors
     */
    static <T> Optional<List<T>> getAncestors(BinaryTreeNode<T> root, T key) {
        if (root == null) {
            return Optional.empty();
        }
        if (root.getContent().equals(key)) {
            return Optional.of(new ArrayList<>());
        }
        Optional<List<T>> resultLeft = getAncestors(root.getLeft(), key);
        if (resultLeft.isPresent()) {
            resultLeft.get().add(root.getContent());
            return resultLeft;
        }
        Optional<List<T>> resultRight = getAncestors(root.getRight(), key);
        if (resultRight.isPresent()) {
            resultRight.get().add(root.getContent());
            return resultRight;
        }
        return Optional.empty();
    }
}