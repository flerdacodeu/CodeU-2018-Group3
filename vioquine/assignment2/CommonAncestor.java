import java.util.List;
import java.util.Optional;

public class CommonAncestor {
    public static void main(String... args) {
        Optional<Integer> result = getLowestCommonAncestor(TreeGenerator.generateBinaryTree(), 6, 5);

        result.ifPresent(System.out::println);
    }

    /**
     * Finds the lowest common ancestor of two nodes in a binary tree
     * Using the getAncestors method from Q1
     *
     * @param root root of the binary tree
     * @param key1 content of first node
     * @param key2 content of second node
     * @param <T>  type of the node content
     * @return lowest common ancestor
     */
    static <T> Optional<T> getLowestCommonAncestor(BinaryTreeNode<T> root, T key1, T key2) {
        Optional<List<T>> ancestorsKey1 = PrintAncestors.getAncestors(root, key1);
        Optional<List<T>> ancestorsKey2 = PrintAncestors.getAncestors(root, key2);
        if (ancestorsKey1.isPresent() && ancestorsKey2.isPresent()) {
            ancestorsKey1.get().add(0, key1);
            ancestorsKey2.get().add(0, key2);
            for (T ancestor1 : ancestorsKey1.get()) {
                for (T ancestor2 : ancestorsKey2.get()) {
                    if (ancestor1.equals(ancestor2)) {
                        return Optional.of(ancestor1);
                    }
                }
            }
        }
        return Optional.empty();
    }
}
