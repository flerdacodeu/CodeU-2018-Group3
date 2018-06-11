import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.assertEquals;


public class CommonAncestorTest {
    BinaryTree<Integer> bt;
    BinaryTree<Integer> dupTree;
    BinaryTree<Integer> emptyTree;

    @Before
    public void setUp() throws Exception {
        /* not the same tree as assign 2 -
                      7
                    3   4
                  2  5  9  8
                1  6                    */
        int[] list = {7,3,4,2,5,9,8,1,6};
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

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBasics() {
        // key is root - no ancestor, empty list
        assertEquals(Optional.empty(),bt.lowestCommonAncestor(7,6));
        assertEquals(Optional.empty(),bt.lowestCommonAncestor(7,3));
        // key in either left or right subtree
        assertEquals(Optional.of(7),bt.lowestCommonAncestor(2,8));
        assertEquals(Optional.of(4), bt.lowestCommonAncestor(9,8));
        assertEquals(Optional.of(7), bt.lowestCommonAncestor(1,8));
        assertEquals(Optional.of(3), bt.lowestCommonAncestor(1,5));
        // one key is ancestor of another
        assertEquals(Optional.of(3),bt.lowestCommonAncestor(2,6));
        assertEquals(Optional.of(7),bt.lowestCommonAncestor(9,4));
        //none existant keys
        assertEquals(Optional.empty(),bt.lowestCommonAncestor(3,150));
        assertEquals(Optional.empty(),bt.lowestCommonAncestor(140,2));
        assertEquals(Optional.empty(),bt.lowestCommonAncestor(100,-1));
    }

    @Test
    public void testEmpty() {
        assertEquals(Optional.empty(),emptyTree.lowestCommonAncestor(6,7));
    }

    @Test
    public void duplicateKeyTest() {
        // remark 1: if key is duplicate in tree it will return the ancestors of first found one (closer to the root)
        assertEquals(Optional.of(1),dupTree.lowestCommonAncestor(4,2));
        // because of remark 1 below test is done for same nodes not two different nodes.
        assertEquals(Optional.of(1),dupTree.lowestCommonAncestor(2,2));

    }
}
