/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Laura
 */
public class WordTest extends TestCase{

    @Test
    public void testWords1() {
        HashMap<String, String> dictionary = new HashMap();
        dictionary.put("CART", "EXPLANATION1");
        dictionary.put("CAR", "EXPLANATION2");
        dictionary.put("CARD", "EXPLANATION3");
        dictionary.put("CAT", "EXPLANATION4");
        Dictionary dict = new Dictionary(dictionary);
        char[][] letterMatrix = new char[2][3];
        ArrayList<Character> letters = new ArrayList<>();
        letters.add('A');
        letters.add('A');
        letters.add('R');
        letters.add('T');
        letters.add('C');
        letters.add('D');
        int k = 0;
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 3; j++)
                letterMatrix[i][j] = letters.get(k++);
        Grid g = new Grid(2, 3, letterMatrix, dict);
        
        TreeSet<String> solution = new TreeSet<>();
        solution.add("CAR"); solution.add("CARD"); solution.add("CAT");
        
        assertEquals(solution, Word.words(g));
    }

    @Test
    public void testWords2() {
        HashMap<String, String> dictionary = new HashMap();
        Dictionary dict = new Dictionary(dictionary);
        char[][] letterMatrix = new char[0][0];
        Grid g = new Grid(0, 0, letterMatrix, dict);
        TreeSet<String> solution = new TreeSet<>();
        assertEquals(solution, Word.words(g));
        
    }
    
}
