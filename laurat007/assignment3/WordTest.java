/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Laura
 */
public class WordTest extends TestCase{

    @Test
    public void testWords1() {
        TreeSet<String> dictionary = new TreeSet<>();
        dictionary.add("CART");
        dictionary.add("CAR");
        dictionary.add("CARD");
        dictionary.add("CAT");
        Dictionary dict = new Dictionary(dictionary);
        char[][] letterMatrix = new char[2][3];
        ArrayList<Character> letters = new ArrayList<>();
        letters.add('A');
        letters.add('A');
        letters.add('R');
        letters.add('T');
        letters.add('C');
        letters.add('D');
        
        // A A R
        // T C D
        
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
        TreeSet<String> dictionary = new TreeSet<>();
        Dictionary dict = new Dictionary(dictionary);
        char[][] letterMatrix = new char[0][0];
        Grid g = new Grid(0, 0, letterMatrix, dict);
        TreeSet<String> solution = new TreeSet<>();
        assertEquals(solution, Word.words(g));
        
    }
    
    @Test
    public void testWords3() {
        TreeSet<String> dictionary = new TreeSet<>();
        dictionary.add("MAKE");
        dictionary.add("MEET");
        dictionary.add("TEAM");
        dictionary.add("BUG");
        dictionary.add("MUG");
        Dictionary dict = new Dictionary(dictionary);
        
        char[][] letterMatrix = new char[2][5];
        ArrayList<Character> letters = new ArrayList<>();
        letters.add('B');
        letters.add('M');
        letters.add('A');
        letters.add('M');
        letters.add('I');
        letters.add('G');
        letters.add('U');
        letters.add('K');
        letters.add('E');
        letters.add('T');
        
        // B M A M I
        // G U K E T
        
        int k = 0;
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 5; j++)
                letterMatrix[i][j] = letters.get(k++);
        Grid g = new Grid(2, 5, letterMatrix, dict);
        
        TreeSet<String> solution = new TreeSet<>();
        solution.add("BUG"); solution.add("MAKE"); solution.add("MUG"); solution.add("TEAM");
        assertEquals(solution, Word.words(g));
    }
}
