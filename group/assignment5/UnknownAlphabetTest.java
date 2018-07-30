import org.junit.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class UnknownAlphabetTest {

    //  ******************* Assignment 5: Unknown Alphabet Test Cases *******************
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


    //  ******************* Challenge 2: Check Inconsistency Test Cases *******************
    @Test
    public void testInconsistencyEmpty() {
        // empty test case
        List<String> dictionary = new LinkedList<>();
        assertEquals(Optional.empty(), UnknownAlphabet.checkInconsistency(dictionary));
    }

    @Test
    public void testInconsistencyBasic() {
        // test case given in the question
        List<String> dictionary = Arrays.asList("ART", "RAT", "CAT", "CAR");
        assertEquals(Optional.empty(), UnknownAlphabet.checkInconsistency(dictionary));
    }

    @Test
    public void testInconsistencyBasic2() {
        // additional test case
        List<String> dictionary = Arrays.asList("ART", "RAT", "ACT", "CAT", "CAR");
        Set<List<Character>> inconsistent = new HashSet<>();
        inconsistent.add(new ArrayList<>(Arrays.asList('A', 'R')));
        inconsistent.add(new ArrayList<>(Arrays.asList('R', 'A')));
        assertEquals(Optional.of(inconsistent), UnknownAlphabet.checkInconsistency(dictionary));
    }

    @Test
    public void testInconsistencyBasic3() {
        // additional test case
        List<String> dictionary = Arrays.asList("ART", "RAT", "CAT", "CAR", "ACT");
        Set<List<Character>> inconsistent = new HashSet<>();
        inconsistent.add(new ArrayList<>(Arrays.asList('A', 'R')));
        inconsistent.add(new ArrayList<>(Arrays.asList('R', 'C', 'A')));
        assertEquals(Optional.of(inconsistent), UnknownAlphabet.checkInconsistency(dictionary));
    }

  
  //  ******************* Challenge 3: Make Consistent Test Cases *******************
    @Test
    public void testMakeConsistentEmpty() {
        // empty test case
        List<String> dictionary = new LinkedList<>();
        assertEquals(dictionary, UnknownAlphabet.makeConsistent(dictionary));
    }

    @Test
    public void testMakeConsistentBasic() {
        // test case given in the question
        List<String> dictionary = Arrays.asList("ART", "RAT", "CAT", "CAR");
        assertEquals(dictionary, UnknownAlphabet.makeConsistent(dictionary));
    }

    @Test
    public void testMakeConsistentBasic2() {
        // additional test case
        List<String> dictionary =  new LinkedList<>(Arrays.asList("ART", "RAT", "ACT", "CAT", "CAR"));
        List<String> subSetDictionary =  new LinkedList<>(Arrays.asList("ART", "ACT", "CAT", "CAR"));
        assertEquals(subSetDictionary, UnknownAlphabet.makeConsistent(dictionary));
    }

    @Test
    public void testMakeConsistentBasic3() {
        // additional test case
        List<String> dictionary =  new LinkedList<>(Arrays.asList("ART", "RAT", "CAT", "CAR", "ACT"));
        List<String> subSetDictionary =  new LinkedList<>(Arrays.asList("RAT", "CAT", "CAR", "ACT"));
        assertEquals(subSetDictionary, UnknownAlphabet.makeConsistent(dictionary));
    }

}
