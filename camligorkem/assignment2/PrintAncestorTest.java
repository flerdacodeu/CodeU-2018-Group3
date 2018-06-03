import java.util.Arrays;
import static org.junit.Assert.*;

public class PrintAncestorTest {
    BinaryTree<Integer> bt;
    BinaryTree<Integer> dupTree;
    BinaryTree<Integer> emptyTree;

    @org.junit.Before
    public void setUp() throws Exception {
        /* not the same tree as assign 2 -
                      7
                    3   4
                  2  5  8  1
                6

         */
        int[] list = {7,3,4,2,5,8,1,6};
        BinaryNode<Integer> root = new BinaryNode<Integer>(7);
        bt = new BinaryTree<>(root);
        for(int i=1; i<list.length;i++){
            bt.insertNode(bt.getRoot(), i,list[i]);
        }
        // empty tree
        emptyTree = new BinaryTree<>(null);
        // Tree with duplicate elements
        int[] list2 = {1,2,4,3,2};
        BinaryNode<Integer> root2 = new BinaryNode<Integer>(1);
        dupTree = new BinaryTree<>(null);
        dupTree.setRoot(root2);
        for(int i=1; i<list2.length;i++){
            dupTree.insertNode(dupTree.getRoot(), i,list2[i]);
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testBasics() {
        // key is root - no ancestor, empty list
        assertEquals(Arrays.asList(),bt.getAncestor(bt.getRoot(),7));
        // key in either left or right subtree
        assertEquals(Arrays.asList(2,3,7),bt.getAncestor(bt.getRoot(),6));
        assertEquals(Arrays.asList(4,7),bt.getAncestor(bt.getRoot(),1));
        assertEquals(Arrays.asList(3,7),bt.getAncestor(bt.getRoot(),2));
        assertEquals(Arrays.asList(4,7),bt.getAncestor(bt.getRoot(),8));
        //none existant keys
        assertEquals(null,bt.getAncestor(bt.getRoot(),150));
        assertEquals(null,bt.getAncestor(bt.getRoot(),-1));
    }

    @org.junit.Test
    public void testEmpty() {
        assertEquals(null,emptyTree.getAncestor(emptyTree.getRoot(),6));
    }

    @org.junit.Test
    public void duplicateKeyTest() {
        // if key is duplicate in tree it will return the ancestors of first found one (closer to the root)
        assertEquals(Arrays.asList(1),dupTree.getAncestor(dupTree.getRoot(),2));
    }


}
