import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DictionaryTest {
    Dictionary dic;
    @Before
    public void setUp() {
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
        assertTrue(dic.isPrefix("card"));
        assertTrue(dic.isPrefix("cart"));
        assertTrue(dic.isPrefix("cat"));
        assertTrue(dic.isPrefix("car"));
        assertFalse(dic.isPrefix("ex"));
        assertFalse(dic.isPrefix("false"));
        assertFalse(dic.isPrefix(""));
    }
}
