import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class PrintAncestorsTest {

    BinaryTreeNode<Integer> root;

    @Before
    public void init() {
        root = TreeGenerator.generateBinaryTree();
    }

    @Test
    public void testEmptyTree() {
        assertEquals(PrintAncestors.getAncestors(null, 1), Optional.empty());
    }

    @Test
    public void testTree() {
        assertEquals(PrintAncestors.getAncestors(root, 1), Optional.of(Arrays.asList(2, 3, 7)));
        assertEquals(PrintAncestors.getAncestors(root, 2), Optional.of(Arrays.asList(3, 7)));
        assertEquals(PrintAncestors.getAncestors(root, 3), Optional.of(Collections.singletonList(7)));
        assertEquals(PrintAncestors.getAncestors(root, 5), Optional.of(Arrays.asList(3, 7)));
        assertEquals(PrintAncestors.getAncestors(root, 6), Optional.of(Arrays.asList(2, 3, 7)));
        assertEquals(PrintAncestors.getAncestors(root, 7), Optional.of(Collections.emptyList()));
        assertEquals(PrintAncestors.getAncestors(root, 8), Optional.of(Arrays.asList(4, 7)));
    }

    @Test
    public void testWrongKey() {
        assertEquals(PrintAncestors.getAncestors(root, -1), Optional.empty());
        assertEquals(PrintAncestors.getAncestors(root, 10), Optional.empty());
    }
}
