import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WordSearchTest {

    @Test
    public void getWords_emptyGrid() {
        WordSearch ws = new WordSearch("wordSearch.txt", "dictionary.txt");
        ws.setGrid(0,0, new String[0][0]);
        assertEquals(null, ws.getWords());
    }

    @Test
    public void getWords_emptyDictionary() {
        WordSearch ws = new WordSearch("wordSearch.txt", "dictionary.txt");
        ws.getEmptyDictionary();
        assertEquals(new ArrayList(), ws.getWords());
    }

    @Test
    public void getWords_validInput() {
        WordSearch ws = new WordSearch("wordSearch.txt", "dictionary.txt");
        List<String> list = new ArrayList<>();
        list.add("CAT");
        list.add("CAR");
        list.add("CARD");
        assertEquals(list, ws.getWords());
    }
}
