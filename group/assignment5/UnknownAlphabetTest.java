package test.edu.codeU.assignment5;

import edu.codeU.assignment5.UnknownAlphabet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UnknownAlphabetTest {

    private UnknownAlphabet unknownAlphabet;

    @Before
    public void createInput(){
        ArrayList<String> dict = new ArrayList<>();
        dict.add("art");
        dict.add("rat");
        dict.add("cat");
        dict.add("car");
        unknownAlphabet = new UnknownAlphabet(dict);
    }

    @Test
    public void checkTheCorrectNumberOfIsland(){
        ArrayList<Character> alphabet = unknownAlphabet.fihndAlphabet();
        ArrayList<Character> possible = new ArrayList<>();
        possible.add('a');
        possible.add('t');
        possible.add('r');
        possible.add('c');
        Assert.assertEquals(alphabet, possible);
    }

    @Test
    public void checkTheCorrectAlphabets(){
        ArrayList<ArrayList<Character>> alphabets = unknownAlphabet.fihndAllAlphabets();
        ArrayList<ArrayList<Character>> set = new ArrayList<>();
        ArrayList<Character> possible = new ArrayList<>();
        possible.add('a');
        possible.add('t');
        possible.add('r');
        possible.add('c');
        ArrayList<Character> possible2 = new ArrayList<>();
        possible.add('t');
        possible.add('a');
        possible.add('r');
        possible.add('c');
        set.add(possible);
        set.add(possible2);
        Assert.assertEquals(set, alphabets);
    }
}
