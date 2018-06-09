import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class AncestorsTest {

    public Ancestors<Integer> getTree() {
        Ancestors<Integer> tree = new Ancestors<>();
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
    public void testGetAncestors1() {
        Vector<Node<Integer>> v = new Vector<>();
        assertEquals(v, getTree().getAncestors(-1));
    }

    @Test
    public void testGetAncestors2() {
        Vector<Node<Integer>> v = new Vector<>();
        assertEquals(v, getTree().getAncestors(7));
    }

    @Test
    public void testGetAncestors3() {
        Vector<Node<Integer>> v;
        v = getTree().getAncestors(8);
        String result = "";
        for (int i = 0; i < v.size(); i++)
            result += v.get(i) + " ";
        assertEquals("4 7 ", result);
    }

    @Test
    public void testGetAncestors4() {
        Vector<Node<Integer>> v;
        v = getTree().getAncestors(1);
        String result = "";
        for (int i = 0; i < v.size(); i++)
            result += v.get(i) + " ";
        assertEquals("2 3 7 ", result);
    }
}
