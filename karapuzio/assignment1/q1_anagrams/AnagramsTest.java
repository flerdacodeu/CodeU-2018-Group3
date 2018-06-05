package test.edu.codeU.assignment1.anagrams;

import edu.codeU.assignment1.anagrams.Anagrams;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for anagrams.
 */
public class AnagramsTest {
    private Anagrams anagrams = new Anagrams();

    @Test
    public void checkOneWordAnagram() {
        String str1 = "333mama";
        String str2 = "amam333";
        boolean checking = anagrams.check(str1, str2);
        Assert.assertTrue(checking);
    }

    @Test
    public void checkManyWordsAnagrams() {
        String str1 = "mama papa kek";
        String str2 = "kek amam apap";
        boolean checking = anagrams.check(str1, str2);
        Assert.assertTrue(checking);
    }

    @Test
    public void checkAnagramWithPunctuation() {
        String str1 = "mama...pap...son";
        String str2 = "amam...nos!!!!pap";
        boolean checking = anagrams.check(str1, str2);
        Assert.assertTrue(checking);
    }

    @Test
    public void checkAnagramWithDiffNumberOfWords() {
        String str1 = "mama papa!!! gran's";
        String str2 = "amam";
        boolean checking = anagrams.check(str1, str2);
        Assert.assertFalse(checking);
    }

    @Test
    public void checkAnagramWithApostropheAndCases() {
        String str1 = "Olya's KEK's ayyy";
        String str2 = "YYYA s'aylo s'kek";
        boolean checking = anagrams.check(str1, str2);
        Assert.assertTrue(checking);
    }
}
