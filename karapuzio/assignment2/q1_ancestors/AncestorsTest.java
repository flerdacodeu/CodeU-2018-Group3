package test.edu.codeU.assignment2.Ancestors;

import edu.codeU.assignment2.Ancestors.BinaryTree;
import edu.codeU.assignment2.BinarySearchElementExeption;
import edu.codeU.assignment2.BinaryTreeInitializationExceprion;
import org.junit.Before;
import org.junit.Test;

public class AncestorsTest {
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
    public void checkCorrectAnswer() throws BinarySearchElementExeption{
        tree.treeTraversal();
        tree.getAllAncestors(1);
        //We should get in the console: 2 3 7
    }

    @Test(expected = BinaryTreeInitializationExceprion.class)
    public void checkInitializeError() throws BinaryTreeInitializationExceprion{
        BinaryTree tree1 = new BinaryTree();
        tree1.addElement("LR", 3);
    }

    @Test(expected = BinarySearchElementExeption.class)
    public void checkIncorrectKey() throws BinarySearchElementExeption{
        tree.treeTraversal();
        tree.getAllAncestors(10);
        //We should get in the console: 2 3 7
    }
}
