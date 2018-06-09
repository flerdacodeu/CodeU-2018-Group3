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
    public void testIsCommonAncestor1() {
        assertEquals(true, getTestCA().isCommonAncestor(getTestCA().getRoot(), 1, 7));
    }

    @Test
    public void testIsCommonAncestor2() {
        assertEquals(true, getTestCA().isCommonAncestor(getTestCA().findNode(getTestCA().getRoot(),
                2), 1, 6));
    }

    @Test
    public void testIsCommonAncestor3() {
        assertEquals(false, getTestCA().isCommonAncestor(getTestCA().findNode(getTestCA().getRoot(),
                2), 10, 6));
    }

    @Test
    public void testIsCommonAncestor4() {
        assertEquals(true, getTestCA().isCommonAncestor(getTestCA().findNode(getTestCA().getRoot(),
                4), 4, 8));
    }

    @Test
    public void testGetCommonAncestor1() {
        assertEquals((Integer) 3, getTestCA().getCommonAncestor(5, 6));
    }

    @Test
    public void testGetCommonAncestor2() {
        assertEquals((Integer) 7, getTestCA().getCommonAncestor(7, 6));
    }

    @Test
    public void testGetCommonAncestor3() {
        assertEquals((Integer) 7, getTestCA().getCommonAncestor(1, 8));
    }

    @Test
    public void testGetCommonAncestor4() {
        assertEquals((Integer) 4, getTestCA().getCommonAncestor(4, 8));
    }

    @Test
    public void testGetCommonAncestor5() {
        assertEquals((Integer) null, getTestCA().getCommonAncestor(10, 6));
    }

    @Test
    public void testGetCommonAncestor6() {
        assertEquals((Integer) 2, getTestCA().getCommonAncestor(1, 6));
    }

    @Test
    public void testGetCommonAncestor7() {
        assertEquals((Integer) null, getTestCA().getCommonAncestor(-1, -2));
    }
}
