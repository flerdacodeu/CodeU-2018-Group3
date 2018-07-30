import org.junit.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class AllUnknownAlphabetsTest {

    @Test
    public void testBasic() {
        // test case given in the question

        List<String> dictionary = Arrays.asList("ART", "RAT", "CAT", "CAR");
        List<List<Character>> alphabetsExpected = new LinkedList<>();
        alphabetsExpected.add(Arrays.asList('A','T', 'R', 'C'));
        alphabetsExpected.add(Arrays.asList('T','A', 'R', 'C'));
        assertEquals(alphabetsExpected, AllUnknownAlphabets.findAllUnknownAlphabets(dictionary));
    }

    @Test
    public void testEmpty() {
        // empty test case

        List<String> dictionary = Arrays.asList();
        List<List<Character>> alphabetsExpected = new LinkedList<>();
        alphabetsExpected.add(new LinkedList<>());
        assertEquals(alphabetsExpected, AllUnknownAlphabets.findAllUnknownAlphabets(dictionary));
    }

    @Test
    public void testBasic2() {
        // additional test case

        List<String> dictionary = Arrays.asList("ART", "T&R", "RAT", "CAT", "CAR");
        List<List<Character>> alphabetsExpected = new LinkedList<>();
        alphabetsExpected.add(Arrays.asList('A','T', 'R', 'C', '&'));
        alphabetsExpected.add(Arrays.asList('A','T', 'R', '&', 'C'));
        alphabetsExpected.add(Arrays.asList('A','T', '&', 'R', 'C'));
        alphabetsExpected.add(Arrays.asList('A','&', 'T', 'R', 'C'));
        alphabetsExpected.add(Arrays.asList('&','A', 'T', 'R', 'C'));

        assertEquals(alphabetsExpected, AllUnknownAlphabets.findAllUnknownAlphabets(dictionary));
    }
}
