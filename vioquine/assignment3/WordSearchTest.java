import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class WordSearchTest {

    Dictionary dictionary;
    char[][] grid;

    @Before
    public void prepare(){
        dictionary = new Dictionary(Arrays.asList("CAR", "CARD", "CART", "CAT"));
        grid = new char[][]{{'a', 'a', 'r'}, {'t', 'c', 'd'}};
    }

    /**
     * Test for the example on the assignment
     */
    @Test
    public void testFromAssignment() {
        Set<String> expectedResult = new HashSet<>(Arrays.asList("car", "card", "cat"));

        Set<String> actualResult = WordSearch.searchWords(dictionary, grid);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCheckForPosition(){
        boolean[][] visited = new boolean[][]{{true,false,false},{false,true,false}};
        assertEquals(WordSearch.checkForPosition(dictionary,grid,0,0,"ca",visited),Optional.empty());
        assertEquals(WordSearch.checkForPosition(dictionary,grid,1,0,"ca",visited),Optional.of(new HashSet<>(Collections.singletonList("cat"))));
    }
}
