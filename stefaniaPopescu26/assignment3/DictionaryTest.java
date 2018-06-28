import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DictionaryTest {

    @Test
    public void isWord_emptyString() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertFalse(dictionary.isWord(""));
    }

    @Test
    public void isWord_invalidWord() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertFalse(dictionary.isWord("HELLO"));
    }

    @Test
    public void isWord_validWord() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertTrue(dictionary.isWord("CAT"));
    }

    @Test
    public void isPrefix_emptyString() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertFalse(dictionary.isPrefix(""));
    }

    @Test
    public void isPrefix_invalidPrefix() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertFalse(dictionary.isPrefix("HE"));
    }

    @Test
    public void isPrefix_validPrefix() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertTrue(dictionary.isPrefix("CA"));
    }
}
