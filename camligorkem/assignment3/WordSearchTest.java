import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WordSearchTest {
    Dictionary dic;
    Dictionary dic2;
    Optional<HashSet<String>> answer;
    char[][] grid = {{'a', 'a', 'r'}, {'t', 'c', 'd'},{'t','r','e'}};
    char[][] grid2 = {{'a', 'a', 'r'}, {'t', 'c', 'd'},{'t','r','e'},{'e','e','e'}};

    @Before
    public void setUp()  {
        dic = new Dictionary();
        String[] words = {"CAR", "CARD", "CART", "CAT"};
        for(String word: words){
            dic.insert(word);
        }
        dic2 = new Dictionary();
        String[] words2 = {"CAR", "CARD", "CART", "CAT", "TREE"};
        for(String word: words2){
            dic2.insert(word);
        }
        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList("car", "card", "cat"));
        answer= Optional.of(set);
    }

    @Test
    public void testBasics() {

        assertEquals(answer, WordSearch.findWords(dic, grid));
        //false word
        Optional<HashSet<String>> ans2 = Optional.of(new HashSet<>(Arrays.asList("car", "card", "cat", "apple")));
        assertNotEquals(ans2, WordSearch.findWords(dic, grid));
        //prefix word
        Optional<HashSet<String>> ans3 = Optional.of(new HashSet<>(Arrays.asList("car", "card", "ca")));
        assertNotEquals(ans3, WordSearch.findWords(dic, grid));
        //can't use same letter in same position in grid twice for creation of single word
        Optional<HashSet<String>> ans4 = Optional.of(new HashSet<>(Arrays.asList("car", "card", "cat", "tree")));
        assertEquals(ans4, WordSearch.findWords(dic2, grid2));
        //false word 2
        Optional<HashSet<String>> ans5 = Optional.of(new HashSet<>(Arrays.asList("car", "card", "cat", "care")));
        assertNotEquals(ans5, WordSearch.findWords(dic2, grid2));
    }
}
