import org.junit.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class UnknownAlphabetTest {

    @Test
    public void testBasic() {
        // test case given in the question
        List<String> dictionary = Arrays.asList("ART", "RAT", "CAT", "CAR");
        List<Character> alphabetExpected = Arrays.asList('A', 'T', 'R', 'C');
        assertEquals(alphabetExpected, UnknownAlphabet.findUnkownAlphabet(dictionary));
    }

    @Test
    public void testEmpty() {
        // empty test case
        List<String> dictionary = new LinkedList<>();
        List<Character> alphabetExpected = new LinkedList<>();
        assertEquals(alphabetExpected, UnknownAlphabet.findUnkownAlphabet(dictionary));
    }

    @Test
    public void testBasic2() {
        // additional test case
        List<String> dictionary = Arrays.asList("&RT", "RAT", "CA+", "CAR");
        List<Character> alphabetExpected = Arrays.asList('A', 'T', '&', '+', 'R', 'C');
        assertEquals(alphabetExpected, UnknownAlphabet.findUnkownAlphabet(dictionary));
    }

}
