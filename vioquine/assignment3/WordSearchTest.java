import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordSearchTest {

    @Test
    public void testEmptyGrid(){
        Dictionary dictionary = new Dictionary(Arrays.asList("CAR", "CARD", "CART", "CAT"));
        char[][] grid = new char[][]{};

        Set<String> result = WordSearch.searchWords(dictionary, grid);

        assertTrue(result.isEmpty());
    }

    /**
     * Test for the example on the assignment
     */
    @Test
    public void testFromAssignment() {
        Dictionary dictionary = new Dictionary(Arrays.asList("CAR", "CARD", "CART", "CAT"));
        char[][] grid = new char[][]{{'a', 'a', 'r'}, {'t', 'c', 'd'}};
        Set<String> expectedResult = new HashSet<>(Arrays.asList("car", "card", "cat"));

        Set<String> actualResult = WordSearch.searchWords(dictionary, grid);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testBiggerGid() {
        Dictionary dictionary = new Dictionary(Arrays.asList("CAR", "CARD", "CART", "CAT", "DEEP", "EAT", "BIT", "BUILD"));
        char[][] grid = new char[][]{{'a', 'a', 'r', 'b'}, {'t', 'c', 'd', 'i'}, {'p', 'e', 'e', 't'}, {'a', 't', 'b', 'y'}};
        Set<String> expectedResult = new HashSet<>(Arrays.asList("car", "card", "cat", "deep", "eat", "bit"));

        Set<String> actualResult = WordSearch.searchWords(dictionary, grid);

        assertEquals(expectedResult, actualResult);
    }
}
