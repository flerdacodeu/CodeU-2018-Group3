import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnknownAlphabetTests {
    @Test
    public void testEmptyDictionary() {
        List<String> words = new ArrayList<>();
        List<Character> alphabet = UnknownAlphabet.findAlphabet(words);

        assertEquals(0, alphabet.size());
    }

    @Test
    public void testSmallDictionary() {
        List<String> words = Arrays.asList("ART","RAT","CAT","CAR");
        List<Character> alphabet = UnknownAlphabet.findAlphabet(words);

        assertEquals(4, alphabet.size());
        assertEquals('A', alphabet.get(0).charValue());
        assertEquals('T', alphabet.get(1).charValue());
        assertEquals('R', alphabet.get(2).charValue());
        assertEquals('C', alphabet.get(3).charValue());
    }
}
