import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DictionaryTest {
    WordSearch ws;
    Dictionary dic;
    Optional<HashSet<String>> answer;
    char[][] grid = {{'a', 'a', 'r'}, {'t', 'c', 'd'}};

    @Before
    public void setUp() throws Exception {
        dic = new Dictionary();
        String[] words = {"CAR", "CARD", "CART", "CAT"};
        for(String word: words){
            dic.insert(word);
        }
    }

    @Test
    public void testBasics() {
        //isWord
        assertTrue(dic.isWord("car"));
        assertTrue(dic.isWord("card"));
        assertTrue(dic.isWord("cat"));
        assertFalse(dic.isWord("ex"));
        assertFalse(dic.isWord("false"));
        assertFalse(dic.isWord(""));

        //isPrefix
        assertTrue(dic.isPrefix("c"));
        assertTrue(dic.isPrefix("ca"));
        assertTrue(dic.isPrefix("c"));
        assertTrue(dic.isPrefix("card"));
        assertTrue(dic.isPrefix("cart"));
        assertTrue(dic.isPrefix("cat"));
        assertTrue(dic.isPrefix("car"));
        assertFalse(dic.isPrefix("ex"));
        assertFalse(dic.isPrefix("false"));
        assertFalse(dic.isPrefix(""));
    }
}
