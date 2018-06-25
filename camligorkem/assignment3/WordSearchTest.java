import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WordSearchTest {
    Dictionary dic;
    Dictionary dic2;
    HashSet<String> answer;
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
        answer = new HashSet<>(Arrays.asList("car", "card", "cat"));
    }

    @Test
    public void testBasics() {

        assertEquals(answer, WordSearch.findWords(dic, grid));
        //false word
        HashSet<String> ans2 = new HashSet<>(Arrays.asList("car", "card", "cat", "apple"));
        assertNotEquals(ans2, WordSearch.findWords(dic, grid));
        //prefix word
        HashSet<String> ans3 = new HashSet<>(Arrays.asList("car", "card", "ca"));
        assertNotEquals(ans3, WordSearch.findWords(dic, grid));
        //can't use same letter in same position in grid twice for creation of single word
        HashSet<String>ans4 = new HashSet<>(Arrays.asList("car", "card", "cat", "tree"));
        assertEquals(ans4, WordSearch.findWords(dic2, grid2));
        //false word 2
        HashSet<String> ans5 = new HashSet<>(Arrays.asList("car", "card", "cat", "care"));
        assertNotEquals(ans5, WordSearch.findWords(dic2, grid2));
    }
}
