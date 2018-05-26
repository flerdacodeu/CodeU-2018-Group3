import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Q1AnagramTest {
    @Test
    public void testEmptyString(){
        assertTrue(Q1Anagram.checkAnagram("","",true));
        assertTrue(Q1Anagram.checkAnagram("","",false));
        assertFalse(Q1Anagram.checkAnagram("a","",true));
        assertFalse(Q1Anagram.checkAnagram("a","",false));
        assertFalse(Q1Anagram.checkAnagram("","a",true));
        assertFalse(Q1Anagram.checkAnagram("","a",false));
    }

    @Test
    public void testEmptyStringWordByWord(){
        assertTrue(Q1Anagram.checkAnagramWordByWord("","",true));
        assertTrue(Q1Anagram.checkAnagramWordByWord("","",false));
        assertFalse(Q1Anagram.checkAnagramWordByWord("a","",true));
        assertFalse(Q1Anagram.checkAnagramWordByWord("a","",false));
        assertFalse(Q1Anagram.checkAnagramWordByWord("","a",true));
        assertFalse(Q1Anagram.checkAnagramWordByWord("","a",false));
        assertTrue(Q1Anagram.checkAnagramWordByWord(" a a"," a  a ",true));
        assertTrue(Q1Anagram.checkAnagramWordByWord("a  a","a a",true));
    }

    @Test
    public void testStrings(){
        assertTrue(Q1Anagram.checkAnagram("listen","silent",true));
        assertFalse(Q1Anagram.checkAnagram("Listen","silent",true));
        assertFalse(Q1Anagram.checkAnagram("listen","Silent",true));
        assertTrue(Q1Anagram.checkAnagram("Listen","silent",false));
        assertTrue(Q1Anagram.checkAnagram("listen","Silent",false));

        assertFalse(Q1Anagram.checkAnagram("apple","pabble",true));
        assertFalse(Q1Anagram.checkAnagram("apple","pabble",false));
    }

    @Test
    public void testStringsWordByWord(){
        assertTrue(Q1Anagram.checkAnagramWordByWord("Hello World","lorwd ohlel",false));
        assertFalse(Q1Anagram.checkAnagramWordByWord("Hello World","lorwd ohlel",true));
        assertFalse(Q1Anagram.checkAnagramWordByWord("Hello World","lorwdo hell",false));
        assertFalse(Q1Anagram.checkAnagramWordByWord("Hello World","lorwdo hell",true));
    }
}
