import org.junit.Test;

import static org.junit.Assert.*;

public class CommonAncestorTest {

    public CommonAncestor<Integer> getTestCA() {
        CommonAncestor<Integer> tree = new CommonAncestor<>();
        tree.add(null, 0, 7);
        tree.add(7, 0, 3);
        tree.add(7, 1, 4);
        tree.add(3, 0, 2);
        tree.add(4, 1, 8);
        tree.add(3, 1, 5);
        tree.add(2, 0, 1);
        tree.add(2, 1, 6);

        return tree;
    }

    @Test
    public void testIsCommonAncestor_root() {
        assertEquals(true, getTestCA().isCommonAncestor(getTestCA().getRoot(), 1, 7));
    }

    @Test
    public void testIsCommonAncestor_directParentForBothNodes() {
        assertEquals(true, getTestCA().isCommonAncestor(getTestCA().findNode(getTestCA().getRoot(),
                2), 1, 6));
    }

    @Test
    public void testIsCommonAncestor_notValidNodes() {
        assertEquals(false, getTestCA().isCommonAncestor(getTestCA().findNode(getTestCA().getRoot(),
                2), 10, 6));
    }

    @Test
    public void testIsCommonAncestor_oneNodeIsLCA() {
        assertEquals(true, getTestCA().isCommonAncestor(getTestCA().findNode(getTestCA().getRoot(),
                4), 4, 8));
    }

    @Test
    public void testGetCommonAncestor_ancestorNotRoot() {
        assertEquals((Integer) 3, getTestCA().getCommonAncestor(5, 6));
    }

    @Test
    public void testGetCommonAncestor_oneNodeIsLCA() {
        assertEquals((Integer) 4, getTestCA().getCommonAncestor(4, 8));
    }

    @Test
    public void testGetCommonAncestor_oneNodeIsLCA_root() {
        assertEquals((Integer) 7, getTestCA().getCommonAncestor(7, 6));
    }

    @Test
    public void testGetCommonAncestor_twoLeaves() {
        assertEquals((Integer) 7, getTestCA().getCommonAncestor(1, 8));
    }

    @Test
    public void testGetCommonAncestor_directParentForBothNodes() {
        assertEquals((Integer) 3, getTestCA().getCommonAncestor(2, 5));
    }

    @Test
    public void testGetCommonAncestor_notValidNodes() {
        assertEquals((Integer) null, getTestCA().getCommonAncestor(-1, -2));
    }
}
