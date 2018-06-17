import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {

    public Tree<Integer> getTestTreeInts() {
        Tree<Integer> tree = new Tree<>();
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
    public void testAddInts() {
        assertEquals("1 2 6 3 5 7 4 8 ", getTestTreeInts().getInorder());
    }

    @Test
    public void testInorderNull() {
        assertEquals("null ", new Tree<Integer>().getInorder());
    }
}
