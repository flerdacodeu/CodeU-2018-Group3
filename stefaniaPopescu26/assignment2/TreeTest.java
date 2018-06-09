import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {

    public Tree<Integer> getTestTree1() {
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

    public Tree<Character> getTestTree2() {
        Tree<Character> tree = new Tree<>();
        tree.add(null, 0, 'L');
        tree.add('L', 0, 'e');
        tree.add('e', 0, 'H');
        tree.add('e', 1, 'l');
        tree.add('L', 1, 'o');
        tree.add('o', 1, '!');

        return tree;
    }

    @Test
    public void testAdd1() {
        assertEquals("1 2 6 3 5 7 4 8 ", getTestTree1().getInorder());
        assertEquals("H e l L o ! ", getTestTree2().getInorder());
        assertEquals("null ", new Tree<Integer>().getInorder());
    }

    @Test
    public void testAdd2() {
        assertEquals("H e l L o ! ", getTestTree2().getInorder());
    }

    @Test
    public void testAdd3() {
        assertEquals("null ", new Tree<Integer>().getInorder());
    }
}
