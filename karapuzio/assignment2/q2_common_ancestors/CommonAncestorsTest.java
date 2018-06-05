package test.edu.codeU.assignment2.CommonAncestor;

import edu.codeU.assignment2.BinarySearchElementExeption;
import edu.codeU.assignment2.BinaryTreeInitializationExceprion;
import edu.codeU.assignment2.CommonAncestor.BinaryTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommonAncestorsTest {
    BinaryTree tree = new BinaryTree();

    @Before
    public void createTree() throws BinaryTreeInitializationExceprion{
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
    public void checkCorrectAnswerFirst() throws BinarySearchElementExeption{
        tree.treeTraversal();
        int lca = tree.getCommonAncestors(6, 5);
        Assert.assertTrue(lca == 3);
    }

    @Test
    public void checkCorrectAnswerSecond() throws  BinarySearchElementExeption{
        tree.treeTraversal();
        int lca = tree.getCommonAncestors(6, 8);
        Assert.assertTrue(lca == 7);
    }

    /**
     * Test for nodes when on of them is ancestor of another one.
     */
    @Test
    public void checkCorrectAnswerThird() throws BinarySearchElementExeption{
        tree.treeTraversal();
        int lca = tree.getCommonAncestors(1, 3);
        Assert.assertTrue(lca == 3);
    }

    @Test(expected = BinaryTreeInitializationExceprion.class)
    public void checkInitializeError() throws BinaryTreeInitializationExceprion{
        BinaryTree tree1 = new BinaryTree();
        tree1.addElement("LR", 3);
    }
}
