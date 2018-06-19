import org.junit.Test;

import static org.junit.Assert.*;

public class DictionaryTest {

    @Test
    public void isWord_null() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertEquals(false, dictionary.isWord(null));
    }

    @Test
    public void isWord_invalidWord() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertEquals(false, dictionary.isWord("HELLO"));
    }

    @Test
    public void isWord_validWord() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertEquals(true, dictionary.isWord("CAT"));
    }

    @Test
    public void isPrefix_null() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertEquals(false, dictionary.isPrefix(null));
    }

    @Test
    public void isPrefix_invalidPrefix() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertEquals(false, dictionary.isPrefix("HE"));
    }

    @Test
    public void isPrefix_validPrefix() {
        Dictionary dictionary = new Dictionary("dictionary.txt");
        assertEquals(true, dictionary.isPrefix("CA"));
    }
}
