package test.edu.codeU.assignment2;

import edu.codeU.assignment2.BinarySearchElementException;
import edu.codeU.assignment2.BinaryTree;
import edu.codeU.assignment2.BinaryTreeInitializationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTest {
    private BinaryTree<Integer> tree = new BinaryTree();

    @Before
    public void createTree() throws BinaryTreeInitializationException {
        tree.addElement("", 7);
        tree.addElement("L", 3);
        tree.addElement("LL", 2);
        tree.addElement("LR", 5);
        tree.addElement("LLL", 1);
        tree.addElement("LLR", 6);
        tree.addElement("R", 4);
        tree.addElement("RR", 8);
    }

    @Test
    public void checkLCACorrectAnswerFirst() throws BinarySearchElementException {
        tree.treeTraversal();
        int lca = tree.getCommonAncestors(6, 5);
        Assert.assertTrue(lca == 3);
    }

    @Test
    public void checkLCACorrectAnswerSecond() throws BinarySearchElementException {
        tree.treeTraversal();
        int lca = tree.getCommonAncestors(6, 8);
        Assert.assertTrue(lca == 7);
    }

    /**
     * Test for nodes when on of them is ancestor of another one.
     */
    @Test
    public void checkLCACorrectAnswerThird() throws BinarySearchElementException {
        tree.treeTraversal();
        int lca = tree.getCommonAncestors(1, 3);
        Assert.assertTrue(lca == 3);
    }

    @Test(expected = BinaryTreeInitializationException.class)
    public void checkInitializeError() throws BinaryTreeInitializationException {
        BinaryTree tree1 = new BinaryTree();
        tree1.addElement("LR", 3);
    }

    @Test
    public void checkAllAncestorsCorrectAnswer() throws BinarySearchElementException {
        tree.treeTraversal();
        List<Integer> path = tree.getAllAncestors(1);
        List<Integer> correctPath = new ArrayList<>();
        correctPath.add(7);
        correctPath.add(3);
        correctPath.add(2);
        Assert.assertTrue(correctPath.equals(path));
    }
}
