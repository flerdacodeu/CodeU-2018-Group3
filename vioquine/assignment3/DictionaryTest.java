import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DictionaryTest {

    @Test
    public void testInsertEmptyList(){
        Dictionary dictionary = new Dictionary(new ArrayList<>());
        assertFalse(dictionary.isWord("CARA"));
    }

    @Test
    public void testInsertAssignmentExampleList(){
        Dictionary dictionary = new Dictionary(Arrays.asList("CAR", "CARD", "CART", "CAT"));
        assertTrue(dictionary.isWord("CAR"));
        assertTrue(dictionary.isWord("CARD"));
        assertTrue(dictionary.isWord("CART"));
        assertTrue(dictionary.isWord("CAT"));

        assertFalse(dictionary.isWord("CARA"));
    }

    @Test
    public void testIsPrefix(){
        Dictionary dictionary = new Dictionary(Arrays.asList("CAR", "CARD", "CART", "CAT"));
        assertTrue(dictionary.isPrefix("CAR"));
        assertTrue(dictionary.isPrefix("CARD"));
        assertTrue(dictionary.isPrefix("CART"));
        assertTrue(dictionary.isPrefix("CAT"));
        assertTrue(dictionary.isPrefix("C"));
        assertTrue(dictionary.isPrefix("CA"));

        assertFalse(dictionary.isPrefix("A"));
    }

    @Test
    public void testIsWord(){
        Dictionary dictionary = new Dictionary(Arrays.asList("CAR", "CARD", "CART", "CAT"));
        assertTrue(dictionary.isWord("CAR"));
        assertFalse(dictionary.isWord("CARA"));
    }
}
