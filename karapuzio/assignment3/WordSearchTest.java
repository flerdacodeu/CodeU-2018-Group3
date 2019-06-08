package test.edu.codeU.assignment3;

import edu.codeU.assignment3.WordSearch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WordSearchTest {
    WordSearch wordSearch;
    Set<String> words= new TreeSet<>();

    @Before
    public void createInput(){
        int rows = 2;
        int columns = 3;
        char grid[][] = {{'a','a','r'}, {'t','c','d'}};
        List<String> dict = new ArrayList<>();
        dict.add("car");
        dict.add("card");
        dict.add("cart");
        dict.add("cat");
        wordSearch = new WordSearch(rows, columns, grid, dict);
        words.add("car");
        words.add("card");
        words.add("cat");
    }

    @Test
    public void checkTheCorrectWords(){
        Set<String> findWords = wordSearch.findAllWords();
        Assert.assertTrue(findWords.equals(words));
    }

    @Test
    public void checkNotAppropriateWord(){
        Set<String> findWords = wordSearch.findAllWords();
        words.add("cart");
        Assert.assertFalse(findWords.equals(words));
    }
}
