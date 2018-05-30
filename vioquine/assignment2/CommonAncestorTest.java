import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the CommonAncestor method getLowestCommonAncestor
 */
public class CommonAncestorTest {
    private BinaryTreeNode<Integer> root;

    @Before
    public void init() {
        root = TreeGenerator.generateBinaryTree();
    }

    @Test
    public void testEmptyTree() {
        assertEquals(CommonAncestor.getLowestCommonAncestor(null, 1, 4), Optional.empty());
    }

    @Test
    public void testTree() {
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 1, 1), Optional.of(1));
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 1, 2), Optional.of(2));
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 1, 3), Optional.of(3));
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 1, 4), Optional.of(7));
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 1, 5), Optional.of(3));
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 1, 6), Optional.of(2));
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 1, 7), Optional.of(7));
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 1, 8), Optional.of(7));
    }

    @Test
    public void testWrongKey() {
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, -1, 8), Optional.empty());
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, 3, 18), Optional.empty());
        assertEquals(CommonAncestor.getLowestCommonAncestor(root, -3, 18), Optional.empty());
    }
}
