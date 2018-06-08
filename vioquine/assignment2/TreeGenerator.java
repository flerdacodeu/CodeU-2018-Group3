public class TreeGenerator {
    /**
     * Builds the example tree from the assignment task
     *
     * @return example binary tree
     */
    public static BinaryTreeNode<Integer> generateBinaryTree() {
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> node7 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> node8 = new BinaryTreeNode<>(8);

        node2.setLeft(node1);
        node2.setRight(node6);
        node3.setLeft(node2);
        node3.setRight(node5);
        node4.setLeft(node8);
        node7.setLeft(node3);
        node7.setRight(node4);

        return node7;
    }

}